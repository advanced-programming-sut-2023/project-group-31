package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.people.enums.TroopTypes;

public class HorseArchers extends Troop {
//    public HorseArchers(User owner, Troops type, double defence, double damage, double speed) {
//
//        super(owner, type);
//    }

    public HorseArchers(Government owner, TroopTypes type) {
        super(owner, type);
    }
}
