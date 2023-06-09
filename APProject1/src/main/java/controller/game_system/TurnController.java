package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.*;
import model.game_stuff.buildings.*;
import model.game_stuff.buildings.enums.*;
import model.game_stuff.enums.Items;
import model.game_stuff.people.Lord;
import model.game_stuff.people.Troop;
import model.game_stuff.types.Buildings;
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
        Building building = createNewBuilding(inputs.get("type"));

        for (int i = x; i < x + buildingType.getHeight(); i++) {
            for (int j = y; j < y + buildingType.getWidth(); j++) {
                currentMap.getBlock(i, j).setBuilding(building);
                building.addBlock(currentMap.getBlock(i, j));
            }
        }

        currentPlayer.addBuilding(building);
        decreaseRequirement(buildingType, 1);
        building.setRectangle();

        return TurnMessages.SUCCESS;
    }

    public static TurnMessages isThereAPlaceForBuilding(int x, int y, Buildings buildingType) {
        for (int i = x; i < x + buildingType.getHeight(); i++) {
            for (int j = y; j < y + buildingType.getWidth(); j++) {
                if (i >= currentMap.getSize() || j >= currentMap.getSize()) {
                    return TurnMessages.OUT_OF_MAP;
                }
                if (!buildingType.isAreaPossible(currentMap.getBlock(i, j).getType())) {
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
        if (BuildingMenus.getEnumByName(type) != null) {
            return new MenuBuilding(currentPlayer, BuildingMenus.getEnumByName(type));
        }
        if (FactorRiserTypes.getEnumByName(type) != null) {
            if (currentPlayer.getReligionRate() == 0) {
                currentPlayer.setReligionRate(2);
            }
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

    public static boolean isRequirementEnough(Buildings buildings, double percent) {
        if (Math.ceil(buildings.getGoldNeeded() * percent) > currentPlayer.getPossession().getGold()) {
            return false;
        }
        if (Math.ceil(buildings.getIronNeeded() * percent) > currentPlayer.getPossession().getItem(Items.IRON)) {
            return false;
        }
        if (Math.ceil(buildings.getRockNeeded() * percent) > currentPlayer.getPossession().getItem(Items.STONE)) { //stone ==rock
            return false;
        }
        if (Math.ceil(buildings.getWoodNeeded() * percent) > currentPlayer.getPossession().getItem(Items.WOOD)) {
            return false;
        }
        return true;
    }

    private static boolean decreaseRequirement(Buildings buildings, double percent) {
        int amountToGet;
        currentPlayer.getPossession().setGold((int) Math.ceil(currentPlayer.getPossession().getGold() - buildings.getGoldNeeded() * percent));
        amountToGet = (int) Math.ceil(buildings.getIronNeeded() * percent);
        currentPlayer.getPossession().setItem(Items.IRON, -amountToGet);
        currentPlayer.reduceItem(Items.IRON, amountToGet);
        amountToGet = (int) Math.ceil(-buildings.getRockNeeded() * (int) percent);
        currentPlayer.getPossession().setItem(Items.STONE, (int) -buildings.getRockNeeded() * (int) percent);
        currentPlayer.reduceItem(Items.STONE, amountToGet);
        amountToGet = (int) Math.ceil(-buildings.getWoodNeeded() * percent);
        currentPlayer.getPossession().setItem(Items.WOOD, (int) (-buildings.getWoodNeeded() * percent));
        currentPlayer.reduceItem(Items.WOOD, amountToGet);
        return true;
    }

    public static TurnMessages selectBuilding() {
        int x = Integer.parseInt(inputs.get("x"));
        int y = Integer.parseInt(inputs.get("y"));
        Building building;
        if ((building = currentMap.getBlock(x, y).getBuilding()) == null) {
            return TurnMessages.EMPTY_PLACE;
        }
        if (!building.getOwner().equals(currentPlayer)) {
            return TurnMessages.NOT_YOUR_BUILDING;
        }
        if (!(building instanceof MenuBuilding)) {
            return TurnMessages.MESSAGE.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.BARRACKS)) {
            BarrackController.setMenuBuilding((MenuBuilding) building);
            return TurnMessages.BARRACK.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.MERCENARY_POST)) {
            MercenaryController.setMenuBuilding((MenuBuilding) building);
            return TurnMessages.MERCENARY_POST.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.ENGINEER_GUID)) {
            MercenaryController.setMenuBuilding((MenuBuilding) building);
            return TurnMessages.ENGINEER_GUID.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.LORD_HOUSE)) {
            return TurnMessages.LORD_HOUSE.setAndGetTxt(building.toString());
        }
        if (((MenuBuilding) building).getType().equals(BuildingMenus.MARKET)) {
            BarrackController.setMenuBuilding((MenuBuilding) building);
            return TurnMessages.MARKET.setAndGetTxt(building.toString());
        }
        return null;
    }


    public static TurnMessages repair() {
        int x = Integer.parseInt(inputs.get("x"));
        int y = Integer.parseInt(inputs.get("y"));
        Building building;
        if ((building = currentMap.getBlock(x, y).getBuilding()) == null) {
            return TurnMessages.EMPTY_PLACE;
        }
        for (Block block : currentMap.getNeighboursWithDistance(currentMap.getBlock(x, y), 3)) {
            if (block.containsEnemyPerson(building.getOwner().getColor())) {
                return TurnMessages.ENEMY_IS_CLOSE;
            }
        }
        if (isRequirementEnough(Buildings.getBuildingByName(building.getName()), building.getHpLost())) {
            return TurnMessages.NOT_ENOUGH_REQUIREMENT;
        }
        building.getRepaired();
        decreaseRequirement(Buildings.getBuildingByName(building.getName()), 1);
        return null;
    }

    public static TurnMessages nextTurn() {
        int counter = 0;
        Government playerToRun = currentPlayer;
        while (counter < currentGame.getPlayers().size()) {
            for (Building workingsBuilding : playerToRun.getBuildings()) {
                workingsBuilding.work();
            }
            for (Troop troop : playerToRun.getTroops()) {
                troop.work();
            }
            while(!playerToRun.getTroopsWaitingForEntrance().isEmpty()) {
                Troop troop = playerToRun.getTroopsWaitingForEntrance().poll();
                if(!playerToRun.getLordsHouse().setPerson(troop)) break;
                troop.setPosition(playerToRun.getLordsHouse());
                troop.work();
                troop.getOwner().getTroops().add(troop);
            }
            nextTurnForGovernment(playerToRun); //TODO: islah
            if (isTheGameOver()) {
                TurnMessages.GAME_FINISHED.setTxt(announceTheWinners());
                //TODO: calculate scores
                calculateScores();
                return TurnMessages.GAME_FINISHED;
            }

            counter++;
            playerToRun = getNextPlayer(playerToRun);
        }
        //TODO: announce dead lords
        currentPlayer = getNextPlayer(currentPlayer);
        return TurnMessages.SUCCESS;
    }


    private static void calculateScores() {
        for (Government player : currentGame.getPlayers()) {
            player.getOwner().setHighScore(player.getOwner().getHighScore() * 12 / 10);
        }
    }

    private static Government getNextPlayer(Government player) {
        int next = 0;
        for (int i = 0; i < currentGame.getPlayers().size(); i++) {
            if (currentGame.getPlayers().get(i).equals(player)) {
                next = (i + 1) % currentGame.getPlayers().size();
            }
        }
        return currentGame.getPlayers().get(next);
    }

    private static boolean isTheGameOver() {
        Colors color = currentPlayer.getColor();
        for (Government player : currentGame.getPlayers()) {
            if (!player.getColor().equals(color)) return false;
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
        if (!inputs.containsKey("x") || !inputs.containsKey("y")) {
            return TurnMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim());
        int y = Integer.parseInt(inputs.get("y").trim());
        if (!currentMap.isInMap(x, y)) {
            return TurnMessages.COORDINATE_OUT_OF_BOUND;
        }
        Block block = currentMap.getBlock(x, y);
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(block);
        ArrayList<Troop> troops = getMyTroopsIn(blocks);
        if (troops.size() < 1) {
            return TurnMessages.NO_UNIT_FOUND;
        }
        if (troops.size() == 1 && (troops.get(0) instanceof Lord)) {
            UnitController.setTroops(troops);
            return TurnMessages.SUCCESS;
        }
        for (Troop troop : troops) {
            if (troop instanceof Lord) {
                troops.remove(troop);
                break;
            }
        }
        UnitController.setTroops(troops);
        return TurnMessages.SUCCESS;
    }

    private static ArrayList<Troop> getMyTroopsIn(ArrayList<Block> blocks) {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Block block : blocks) {
            Person person = block.getPerson();
            if ((person instanceof Troop) && person.getOwner().equals(currentPlayer)) {
                troops.add((Troop) person);
            }
        }
        return troops;
    }

    public static TurnMessages selectMultipleUnits() {
        if (!inputs.containsKey("x1") || !inputs.containsKey("y1") || !inputs.containsKey("x2") || !inputs.containsKey("y2")) {
            return TurnMessages.INVALID_COMMAND;
        }
        int x1 = Integer.parseInt(inputs.get("x1").trim()) - 1;
        int y1 = Integer.parseInt(inputs.get("y1").trim()) - 1;
        int x2 = Integer.parseInt(inputs.get("x2").trim()) - 1;
        int y2 = Integer.parseInt(inputs.get("y2").trim()) - 1;
        if (x1 < 0 || x1 > x2 || x2 >= currentMap.getLength() || y1 < 0 || y1 > y2 || y2 >= currentMap.getLength()) {
            return TurnMessages.COORDINATE_OUT_OF_BOUND;
        }
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                blocks.add(currentMap.getBlock(i, j));
            }
        }
        ArrayList<Troop> troops = getMyTroopsIn(blocks);
        if (troops.size() < 1) {
            return TurnMessages.NO_UNIT_FOUND;
        }
        if (troops.size() == 1 && (troops.get(0) instanceof Lord)) {
            UnitController.setTroops(troops);
            return TurnMessages.SUCCESS;
        }
        for (Troop troop : troops) {
            if (troop instanceof Lord) {
                troops.remove(troop);
                break;
            }
        }
        return TurnMessages.SUCCESS;
    }


    //////////////////////////////////////////


    public static void nextTurnForGovernment(Government current) {
        //TODO ADD ARRAYS OF BUILDINGS
        //if(currentPlayer.getBuildings.contains(FactorRiser)){
        // currentPlayer.setReligionRate(2)}
        int addToPopularityFoods = 0;
        int addToPopularityBuildings = 0;
        if (!current.getBuildings().isEmpty()) {
            addToPopularityBuildings = 1;
        }
        addToPopularityFoods = current.getPossession().getItem(Items.MEAT) + current.getPossession().getItem(Items.BREAD) + current.getPossession().getItem(Items.CHEESE) + current.getPossession().getItem(Items.APPLE) - 1;
        current.setPopularity(current.getPopularity() + addToPopularityFoods + addToPopularityBuildings);
        if (current.getPopularity() > 100) {
            current.setPopularity(100);
        }
        if (current.getPopularity() < 0) {
            current.setPopularity(0);
        }
        checkForTax(current);
        checkForFood(current);
        checkForFear(current);
        current.nextTurn();
    }

    private static void checkForFear(Government current) {
        switch (current.getFearRate()) {
            case 5:
                current.setEfficiency(0.75);
                break;
            case 4:
                current.setEfficiency(0.80);
                break;
            case 3:
                current.setEfficiency(0.85);
                break;
            case 2:
                current.setEfficiency(0.90);
                break;
            case 1:
                current.setEfficiency(0.95);
                break;
            case 0:
                current.setEfficiency(1);
                break;
            case -1:
                current.setEfficiency(1.05);
                break;
            case -2:
                current.setEfficiency(1.1);
                break;
            case -3:
                current.setEfficiency(1.15);
                break;
            case -4:
                current.setEfficiency(1.2);
                break;
            case -5:
                current.setEfficiency(1.25);
                break;
            default:
                break;
        }
    }

    private static void checkForFood(Government current) {
        switch (current.getFoodRate()) {
            case -2:
                foodToPeople(0, -8, current);
                break;
            case -1:
                foodToPeople(0.5, -4, current);
                break;
            case 0:
                foodToPeople(1, 0, current);
                break;
            case 1:
                foodToPeople(1.5, 4, current);
                break;
            case 2:
                foodToPeople(2, 8, current);
                break;
            default:
                break;
        }
    }

    private static void foodToPeople(double foodToDonate, int popularityRate, Government current) {
        double toDecreaseFromStorage = Math.ceil(foodToDonate * current.getPopulation());
        while (toDecreaseFromStorage > 0) {
            for (Storage granary : current.getGranaries()) {
                if (toDecreaseFromStorage <= 0) {
                    break;
                }
                if (current.getPossession().getItem(Items.CHEESE) == 0 && current.getPossession().getItem(Items.BREAD) == 0 && current.getPossession().getItem(Items.APPLE) == 0 && current.getPossession().getItem(Items.MEAT) == 0) {
                    break;
                }
                if (granary.getProperties().containsKey(Items.CHEESE) && granary.getProperties().get(Items.CHEESE) != 0) {
                    granary.removeProduct(Items.CHEESE, 1);
                    current.getPossession().setItem(Items.CHEESE, -1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if (granary.getProperties().containsKey(Items.BREAD) && granary.getProperties().get(Items.BREAD) != 0) {
                    granary.removeProduct(Items.BREAD, 1);
                    current.getPossession().setItem(Items.BREAD, -1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if (granary.getProperties().containsKey(Items.APPLE) && granary.getProperties().get(Items.APPLE) != 0) {
                    granary.removeProduct(Items.APPLE, 1);
                    current.getPossession().setItem(Items.APPLE, -1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if (granary.getProperties().containsKey(Items.MEAT) && granary.getProperties().get(Items.MEAT) != 0) {
                    granary.removeProduct(Items.MEAT, 1);
                    current.getPossession().setItem(Items.MEAT, -1);
                    toDecreaseFromStorage--;
                    continue;
                }
            }
            break;
        }
        if (toDecreaseFromStorage != 0) {
            current.setFoodRate(-2);
        }
    }

    private static void checkForTax(Government current) {
        switch (current.getTaxRate()) {
            case -3:
                getTax(-1, 7, current);
            case -2:
                getTax(-0.8, 5, current);
            case -1:
                getTax(-0.6, 3, current);
            case 0:
                getTax(0, 1, current);
                break;
            case 1:
                getTax(0.6, -2, current);
                break;
            case 2:
                getTax(0.8, -4, current);
                break;
            case 3:
                getTax(1, -6, current);
                break;
            case 4:
                getTax(1.2, -8, current);
                break;
            case 5:
                getTax(1.4, -12, current);
                break;
            case 6:
                getTax(1.6, -16, current);
                break;
            case 7:
                getTax(1.8, -20, current);
                break;
            case 8:
                getTax(2, -24, current);
                break;
            default:
                break;
        }
    }

    private static void getTax(double gold, int toChange, Government current) {
        if (current.getPossession().getGold() == 0 && current.getTaxRate() < 0) {
            current.setTaxRate(0);
            return;
        }
        double toDecrease = Math.ceil(current.getPopulation() * gold);
        current.getPossession().setGold(current.getPossession().getGold() + (int) toDecrease);
        if (current.getPossession().getGold() < 0) {
            current.getPossession().setGold(0);
        }
    }
}
