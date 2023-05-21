package model.game_stuff;

import model.game_stuff.buildings.GateHouse;
import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;


import java.util.LinkedList;

public abstract class Person implements HasHp{
    protected Government owner;
    protected String name;
    protected int hp;
    protected int speed;
    protected Block position;
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

    public boolean move() {
        for(int i = 0; i < speed; i++) {
            //TODO: replace s outs
            System.out.println(name + " move step" + i + " " + position);
            if (moveOrder.isEmpty()) {
                System.out.println("1");
                return false;
            }
            Block target;
            if ((target = owner.getGame().getMap().getNeighbour(moveOrder.getFirst(), position)) == null) {
                moveOrder.clear();
                System.out.println("2");
                return false;
            }
            if (!target.isPermeable()) {
                moveOrder.clear();
                System.out.println("3");
                return false;
            }
            if (target.containsEnemyBuilding(owner.getColor()) || target.containsEnemyPerson(owner.getColor())) {
                System.out.println("4");
                return false;
            }
            if (target.getBuilding() != null)
                if (target.getBuilding().getOwner().equals(owner)) {
                    if (target.getBuilding() instanceof Tower) {
                        Tower tower = (Tower) target.getBuilding();
                        if (moveOrder.size() != 1 || !tower.canEnter(moveOrder.getFirst()) || !tower.isFull()) {
                            moveOrder.clear();
                            System.out.println("5");
                            return false;
                        }
                    }
                    if (target.getBuilding() instanceof GateHouse) {
                        if (!((GateHouse)target.getBuilding()).isOpen()) {
                            System.out.println("6");
                            return false;
                        }
                    }
                    moveOrder.clear();
                    System.out.println("7");
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
