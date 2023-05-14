package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.Waiter;
import model.game_stuff.Working;
import model.game_stuff.buildings.enums.GateHouseTypes;

public class GateHouse extends Building implements Working {
    private GateHouseTypes type;
    private boolean isHorizontal;
    private Waiter closerWaiter;
    private boolean isOpen;
    {
        isOpen = false;
        closerWaiter = new Waiter(5);
    }
    public GateHouse(GateHouseTypes type, Government government, boolean isHorizontal) {
        super(government);
        this.type=type;
        this.isHorizontal = isHorizontal;
        hp = type.getHp();
        owner.addBuilding(this);
    }
    public void work() {
        if (isOpen && closerWaiter.isTheTurn()) {
            isOpen = false;
        }
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

    public void open() {
        isOpen = true;
        closerWaiter.reset();
    }

    public void close() {
        isOpen = false;
    }

    public GateHouseTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        String output = type.getName() + "\n" +
            "hp: " + hp + " / " + maxHp + "\n";
        if(isOpen) {
            output += "open";
        } else {
            output += "closed";
        }
        return output;
    }
}
