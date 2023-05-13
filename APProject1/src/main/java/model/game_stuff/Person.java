package model.game_stuff;

import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;


import java.util.LinkedList;

public abstract class Person implements HasHp{
    ///TODO: chiz hay invisible dar jadval zakhire nemishavand va serfan dar government zakhire mishan
    protected Government owner;
    protected String name;
    protected int hp;
    protected Block position;
    protected LinkedList<Direction> moveOrder;

    {
        moveOrder = new LinkedList<>();
    }

    public Person(Government owner) {
        this.owner = owner;
    }

    public void setMoveOrder(LinkedList<Direction> moveOrder) {
        this.moveOrder = moveOrder;
    }

    public LinkedList<Direction> getMoveOrder() {
        return moveOrder;
    }

    public void move() {
        if(moveOrder.isEmpty()) {
            return;
        }
        Block target;
        if((target = owner.getGame().getMap().getNeighbour(moveOrder.getFirst(), position)) == null) {
            moveOrder.clear();
            return;
        }
        if(!target.isPermeable()) {
            moveOrder.clear();
            return;
        }
        if(target.containsEnemyBuilding(owner.getColor()) || target.containsEnemyPerson(owner.getColor())) {
            return;
        }
        if(target.getBuilding().getOwner().equals(owner)) {
            if(target.getBuilding() instanceof Tower) {
                Tower tower = (Tower) target.getBuilding();
                if (moveOrder.size() != 1 || !tower.canEnter(moveOrder.getFirst()) || !tower.isFull()) {
                    moveOrder.clear();
                    return;
                }
            }
            moveOrder.clear();
            return;
        }
        move(target);
        moveOrder.removeFirst();
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void getDamaged(int damage) {
        this.hp -= damage;
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
    }
    abstract public String toString();

}
