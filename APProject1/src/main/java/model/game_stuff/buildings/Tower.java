package model.game_stuff.buildings;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.game_stuff.Block;
import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.TowerTypes;
import model.game_stuff.enums.Direction;
import view.game_system.GameMainPage;

import java.net.URL;
import java.util.HashMap;


public class Tower extends Building {
    private final TowerTypes type;
    private boolean hasBrazer;
    private HashMap<Direction, Boolean> hasStairs;

    {
        hasStairs = new HashMap<>();
        for (Direction direction : Direction.values()) {
            hasStairs.put(direction, true);
        }
        hasBrazer = false;
    }

    public Tower(TowerTypes type, Government government) {
        super(government);
        this.type = type;
        this.hp = type.getHp();
        owner.addBuilding(this);
        name = type.getName();
        imagePath = type.getImagePath();
        baseBuildingType = type.getBaseBuildingType();
    }

    public boolean isFull() {
        int numberOfPeople = 0;
        for (Block block : blocks) {
            if(block.getPerson() != null) {
                numberOfPeople++;
            }
        }
        return numberOfPeople >= type.getCapacity();
    }

    public boolean hasBrazer() {
        return hasBrazer;
    }

    //todo add option to add brazer
    public void setBrazer() {
        this.hasBrazer = true;

        Rectangle brazer = new Rectangle(0.25, 0.25);
        brazer.setX(getPosition().getX());
        brazer.setY(getPosition().getY());
        brazer.setFill(new ImagePattern(new Image("/Media/Buildings/Castle/brazer/brazer.png")));
        GameMainPage.getMapPane().getChildren().add(brazer);
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
