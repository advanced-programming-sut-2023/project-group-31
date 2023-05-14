package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.*;
import model.game_stuff.buildings.*;
import model.game_stuff.buildings.enums.*;
import model.game_stuff.people.Lord;
import model.game_stuff.people.Troop;
import model.game_stuff.types.Buildings;
import view.game_system.messages.StartGameMessages;
import view.game_system.messages.TurnMessages;

import java.util.ArrayList;

public class TurnController extends ControllerUtils {
    public static TurnMessages dropBuilding() {
        Buildings buildingType;
        int x = Integer.parseInt(inputs.get("x"));
        int y = Integer.parseInt(inputs.get("y"));

        if ((buildingType = Buildings.getBuildingByName(inputs.get("type"))) == null) {
            return TurnMessages.INVALID_BUILDING_TYPE;
        }
        if (isThereAPlaceForBuilding(x, y, buildingType) != null) {
            return isThereAPlaceForBuilding(x, y, buildingType);
        }
        if (!isRequirementEnough(buildingType, 1)) {
            return TurnMessages.NOT_ENOUGH_REQUIREMENT;
        }
        Building building=createNewBuilding(inputs.get("type"));
        for (int i = x; i < buildingType.getLength(); i++) {
            for (int j = y; j < buildingType.getWidth(); j++) {
                currentGame.getMap().getBlock(i, j).setBuilding(building);
                building.addBlock(currentGame.getMap().getBlock(i, j));
            }
        }
        currentPlayer.addBuilding(building);
        decreaseRequirement(buildingType,1);
        return TurnMessages.SUCCESS;
    }

    private static TurnMessages isThereAPlaceForBuilding(int x, int y, Buildings buildingType) {
        for (int i = x; i < buildingType.getLength(); i++) {
            for (int j = y; j < buildingType.getWidth(); j++) {
                if (i >= currentGame.getMap().getSize() || j >= currentGame.getMap().getSize()) {
                    return TurnMessages.OUT_OF_MAP;
                }
                if (!buildingType.isAreaPossible(currentGame.getMap().getBlock(i, j).getType())) {
                    return TurnMessages.BANNED_AREA;
                }
                if (!currentMap.getBlock(i, j).isEmpty()) {
                    return TurnMessages.THERE_IS_BUILDING_ON_AREA;
                }
            }
        }
        return null;
    }

    private static Building createNewBuilding(String type) {
        Building building;
        if (BuildingMenus.getEnumByName(type) != null) {
            return new MenuBuilding(currentPlayer, BuildingMenus.getEnumByName(type));
        }
        if (FactorRiserTypes.getEnumByName(type) != null) {
            return new FactorRiser(currentPlayer, FactorRiserTypes.getEnumByName(type));
        }
        if (GateHouseTypes.getEnumByName(type) != null) {
            return new GateHouse(GateHouseTypes.getEnumByName(type), currentPlayer, true);
        }
        if (ProducerTypes.getEnumByName(type) != null) {
            return new Producer(ProducerTypes.getEnumByName(type), currentPlayer);
        }
        if (StorageTypes.getEnumByName(type) != null) {
            return new Storage(currentPlayer, StorageTypes.getEnumByName(type));
        }
        if (TowerTypes.getEnumByName(type) != null) {
            return new Tower(TowerTypes.getEnumByName(type), currentPlayer);
        }
        if (TrapTypes.getEnumByName(type) != null) {
            return new Trap(TrapTypes.getEnumByName(type), currentPlayer);
        }
        return null;
    }

    private static boolean isRequirementEnough(Buildings buildings, double percent) {
        if (Math.ceil(buildings.getGoldNeeded() * percent) > currentPlayer.getPossession().getGold()) {
            return false;
        }
        if (Math.ceil(buildings.getIronNeeded() * percent) > currentPlayer.getPossession().getIron()) {
            return false;
        }
        if (Math.ceil(buildings.getRockNeeded() * percent) > currentPlayer.getPossession().getStone()) { //stone ==rock
            return false;
        }
        if (Math.ceil(buildings.getWoodNeeded() * percent) > currentPlayer.getPossession().getWood()) { //stone ==rock
            return false;
        }
        return true;
    }

    private static boolean decreaseRequirement(Buildings buildings, double percent) {
        Double dou;
        int amountToGet;
        currentPlayer.getPossession().setGold((int)Math.ceil(currentPlayer.getPossession().getGold()-buildings.getGoldNeeded() * percent));
        amountToGet=(int)Math.ceil(buildings.getIronNeeded() * percent);
        currentPlayer.getPossession().setIron(currentPlayer.getPossession().getIron() - amountToGet);
        currentPlayer.getPossession().setStone((int)Math.ceil(currentPlayer.getPossession().getStone()-buildings.getRockNeeded() * percent));
        currentPlayer.getPossession().setWood((int)Math.ceil(currentPlayer.getPossession().getWood()-buildings.getWoodNeeded() * percent));
        return true;
    }

