package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.types.Buildings;

public enum BuildingMenus {
    MERCENARY_POST(Buildings.MERCENARY_POST, "MercenaryPostMenu"),
    BARRACKS(Buildings.BARRACKS, "BarracksMenu"),
    LORD_HOUSE("lord house", "lord menu"),
    MARKET(Buildings.MARKET,"MarketMenu"),
    ENGINEER_GUID(Buildings.ENGINEER_GUID,"EngineerGuildMenu");

    private String name;
    private String menu;
    private String imagePath;
    private Buildings buildingCost;

    BuildingMenus(Buildings building, String menu) {
        this.name = building.getName();
        buildingCost = building;
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name;
        this.menu = menu;
    }

    BuildingMenus(String name, String menu) {
        this.name = name;
        this.menu = menu;
        buildingCost = null;
        imagePath = "Media/Buildings/" + name + "/" + name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return menu;
    }

    public static BuildingMenus getEnumByName(String name) {
        for (BuildingMenus buildingMenu : values()) {
            if(buildingMenu.name.equalsIgnoreCase(name)){
                return buildingMenu;
            }
        }
        return null;
    }
}
