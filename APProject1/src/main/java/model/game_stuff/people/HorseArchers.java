package model.game_stuff.people;

import model.User;
import model.game_stuff.Troop;
import model.game_stuff.types.Troops;

public class HorseArchers extends Troop {
    public HorseArchers(User owner, Troops type, double defence, double damage, double speed) {
        super(owner, type);
    }
}
