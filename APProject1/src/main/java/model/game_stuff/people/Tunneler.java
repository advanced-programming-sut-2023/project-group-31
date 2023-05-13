package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.people.enums.TroopTypes;

public class Tunneler extends Troop {
    TroopTypes type;
    public Tunneler(Government owner, TroopTypes type) {
        super(owner, TroopTypes.TUNNELER);
        this.type=type;
    }
    public static void digTunnel(Block block){
        block.getPeople().clear();
        block.getBuilding().terminate();
        block.getTrees().clear();
        block.setTunneled(true);
    }
    @Override
    public void work() {

    }

    @Override
    public String toString() {
        return "engineering:" + (hp/ type.getHp());
    }
}
