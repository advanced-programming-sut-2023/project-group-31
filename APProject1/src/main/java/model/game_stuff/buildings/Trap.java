package model.game_stuff.buildings;

import model.game_stuff.*;
import model.game_stuff.buildings.enums.TrapTypes;

public class Trap extends Building implements Working {
    private TrapTypes type;
    public Trap(TrapTypes type, Government government) {
        super(government);
        this.type = type;
        owner.addBuilding(this);
        owner.getBuildings().add(this);
        name = type.getName();
        imagePath = type.getImagePath();
        baseBuildingType = type.getBaseBuildingType();
    }
    public void work() {
        super.work();
        if(getPosition().containsEnemyPerson(owner.getColor())) {
            for (Block block : blocks) {
                block.getPerson().getDamaged(type.getDamage());
            }
            terminate();
        }
    }

    @Override
    public String toString() {
        return type.getName();
    }
}
