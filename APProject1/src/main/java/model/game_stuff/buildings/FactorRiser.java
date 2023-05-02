package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.FactorRiserTypes;

public class FactorRiser extends Building {
    private FactorRiserTypes type;

    public FactorRiser(Government government, FactorRiserTypes type) {
        super(government);
        this.type = type;
    }

    public FactorRiserTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "FactorRiser{" +
                "type=" + type +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", blocks=" + blocks +
                '}';
    }
    public String getName() {
        return type.getName();
    }
}