    public static TurnMessages selectBuilding() {
        int x = Integer.parseInt(inputs.get("x"));
        int y = Integer.parseInt(inputs.get("y"));
        Building building;
        if ((building = currentGame.getMap().getBlock(x, y).getBuilding()) == null) {
            return TurnMessages.EMPTY_PLACE;
        }
        if (!(building instanceof MenuBuilding)) {
            return TurnMessages.MESSAGE.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.BARRACK)) {
            return TurnMessages.BARRACK.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.MERCENARY_POST)) {
            return TurnMessages.MERCENARY_POST.setAndGetTxt(building.toString());
        }
        return null;
    }


    public static TurnMessages repair() {
        int x = Integer.parseInt(inputs.get("x"));
        int y = Integer.parseInt(inputs.get("y"));
        Building building;
        if ((building = currentGame.getMap().getBlock(x, y).getBuilding()) == null) {
            return TurnMessages.EMPTY_PLACE;
        }
        for(Block block:currentGame.getMap().getNeighboursWithDistance(currentGame.getMap().getBlock(x,y), 3)){
            if(block.containsEnemyPerson(building.getOwner().getColor())){
                return TurnMessages.ENEMY_IS_CLOSE;
            }
        }
        if(isRequirementEnough(Buildings.getBuildingByName(building.getName()),building.getHpLost())){
            return TurnMessages.NOT_ENOUGH_REQUIREMENT;
        }
        building.getRepaired();
        decreaseRequirement(Buildings.getBuildingByName(building.getName()),1);
        return null;
    }

    public static TurnMessages nextTurn() {
        int counter = 0;
        Government playerToRun = currentPlayer;
        while (counter < currentGame.getPlayers().size()) {
            for (Working workingsBuilding : playerToRun.getWorkingsBuildings()) {
                workingsBuilding.work();
            }
            for (Troop troop : playerToRun.getTroops()) {
                troop.work();
            }
            playerToRun.nextTurn(); //TODO: islah
            if(isTheGameOver()) {
                TurnMessages.GAME_FINISHED.setTxt(announceTheWinners());
                //TODO: calculate scores
                return TurnMessages.GAME_FINISHED;
            }
        }
        //TODO: announce dead lords
        return TurnMessages.SUCCESS;
    }
    private static Government GetNextPlayer(Government player) {
        int next = 0;
        for(int i = 0; i < currentGame.getPlayers().size(); i++) {
            if(currentGame.getPlayers().get(i).equals(player)) {
                next = (i + 1) % currentGame.getPlayers().size();
            }
        }
        return currentGame.getPlayers().get(next);
    }
    private static void nextTurnForGovernment() {

    }
    private static boolean isTheGameOver() {
        Colors color = currentPlayer.getColor();
        for (Government player : currentGame.getPlayers()) {
            if(!player.getColor().equals(color))
                return false;
        }
        return true;
    }

    private static String announceTheWinners() {
        String output = currentPlayer.getColor().getName().toUpperCase() + " team wins:";
        for (Government player : currentGame.getPlayers()) {
            output += "\n* " + player.getName();
        }
        return output;
    }

    public static TurnMessages selectUnit() {
        if(!inputs.containsKey("x") || !inputs.containsKey("y")) {
            return TurnMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim()) - 1;
        int y = Integer.parseInt(inputs.get("y").trim()) - 1;
        if(!currentMap.isInMap(x, y)) {
            return TurnMessages.COORDINATE_OUT_OF_BOUND;
        }
        Block block = currentMap.getBlock(x, y);
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(block);
        ArrayList<Troop> troops = getMyTroopsIn(blocks);
        if(troops.size() < 1) {
            return TurnMessages.NO_UNIT_FOUND;
        }
        if(troops.size() == 1 && (troops.get(0) instanceof Lord)) {
            UnitController.setTroops(troops);
            return TurnMessages.SUCCESS;
        }
        for (Troop troop : troops) {
            if(troop instanceof Lord) {
                troops.remove(troop);
                break;
            }
        }
        return TurnMessages.SUCCESS;
    }
    private static ArrayList<Troop> getMyTroopsIn(ArrayList<Block> blocks) {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Block block : blocks) {
            for (Person person : block.getPeople()) {
                if ((person instanceof Troop) && person.getOwner().equals(currentPlayer)) {
                    troops.add((Troop) person);
                }
            }
        }
        return troops;
    }

    public static TurnMessages selectMultipleUnits() {
        if(!inputs.containsKey("x1") || !inputs.containsKey("y1") ||
            !inputs.containsKey("x2") || !inputs.containsKey("y2") || !inputs.containsKey("type")) {
            return TurnMessages.INVALID_COMMAND;
        }
        int x1 = Integer.parseInt(inputs.get("x1").trim()) - 1;
        int y1 = Integer.parseInt(inputs.get("y1").trim()) - 1;
        int x2 = Integer.parseInt(inputs.get("x2").trim()) - 1;
        int y2 = Integer.parseInt(inputs.get("y2").trim()) - 1;
        if(x1 < 0 || x1 > x2 || x2 >= currentMap.getLength() ||
            y1 < 0 || y1 > y2 || y2 >= currentMap.getLength()) {
            return TurnMessages.COORDINATE_OUT_OF_BOUND;
        }
        ArrayList<Block> blocks = new ArrayList<>();
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                blocks.add(currentMap.getBlock(i, j));
            }
        }
        ArrayList<Troop> troops = getMyTroopsIn(blocks);
        if(troops.size() < 1) {
            return TurnMessages.NO_UNIT_FOUND;
        }
        if(troops.size() == 1 && (troops.get(0) instanceof Lord)) {
            UnitController.setTroops(troops);
            return TurnMessages.SUCCESS;
        }
        for (Troop troop : troops) {
            if(troop instanceof Lord) {
                troops.remove(troop);
                break;
            }
        }
        return TurnMessages.SUCCESS;
    }
}
