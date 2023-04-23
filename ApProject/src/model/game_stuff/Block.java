package model.game_stuff;


import model.game_stuff.enums.Textures;

import java.util.ArrayList;

public class Block {
    private Textures type;
    private ArrayList<Person> people;
    private Building building;
    private int x;
    private int y;

    {
        people = new ArrayList<>();
        building = null;
    }

    public Block(Textures type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void addPerson(Person person) {
        people.add(person);
    }

    public void removePerson(Person person) {
        people.remove(person);
    }

    public Textures getType() {
        return type;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public Building getBuilding() {
        return building;
    }

    public void setType(Textures type) {
        this.type = type;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
    public boolean isEmpty() {
        return (building == null && people.isEmpty());
    }
}
