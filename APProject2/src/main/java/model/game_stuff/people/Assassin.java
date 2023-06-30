package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.people.enums.TroopTypes;

public class Assassin extends Troop{
    public Assassin (Government owner) {
        super(owner, TroopTypes.ASSASSIN);
    }
    public void work() {
        switch (state) {
            case AGGRESSIVE:
                if(!attack()) {
                    move();
                }
                break;
            case DEFENCIVE:
                if(!move()) {
                    attack();
                }
        }
    }
    public boolean move() {
        boolean result = super.move();
        if(result) {
            position.removePerson(this);
        }
        return result;
    }

    public boolean attack() {
        boolean result = super.attack();
        if(result) {
            position.addPerson(this);
        }
        return result;
    }
}
