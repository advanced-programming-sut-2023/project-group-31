package model.game_stuff;

import model.User;
import model.game_stuff.types.Persons;

public class Person {
    private User owner;
    private String name;
    private Persons type;
    private Building habitat;

    public String getName() {
        return name;
    }
}
