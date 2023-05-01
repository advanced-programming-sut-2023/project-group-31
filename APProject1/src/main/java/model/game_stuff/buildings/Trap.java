package model.game_stuff.buildings;

import model.game_stuff.Block;
import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.buildings.enums.TrapTypes;

public class Trap extends Building {
    private TrapTypes type;
    public Trap(TrapTypes type, Government government) {
        super(government);
        this.type = type;
    }
    public void work() {
        for (Block block : blocks) {
            for (Person person : block.getPeople()) {
                if(!person.getOwner().equals(owner)) {
                    // BOKOSHESH.
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Trap{" +
                "type=" + type +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", blocks=" + blocks +
                '}';
    }
}
