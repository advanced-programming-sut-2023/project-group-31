package model.game_stuff.buildings.enums;

import model.game_stuff.enums.ItemTypes;

public enum BuildingMenus {
    MERCENARY_POST("Mercenary post", "MercenaryPostMenu", "Castle"),
    BARRACK("Barrack", "BarracksMenu", "Castle"),
    LORD_HOUSE("lord house", "lord menu", "lord house"),
    MARKET("Market","MarketMenu", "Industry"),
    ENGINEER_GUID("Engineer guid","EngineerGuildMenu", "Castle");

    private String name;
    private String menu;
    private String imagePath;

    BuildingMenus(String name, String menu, String category) {
        this.name = name;
        this.menu = menu;
        imagePath = "/Media/Buildings/" + category + "/" + name + "/" + name + ".png";
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return menu;
    }

    public String getImagePath() {
        return imagePath;
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
