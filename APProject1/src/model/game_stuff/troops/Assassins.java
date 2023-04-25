package model.game_stuff.troops;

import model.User;
import model.game_stuff.Troop;
import model.game_stuff.types.Troops;

public class Assassins extends Troop {
    boolean haveBeenSeen;

    public Assassins(User owner, Troops type) {
        super(owner, type);
        this.haveBeenSeen = false;
    }

    public boolean isHaveBeenSeen() {
        return haveBeenSeen;
    }

    public void setHaveBeenSeen(boolean haveBeenSeen) {
        this.haveBeenSeen = haveBeenSeen;
    }

    public void move(){

    }
}
