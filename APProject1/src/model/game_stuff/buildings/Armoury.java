package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Weapons;

import java.util.ArrayList;

public class Armoury extends Building {
    private final double Hp=0;
    private final double Capacity=0;
    ArrayList<Weapons> weapons;

    public Armoury() {
        this.weapons = new ArrayList<Weapons>();
        hp=Hp;
    }

}
