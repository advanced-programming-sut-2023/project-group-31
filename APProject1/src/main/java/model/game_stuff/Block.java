package model.game_stuff;


import javafx.scene.shape.Rectangle;
import model.game_stuff.buildings.Trap;
import model.game_stuff.enums.Textures;
import model.game_stuff.people.Troop;

import java.util.ArrayList;

public class Block {
    private Textures type;
    private Rectangle rectangle;
    //private ArrayList<Person> people;
    private Person person;
    private ArrayList<Tree> trees;
    private Trap trap;
    //private HashMap<String, Integer> numberOfEachPeople;
    private Building building;
    private boolean tunneled;
    private int x;
    private int y;

    {
        trees = new ArrayList<>();
        building = null;
        tunneled = false;
        trap = null;
    }

    public Block(Textures type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isTunneled() {
        return tunneled;
    }

    public void setTunneled(boolean tunneled) {
        this.tunneled = tunneled;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public Person getPerson() {
        return person;
    }

    public boolean setPerson(Person person) {
        if(this.person != null && person != null) {
            System.out.println("############### BE FANAA RAFTIM ###############");
            return false;
        }
        this.person = person;
        return true;
    }

    //Todo bayad baraye mercenary o ... yek array list az person besazim va be oon add konim

    public void addTree(Tree tree) {
        trees.add(tree);
    }
    public void removeTree(Tree tree) {
        trees.remove(tree);
    }

    public Textures getType() {
        return type;
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

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public boolean isEmpty() {
        return (trap == null && building == null && person == null && trees.isEmpty() && tunneled==false);
    }
    public int getDistanceTo(Block anotherBlock) {
        return Math.abs(x - anotherBlock.getX()) + Math.abs(y - anotherBlock.getY());
    }
    public boolean containsEnemyTroop(Colors color) {
        return person != null && (person instanceof Troop) && person.getOwner().getColor() != color;
    }
    public boolean containsEnemyBuilding(Colors color) {
        if(building != null && building.getOwner().getColor() != color)
            return true;
        return false;
    }

    public String getDetails(){
        String detail = type.getName();
        if(building != null) {
            detail += "\t" + building.getName();
        }
        if(person != null) {
            detail += "\t" + person.name;
        }
        return detail;
    }

    public String getMoreDetails(){
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        if(person == null) output += "\tno person";
        else output += "\t" + person.name;
        return output;
    }


    @Override
    public String toString() {
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        if(person == null) output += "\n no person";
        else output += "\n " + person.getName();
        output += "\n trees:";
        for (Tree tree : trees) {
            output += "\t" + tree.getType().getName();
        }
        return output;
    }

    public boolean containsEnemyPerson(Colors color) {
        return person != null && person.getOwner().getColor() != color;
    }
    public Boolean isPermeable() {
        return type.isPermeable();
    }

    @Override
    public Block clone(){
        Block block=new Block(this.getType(),this.getX(),this.getY());
        block.setTrees(new ArrayList<Tree>(this.getTrees()));
        return block;
    }

    public void collapse () {
        if(person != null) person.die();
        if(building != null) building.terminate();
        while(!trees.isEmpty()) {
            trees.get(0).terminate();
        }
    }
}
