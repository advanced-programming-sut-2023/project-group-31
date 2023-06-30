package model.game_stuff.buildings;

import model.game_stuff.*;
import model.game_stuff.buildings.enums.TrapTypes;

public class Trap extends Building implements Working {
    private TrapTypes type;
    public Trap(TrapTypes type, Government government) {
        super(government);
        this.type = type;
        owner.addBuilding(this);
        owner.getWorkingsBuildings().add(this);
        name = type.getName();
        imagePath = type.getImagePath();
    }
    public void work() {
        if(getPosition().containsEnemyPerson(owner.getColor())) {
            for (Block block : blocks) {
                for (Person person : block.getPeople()) {
                    person.getDamaged(type.getDamage());
                }
            }
            terminate();
        }
    }

    @Override
    public String toString() {
        return type.getName();
    }
}
