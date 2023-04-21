package model.game_stuff.enums;

public enum Textures {
    GROUND(true),
    SAND(true),
    QUARRY(true),
    CLIFF(false),
    IRON(true),
    GROSS(true),
    MEADOW(true),
    GROSS_LAND(true),
    WATER(false)
    ;
    private boolean permeability;

    Textures(boolean permeability) {
        this.permeability = permeability;
    }

    public boolean isPermeable() {
        return permeability;
    }
}