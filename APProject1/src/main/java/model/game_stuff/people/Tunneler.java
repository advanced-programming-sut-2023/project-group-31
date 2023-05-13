package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.people.enums.TroopTypes;

public class Tunneler extends Person {
    TroopTypes type;
    public Tunneler(Government owner, TroopTypes type) {
        super(owner);
        this.type=type;
    }
    public static void digTunnel(Block block){
        block.getPeople().clear();
        block.getBuilding().terminate();
        block.getTrees().clear();
        block.setTunneled(true);
    }
}
