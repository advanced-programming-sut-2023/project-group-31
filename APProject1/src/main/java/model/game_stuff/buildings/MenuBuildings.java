package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.BuildingMenus;

public class MenuBuildings extends Building {
    private BuildingMenus menu;
    public MenuBuildings(Government government, BuildingMenus menu) {
        super(government);
        this.menu = menu;
    }
}
