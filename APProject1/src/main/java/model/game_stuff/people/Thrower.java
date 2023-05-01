package model.game_stuff.people;

import model.game_stuff.Government;
import model.game_stuff.HasHp;
import model.game_stuff.Person;
import model.game_stuff.people.enums.ThrowerTypes;

public class Thrower extends Person implements Fighter{
    private ThrowerTypes type;
    public Thrower(Government owner, ThrowerTypes type) {
        super(owner);
        this.type = type;
    }
    public void hit(HasHp livingBeing) {
        livingBeing.getDamaged(type.getDamage());
    }
}
