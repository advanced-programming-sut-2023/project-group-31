package model.game_stuff;

import model.game_stuff.enums.Direction;
import model.game_stuff.enums.Textures;
import model.game_stuff.enums.TreeTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private static ArrayList<Map> maps;
    private String name;
    private int size;
    private ArrayList<Tree> trees;
    private ArrayList<Block> lordHouses;
    //TODO: fire va stockpile ham payin va chapesh miran;
    private boolean saved;
    private ArrayList<ArrayList<Block>> blocks;
    private String description;

    {
        blocks = new ArrayList<>();
        saved = false;
    }

    public Map(String name, int size) {
        this.name = name;
        this.size = size;
        for(int i = 0; i < size; i++) {
            blocks.add(new ArrayList<>());
            for(int j = 0; j < size; j++) {
                blocks.get(i).add(new Block(Textures.GROUND, j, i));
            }
        }
    }

    public ArrayList<Block> getLordHouses() {
        return lordHouses;
    }
    public void addLordHouse(Block block) {
        lordHouses.add(block);
    }
    public void removeLordHouse(Block block) {
        lordHouses.remove(block);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Block getBlock(int x, int y) {
        return blocks.get(x).get(y);
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Map> getMaps() {
        return maps;
    }

    public int getSize() {
        return size;
    }
    public int getLength() {
        return size;
    }
    public int getWidth() {
        return size;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public ArrayList<ArrayList<Block>> getBlocks() {
        return blocks;
    }
    public Block getNeighbour(Direction direction, Block block) {
        int x = block.getX() + direction.getX();
        int y = block.getY() + direction.getY();
        if(!isInMap(x, y)){
            return null;
        }
        return getBlock(x, y);
    }
    public HashMap<Direction, Block> getNeighbours(Block block) {
        HashMap<Direction, Block> neighbours = new HashMap<>();
        Block neighbour;
        for (Direction direction : Direction.values()) {
            if((neighbour = getNeighbour(direction, block)) != null) {
                neighbours.put(direction, neighbour);
            }
        }
        return neighbours;
    }
    public ArrayList<Block> getNeighboursWithDistance(Block block, int distance) {
        ArrayList<Block> neighbours = new ArrayList<>();
        Block block1;
        int x, y;
        for(int i = 0; i < distance; i++) {
            int j = distance - i;
            if (isInMap((x = block.getX() + i), (y = block.getY() + j)) && !neighbours.contains((block1 = getBlock(x, y)))) {
                neighbours.add(block1);
            }
            if (isInMap((x = block.getX() + i), (y = block.getY() - j)) && !neighbours.contains((block1 = getBlock(x, y)))) {
                neighbours.add(block1);
            }
            if (isInMap((x = block.getX() - i), (y = block.getY() + j)) && !neighbours.contains((block1 = getBlock(x, y)))) {
                neighbours.add(block1);
            }
            if (isInMap((x = block.getX() - i), (y = block.getY() - j)) && !neighbours.contains((block1 = getBlock(x, y)))) {
                neighbours.add(block1);
            }
        }
        return neighbours;
    }
    public boolean isInMap(int x, int y) {
        return x >= 0 &&  y >= 0 && x < size && y < size;
    }

    public static void setMaps(ArrayList<Map> maps) {
        Map.maps = maps;
    }

    public static void setDefaultMaps(){
        Map newMap=new Map("Default map",150);
        for(int i=1;i<20;i++){
            for(int j=1;j<30;j++){
                newMap.getBlock(i,j).setType(Textures.GROSS);
                Tree tree= new Tree(newMap.getBlock(i,j), TreeTypes.OLIVE);
            }

        }

        for(int i=80;i<100;i++){
            for(int j=100;j<150;j++){
                newMap.getBlock(i,j).setType(Textures.GROSS);
                Tree tree= new Tree(newMap.getBlock(i,j), TreeTypes.DATE);
            }
        }

        for(int i=50;i<150;i++){
            for(int j=120;j<150;j++){
                newMap.getBlock(i,j).setType(Textures.WATER);
            }
        }

        for(int i=40;i<100;i++){
            for(int j=1;j<20;j++){
                newMap.getBlock(i,j).setType(Textures.WATER);
            }
        }

    }

    @Override
    public String toString() {
        return "Map " + name +
                "\t\t" + size +
                " * " + size +
                "\n\t" + description;
    }
}
