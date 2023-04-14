package model.game_stuff;


import model.game_stuff.types.PermeabilityType;
import model.game_stuff.types.Textures;

import java.util.ArrayList;

public class Block {
    private Textures type;
    private PermeabilityType permeability;
    private ArrayList<Person> people;
    private ArrayList<Building> buildings;

    {
        people = new ArrayList<>();
        buildings = new ArrayList<>();
    }

    public Block(Textures type) {
        this.type = type;
    }
}
