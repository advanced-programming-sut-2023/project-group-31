package model.game_stuff;

import model.game_stuff.enums.Textures;

import java.util.ArrayList;

public class Map {
    private static ArrayList<Map> maps;
    private String name;
    private int length;
    private int width;
    private ArrayList<Building> lordHouses;
    //TODO: fire va stockpile ham payin va chapesh miran;
    private boolean saved;
    private ArrayList<ArrayList<Block>> blocks;

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
                blocks.get(i).add(new Block(Textures.GROUND));
            }
        }
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
}
