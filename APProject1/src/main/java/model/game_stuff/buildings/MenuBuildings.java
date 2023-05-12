package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.BuildingMenus;

public class MenuBuildings extends Building {
    private BuildingMenus type;
    public MenuBuildings(Government government, BuildingMenus menu) {
        super(government);
        this.type = menu;
    }

    public String getMenu() {
        return type.getMenu();
    }

    public BuildingMenus getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.getName() + "\n" +
            "hp: " + hp + " / " + maxHp;
    }
}
