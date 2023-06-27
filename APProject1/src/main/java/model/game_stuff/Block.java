package model.game_stuff;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.game_stuff.buildings.Trap;
import model.game_stuff.enums.Textures;
import model.game_stuff.people.Troop;
import model.game_stuff.types.PersonType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Block {

    class ImagePathNumberNode {
        private String imagePath;
        private Integer number;
        private Node node;

        public ImagePathNumberNode(String imagePath, Integer number, Node node) {
            this.imagePath = imagePath;
            this.number = number;
            this.node = node;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public Integer getNumber() {
            return number;
        }

        public void changeNumber(Integer delta) {
            this.number += delta;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }
    }
    private Textures type;
    private ArrayList<Person> people;
    private ArrayList<Tree> trees;
    private Trap trap;
    private HashMap<PersonType, ImagePathNumberNode> numberOfEachPeople;
    private Building building;
    private boolean tunneled;
    private int x;
    private int y;

    {
        people = new ArrayList<>();
        trees = new ArrayList<>();
        numberOfEachPeople = new HashMap<>();
        building = null;
        tunneled = false;
        trap = null;
    }

    public Block(Textures type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isTunneled() {
        return tunneled;
    }

    public void setTunneled(boolean tunneled) {
        this.tunneled = tunneled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void addPerson(Person person) {
        people.add(person);
        if(numberOfEachPeople.containsKey(person.getPersonType())) {
            numberOfEachPeople.get(person.getPersonType()).changeNumber(1);
            return;
        }
        ImageView imageView = new ImageView(new Image(Block.class.getResource("/" + person.getImagePath()).toString(), 1d/5, 1d/5,false,false));
        Text text = new Text("1");
        numberOfEachPeople.put(person.getPersonType(), new ImagePathNumberNode(person.getImagePath(), 1,
            new Group(imageView, text)));
    }

    public void removePerson(Person person) {
        people.remove(person);
        numberOfEachPeople.get(person.getPersonType()).changeNumber(-1);
        if(numberOfEachPeople.get(person.getPersonType()).getNumber() == 0) {
            numberOfEachPeople.remove(person.getPersonType());
            //TODO shayad lazem bashe node ro az map hazf konim
        }
    }
    public String getImagePathOfAPersonType(PersonType personType) {
        return numberOfEachPeople.get(personType).getImagePath();
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

    public HashMap<PersonType, ImagePathNumberNode> getNumberOfEachPeople() {
        return numberOfEachPeople;
    }
    public int getNumberOfPeople() {
        return people.size();
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public boolean isEmpty() {
        return (trap == null &&building == null && people.isEmpty() && trees.isEmpty()&&tunneled==false);
    }
    public int getDistanceTo(Block anotherBlock) {
        return Math.abs(x - anotherBlock.getX()) + Math.abs(y - anotherBlock.getY());
    }
    public boolean containsEnemyTroop(Colors color) {
        for (Person person : people) {
            if((person instanceof Troop) && person.getOwner().getColor() != color)
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
        for (PersonType personType : numberOfEachPeople.keySet()) {
            output += "\t" + personType + ":" + numberOfEachPeople.get(personType).getNumber();
        }
        return output;
    }


    @Override
    public String toString() {
        String output = "(" + x + "," + y + ")" + "\t|\t" + type + "\t|\t";
        if(building == null) output += "empty";
        else output += building.getName();
        output += "\n people:";
        for (PersonType personType : numberOfEachPeople.keySet()) {
            output += "\t" + personType + ":" + numberOfEachPeople.get(personType).getNumber();
        }
        output += "\n trees:";
        for (Tree tree : trees) {
            output += "\t" + tree.getType().getName();
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

    @Override
    public Block clone(){
        Block block=new Block(this.getType(),this.getX(),this.getY());
        block.setTrees(new ArrayList<Tree>(this.getTrees()));
        return block;
    }

    public void collapse () {
        while(!people.isEmpty()) {
            people.get(0).die();
        }
        building.terminate();
        while(!trees.isEmpty()) {
            trees.get(0).terminate();
        }
    }
}
