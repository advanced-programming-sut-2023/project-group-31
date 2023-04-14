package model.game_stuff;

import model.User;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;

public class Troop extends Person{
    private User owner;
    private double hp;
    private double salary;
    public double range;
    public static Nationality nationality;

    private Troops troopType;


    public Troop(User owner,Troops type) {
            this.owner=owner;
            this.hp=type.getDefence();
    }

    public void attack(){

    }

    public void move(){

    }


}
