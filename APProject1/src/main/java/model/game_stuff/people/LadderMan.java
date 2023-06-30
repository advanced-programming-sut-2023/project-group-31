package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.people.enums.TroopTypes;

public class LadderMan extends Troop {
    public LadderMan(Government owner) {
        super(owner, TroopTypes.LADDER_MAN);
    }

    public void putLadder(){

    }
    @Override
    public void work() {

    }

    @Override
    public String toString() {
        return "engineering:" + (hp/ type.getHp());
    }
}
