package model.game_stuff.buildings.enums;

public enum BuildingMenus {
    ;
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
}
