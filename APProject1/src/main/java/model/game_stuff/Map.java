package model.game_stuff;

import model.game_stuff.enums.Textures;

import java.util.ArrayList;

public class Map {
    private static ArrayList<Map> maps;
    private String name;
    private int length;
    private int width;
    private ArrayList<Block> lordHouses;
    //TODO: fire va stockpile ham payin va chapesh miran;
    private boolean saved;
    private ArrayList<ArrayList<Block>> blocks;
    private String description;

    {
        blocks = new ArrayList<>();
        saved = false;
    }

    public Map(String name, int length, int width) {
        this.name = name;
        this.length = length;
        this.width = width;
        for(int i = 0; i < width; i++) {
            blocks.add(new ArrayList<>());
            for(int j = 0; j < length; j++) {
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

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }


    public ArrayList<ArrayList<Block>> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "Map " + name +
                "\t\tlength=" + length +
                ", width=" + width +
                "\n\t" + description;
    }
}
