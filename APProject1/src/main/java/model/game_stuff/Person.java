package model.game_stuff;

import model.User;
import model.game_stuff.types.Persons;

public abstract class Person implements HasHp{
    ///TODO: chiz hay invisible dar jadval zakhire nemishavand va serfan dar government zakhire mishan
    protected Government owner;
    protected String name;
    protected int hp;
    protected Block position;

    public Person(Government owner) {
        this.owner = owner;
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
}
