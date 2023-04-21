package model.game_stuff;


import model.game_stuff.enums.Textures;

import java.util.ArrayList;

public class Block {
    private Textures type;
    private ArrayList<Person> people;
    private Building building;

    {
        people = new ArrayList<>();
        building = null;
    }

    public Block(Textures type) {
        this.type = type;
    }

    public boolean isEmpty() {
        if(building == null) return true;
        return false;
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


}
