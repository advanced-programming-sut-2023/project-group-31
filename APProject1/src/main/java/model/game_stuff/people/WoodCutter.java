package model.game_stuff.people;

import model.game_stuff.Building;
import model.game_stuff.buildings.Producer;
import model.game_stuff.enums.Items;
import model.game_stuff.people.enums.WorkerTypes;

public class WoodCutter extends Worker{
    public WoodCutter(Producer workHouse, WorkerTypes type) {
        super(workHouse, type);
    }
}
