package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.buildings.enums.BaseType;

public class Base extends Building {
    private BaseType type;

    public Base(BaseType type) {
        this.type = type;
    }
}
