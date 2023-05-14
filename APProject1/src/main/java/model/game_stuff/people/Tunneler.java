package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.enums.Direction;
import model.game_stuff.people.enums.TroopTypes;

public class Tunneler extends Troop {
    TroopTypes type;
    public Tunneler(Government owner, TroopTypes type) {
        super(owner, TroopTypes.TUNNELER);
        this.type=type;
    }
    public void digTunnel(Direction direction){
        Block toDig = owner.getGame().getMap().getNeighbour(direction, position);
        if(toDig.getBuilding() != null)
            toDig.collapse();
        toDig.setTunneled(true);
    }

    public void fillTunnel(Direction direction) {
        Block toFill = owner.getGame().getMap().getNeighbour(direction, position);
        if(toFill.isTunneled()) {
            position.setTunneled(false);
        }
    }
    @Override
    public void work() {

    }

    @Override
    public String toString() {
        return "engineering:" + (hp/ type.getHp());
    }
}
