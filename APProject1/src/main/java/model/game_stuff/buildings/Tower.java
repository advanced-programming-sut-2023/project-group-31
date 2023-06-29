package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.TowerTypes;
import model.game_stuff.enums.Direction;

import java.util.HashMap;


public class Tower extends Building {
    private final TowerTypes type;
    private HashMap<Direction, Boolean> hasStairs;

    {
        hasStairs = new HashMap<>();
    }

    public Tower(TowerTypes type, Government government) {
        super(government);
        this.type = type;
        this.hp = type.getHp();
        owner.addBuilding(this);
        name = type.getName();
        imagePath = type.getImagePath();
    }

    public boolean isFull() {
		return getPosition().getNumberOfPeople() >= type.getCapacity();
    }

    public boolean canExit(Direction direction) {
        return hasStairs.get(direction);
    }

    public boolean canEnter(Direction direction) {
        switch (direction) {
            case UP:
                return hasStairs.get(Direction.DOWN);
            case DOWN:
                return hasStairs.get(Direction.UP);
            case LEFT:
                return hasStairs.get(Direction.RIGHT);
            default: RIGHT:
                return hasStairs.get(Direction.LEFT);
        }
    }

    public void setStairs(Direction direction, boolean hasStairs) {
        this.hasStairs.put(direction, hasStairs);
    }

    public TowerTypes getType() {
        return type;
    }

    public int getFireRange() {
        return type.getFireRange();
    }

    public int getDefendRange() {
        return type.getDefendRange();
    }

    @Override
    public String toString() {
        String output = type.getName() + "\n" +
            "hp: " + hp + " / " + maxHp;
        if(!hasStairs.isEmpty()) {
            output += "\nhas stairs in directions:";
            for (Direction direction : hasStairs.keySet()) {
                if (hasStairs.get(direction)) {
                    output += " " + direction.getName();
                }
            }
        }
        return output;
    }
}
