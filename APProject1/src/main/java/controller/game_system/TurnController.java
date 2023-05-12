package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Building;
import model.game_stuff.buildings.FactorRiser;
import model.game_stuff.buildings.GateHouse;
import model.game_stuff.buildings.MenuBuildings;
import model.game_stuff.buildings.enums.BuildingMenus;
import model.game_stuff.buildings.enums.FactorRiserTypes;
import model.game_stuff.buildings.enums.GateHouseTypes;
import model.game_stuff.enums.Textures;
import model.game_stuff.types.Buildings;
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

        for (int i = x; i < buildingType.getLength(); i++) {
            for (int j = y; j < buildingType.getWidth(); j++) {
                currentGame.getMap().getBlock(i, j).setBuilding();
            }
        }

        currentGame.getMap();
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
            return new GateHouse(currentPlayer, GateHouseTypes.getEnumByName(type),);
        }

    }

    public static TurnMessages selectBuilding(int x, int y) {
        return null;
    }


    public static TurnMessages repair(int x, int y, String type) {
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
