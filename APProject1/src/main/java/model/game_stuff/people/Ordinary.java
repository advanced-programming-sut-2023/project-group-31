package model.game_stuff.people;

import model.User;
import model.game_stuff.Troop;
import model.game_stuff.types.Troops;

public class Ordinary extends Troop {
    public Ordinary(User owner, Troops type) {
        super(owner, type);
    }
}
