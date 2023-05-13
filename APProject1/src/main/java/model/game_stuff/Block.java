package model.game_stuff;


import model.game_stuff.buildings.Trap;
import model.game_stuff.enums.Textures;
import model.game_stuff.people.Fighter;

import java.util.ArrayList;
import java.util.HashMap;

public class Block {
    private Textures type;
    private ArrayList<Person> people;
    private ArrayList<Tree> trees;
    private Trap trap;
    private HashMap<String, Integer> numberOfEachPeople;
    private Building building;
    private int x;
    private int y;

    {
        people = new ArrayList<>();
        trees = new ArrayList<>();
        numberOfEachPeople = new HashMap<>();
        building = null;
        trap = null;
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
        if(numberOfEachPeople.containsKey(person.getName())) {
            numberOfEachPeople.replace(person.getName() , numberOfEachPeople.get(person.getName()) + 1);
            return;
        }
        numberOfEachPeople.put(person.getName(), 1);
    }

    public void removePerson(Person person) {
        people.remove(person);
        numberOfEachPeople.replace(person.getName() , numberOfEachPeople.get(person.getName()) -1);
        if(numberOfEachPeople.get(person.getName()) == 0) {
            numberOfEachPeople.remove(person.getName());
        }
    }
    public void addTree(Tree tree) {
        trees.add(tree);
    }
    public void removeTree(Tree tree) {
        trees.remove(tree);
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

    public HashMap<String, Integer> getNumberOfEachPeople() {
        return numberOfEachPeople;
    }
    public int getNumberOfPeople() {
        return people.size();
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
    public boolean isEmpty() {
        return (trap == null && building == null && people.isEmpty() && trees.isEmpty());
    }
    public int getDistanceTo(Block anotherBlock) {
        return Math.abs(x - anotherBlock.getX()) + Math.abs(y - anotherBlock.getY());
    }
    public boolean containsEnemyTroop(Colors color) {
        for (Person person : people) {
            if((person instanceof Fighter) && person.getOwner().getColor() != color)
                return true;
        }
        return false;
    }
    public boolean containsEnemyBuilding(Colors color) {
        if(building != null && building.getOwner().getColor() != color)
            return true;
        return false;
    }

    public String getDetails(){
        String detail="";
        for(Person person : people){
            detail+=person.toString();
            detail+="\n";
        }
        return detail;
    }

    public String getMoreDetails(){
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        output += "\t people:";
        for (String personName : numberOfEachPeople.keySet()) {
            output += "\t" + personName + ":" + numberOfEachPeople.get(personName);
        }
        for(Person person : people){
            output+=person.toString();
            output+="\t";
        }
        return output;
    }


    @Override
    public String toString() {
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        output += "\n people:";
        for (String personName : numberOfEachPeople.keySet()) {
            output += "\t" + personName + ":" + numberOfEachPeople.get(personName);
        }
        return output;
    }

    public boolean containsEnemyPerson(Colors color) {
        for (Person person : people) {
            if(person.getOwner().getColor() != color)
                return true;
        }
        return false;
    }
    public Boolean isPermeable() {
        return type.isPermeable();
    }
}
