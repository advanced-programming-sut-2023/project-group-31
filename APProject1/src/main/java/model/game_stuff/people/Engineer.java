package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.people.enums.TroopTypes;

public class Engineer extends Person {
    private TroopTypes troopTypes;
    public Engineer(Government owner,TroopTypes troopTypes) {
        super(owner);
        this.troopTypes=troopTypes;
    }

}
