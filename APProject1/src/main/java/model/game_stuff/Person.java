package model.game_stuff;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.game_stuff.buildings.GateHouse;
import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;
import model.game_stuff.types.PersonType;
import view.game_system.GameMainPage;


import java.util.LinkedList;

public abstract class Person implements HasHp{
    protected Government owner;
    protected String name;
    protected PersonType personType;
    protected int hp;
    protected Rectangle rectangle;
    protected int speed;
    protected Block position;
    protected String imagePath;
    //TODO: set image packages for people
    protected LinkedList<Direction> moveOrder;

    {
        moveOrder = new LinkedList<>();
    }

    public Person(Government owner) {
        this.owner = owner;
    }

    public void setMoveOrder(LinkedList<Direction> moveOrder) {
        this.moveOrder = new LinkedList<>(moveOrder);
    }

    public void setRectangle() {
        rectangle = new Rectangle();
        rectangle.setHeight(1);
        rectangle.setWidth(1);
        rectangle.setX(this.getPosition().getX());
        rectangle.setY(this.getPosition().getY());
        rectangle.setFill(new ImagePattern(new Image(this.getImagePath() + ".png")));
        GameMainPage.getMapPane().getChildren().add(rectangle);

        Tooltip tooltip = new Tooltip(toString());
        Tooltip.install(rectangle, tooltip);
    }

    public LinkedList<Direction> getMoveOrder() {
        return moveOrder;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean move() {
        for(int i = 0; i < speed; i++) {
            if (moveOrder.isEmpty()) {
                return false;
            }
            Block target;
            if ((target = owner.getGame().getMap().getNeighbour(moveOrder.getFirst(), position)) == null) {
                moveOrder.clear();
                return false;
            }
            if (!target.isPermeable()) {
                moveOrder.clear();
                return false;
            }
            if (target.containsEnemyBuilding(owner.getColor()) || target.containsEnemyPerson(owner.getColor())) {
                return false;
            }
            if (target.getBuilding() != null)
                if (target.getBuilding().getOwner().equals(owner)) {
                    if (target.getBuilding() instanceof Tower) {
                        Tower tower = (Tower) target.getBuilding();
                        if (moveOrder.size() != 1 || !tower.canEnter(moveOrder.getFirst()) || !tower.isFull()) {
                            moveOrder.clear();
                            return false;
                        }
                    }
                    if (target.getBuilding() instanceof GateHouse) {
                        if (!((GateHouse)target.getBuilding()).isOpen()) {
                            return false;
                        }
                    }
                    moveOrder.clear();
                    return false;
                }
            move(target);
            moveOrder.removeFirst();
        }
        return true;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void getDamaged(int damage) {
        this.hp -= damage;
        if(hp < 0) {
            die();
        }
    }

    public int getHp() {
        return hp;
    }

    public Government getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Block getPosition() {
        return position;
    }

    public void setPosition(Block position) {
        this.position = position;
    }
    public void move(Block destination) {
        position.removePerson(this);
        destination.addPerson(this);
        position = destination;
    }
    public abstract void work();
    abstract public String toString();

    public abstract void die();
}
