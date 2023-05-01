package model.game_stuff;


import model.game_stuff.enums.Items;
import model.game_stuff.enums.Textures;

import java.util.ArrayList;
import java.util.HashMap;

public class Block {
    private Textures type;
    private ArrayList<Person> people;
    private HashMap<String, Integer> numberOfPeople;
    private Building building;
    private int x;
    private int y;

    {
        people = new ArrayList<>();
        numberOfPeople = new HashMap<>();
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
        if(numberOfPeople.containsKey(person.getName())) {
            numberOfPeople.replace(person.getName() ,numberOfPeople.get(person.getName()) + 1);
            return;
        }
        numberOfPeople.put(person.getName(), 1);
    }

    public void removePerson(Person person) {
        people.remove(person);
        numberOfPeople.replace(person.getName() ,numberOfPeople.get(person.getName()) -1);
        if(numberOfPeople.get(person.getName()) == 0) {
            numberOfPeople.remove(person.getName());
        }
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
    public int getDistanceTo(Block anotherBlock) {
        return Math.abs(x - anotherBlock.getX()) + Math.abs(y - anotherBlock.getY());
    }

    @Override
    public String toString() {
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        output += "\n people:";
        for (String personName : numberOfPeople.keySet()) {
            output += "\t" + personName + ":" + numberOfPeople.get(personName);
        }
        return output;
    }
}
