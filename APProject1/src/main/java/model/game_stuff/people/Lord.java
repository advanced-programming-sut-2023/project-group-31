package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.people.enums.TroopTypes;

public class Lord extends Troop{
    public Lord(Government owner) {
        super(owner, TroopTypes.LORD);
    }

    public void die() {
        super.die();
        owner.terminate();
    }
}
