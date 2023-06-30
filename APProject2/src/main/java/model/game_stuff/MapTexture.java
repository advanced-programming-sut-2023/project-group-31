package model.game_stuff;

import model.game_stuff.enums.Textures;

import java.util.ArrayList;
import java.util.SortedSet;

public class MapTexture {
    private String name;
    private String description;
    private ArrayList<ArrayList<Textures>> textures;

    public MapTexture(String name , ArrayList<ArrayList<Block>> map , String description) {
        this.name = name;
        for (int i = 0; i < map.size(); i++) {
            textures.add(new ArrayList<>());
            for (int j = 0; j < map.size(); j++) {
                textures.get(i).add(map.get(i).get(j).getType());
            }
        }
        this.description = description;
    }

    public Map getMap(){
        Map map = new Map(name,textures.size());
        for (int i = 0; i < textures.size(); i++) {
            for (int j = 0; j < textures.size(); j++) {
                map.getBlocks().get(i).get(j).setType(textures.get(i).get(j));
            }
        }
        map.setDescription(description);
        return map;
    }

}
