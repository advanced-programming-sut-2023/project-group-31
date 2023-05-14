package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.BuildingMenus;

public class MenuBuilding extends Building {
    private BuildingMenus type;
    public MenuBuilding(Government government, BuildingMenus menu) {
        super(government);
        this.type = menu;
        owner.addBuilding(this);
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
