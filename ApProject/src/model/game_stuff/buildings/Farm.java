package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Food;
import model.game_stuff.Good;
import model.game_stuff.buildings.enums.FarmType;

public class Farm extends Building {
    private Food foodToBeGenerated;
    private FarmType type;

    Farm(FarmType type) {
    }
    public void generate() {

    }
}
