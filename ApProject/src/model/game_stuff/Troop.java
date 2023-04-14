package model.game_stuff;

import model.User;
import model.game_stuff.types.Troops;

public class Troop extends Person{
    private double hp;
    private double salary;
    public double range;

    private Troops troopType;
    public Troop(User owner,Troops type, double defence, double damage, double speed) {
            this.hp=type.getDefence();
    }

    public void attack(){

    }



}
