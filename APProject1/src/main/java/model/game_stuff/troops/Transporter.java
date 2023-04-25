package model.game_stuff.troops;

import model.game_stuff.Block;
import model.game_stuff.Building;
import model.game_stuff.Person;
import model.game_stuff.enums.Items;

public class Transporter extends Person {
    private Building workHouse;
    //tabe add product ro dar government mi sazim
    private Items product;
    private Block destination;

    public Transporter(Building workHouse, Items product) {
        this.workHouse = workHouse;
        this.product = product;
    }
}
