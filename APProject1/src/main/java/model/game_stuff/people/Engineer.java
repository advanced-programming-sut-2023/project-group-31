package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.people.enums.TroopTypes;

public class Engineer extends Troop {
    private TroopTypes troopTypes;
    public Engineer(Government owner,TroopTypes troopTypes) {
        super(owner, TroopTypes.ENGINEER);
        this.troopTypes=troopTypes;
    }

    @Override
    public void work() {

    }

    @Override
    public String toString() {
        return "engineering:" + (hp/ type.getHp());
    }
}
