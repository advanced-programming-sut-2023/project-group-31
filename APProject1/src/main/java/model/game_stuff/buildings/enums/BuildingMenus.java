package model.game_stuff.buildings.enums;

import model.game_stuff.enums.ItemTypes;

public enum BuildingMenus {
    MERCENARY_POST("Mercenary post", "MercenaryPostMenu"),
    BARRACK("Barrack", "BarracksMenu"),
    LORD_HOUSE("lord house", "lord menu" ),
    MARKET("Market","MarketMenu"),
    ENGINEER_GUID("Engineer guid","EngineerGuildMenu");

    private String name;
    private String menu;

    BuildingMenus(String name, String menu) {
        this.name = name;
        this.menu = menu;
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
