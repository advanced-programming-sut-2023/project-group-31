package model.game_stuff;

import javafx.scene.image.ImageView;
import model.game_stuff.buildings.GateHouse;
import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;


import java.util.LinkedList;

public abstract class Person extends ImageView implements HasHp{
    //TODO : FILL PEOPLE WITH IMAGES
    protected Government owner;
    protected String name;
    protected int hp;
    protected int speed;
    protected Block position;
    protected ImagePackage imagePackage;
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

    public LinkedList<Direction> getMoveOrder() {
        return moveOrder;
    }

    public int getSpeed() {
        return speed;
    }

    public ImagePackage getImagePackage() {
        return imagePackage;
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
