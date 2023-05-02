package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.GateHouseTypes;

public class GateHouse extends Building {
    private GateHouseTypes type;
    private boolean isHorizontal;
    private boolean isOpen;
    {
        isOpen = false;
    }
    public GateHouse(GateHouseTypes type, Government government, boolean isHorizontal) {
        super(government);
        this.type=type;
        this.isHorizontal = isHorizontal;
        hp = type.getHp();
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public GateHouseTypes getType() {
        return type;
    }

    public String getName() {
        return type.getName();
    }
}
