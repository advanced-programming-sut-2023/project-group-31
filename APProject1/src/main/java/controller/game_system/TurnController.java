package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Block;
import model.game_stuff.Building;
import model.game_stuff.buildings.*;
import model.game_stuff.buildings.enums.*;
import model.game_stuff.enums.Textures;
import model.game_stuff.types.Buildings;
import view.game_system.commands.TurnCommands;
import view.game_system.messages.TurnMessages;
import view.user_system.messages.UserMessages;

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
                if (currentGame.getMap().getBlock(i, j).getBuilding() != null) {
                    return TurnMessages.THERE_IS_BUILDING_ON_AREA;
                }
            }
        }
        return null;
    }

    private static Building createNewBuilding(String type) {
        Building building;
        if (BuildingMenus.getEnumByName(type) != null) {
            return new MenuBuildings(currentPlayer, BuildingMenus.getEnumByName(type));
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
        currentPlayer.getPossession().setGold((int)Math.ceil(currentPlayer.getPossession().getGold()-buildings.getGoldNeeded() * percent));
        currentPlayer.getPossession().setIron((int)Math.ceil(currentPlayer.getPossession().getIron()-buildings.getIronNeeded() * percent));
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
        if (!(building instanceof MenuBuildings)) {
            return TurnMessages.MESSAGE.setAndGetTxt(building.toString());
        }
        if (((MenuBuildings) building).getType().equals(BuildingMenus.BARRACK)) {
            return TurnMessages.BARRACK.setAndGetTxt(building.toString());
        }
        if (((MenuBuildings) building).getType().equals(BuildingMenus.MERCENARY_POST)) {
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

    public static UserMessages digTunnel(String username, int x, int y) {
        return null;
    }

    public static UserMessages build(String equipmentName) {
        return null;
    }

    public static UserMessages disbandUnit() {
        return null;
    }

}
