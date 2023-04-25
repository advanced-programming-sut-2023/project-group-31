package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.buildings.enums.StoneGatehouses;

public class StoneGatehouse extends Building {
    private StoneGatehouses type;
    public StoneGatehouse(StoneGatehouses type) {
        this.type=type;
        hp= type.getHp();
    }

    public StoneGatehouses getType() {
        return type;
    }

    public void changeTax(){

    }
}
