package model;

import java.util.ArrayList;

public class Map {
    private int length;
    private int width;
    private ArrayList<ArrayList<Block>> blocks;

    {
        blocks = new ArrayList<>();
    }

    public Map(int length, int width) {
        this.length = length;
        this.width = width;
        for(int i = 0; i < width; i++) {
            blocks.add(new ArrayList<>());
            for(int j = 0; j < length; j++) {
                blocks.get(i).add(new Block());
            }
        }
    }
}
