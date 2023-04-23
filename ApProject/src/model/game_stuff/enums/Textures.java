package model.game_stuff.enums;

public enum Textures {
    GROUND(true,"ground"),
    SAND(true,"sand"),
    QUARRY(true,"quarry"),
    CLIFF(false,"cliff"),
    IRON(true, "iron"),
    GROSS(true, "gross"),
    MEADOW(true, "meadow"),
    GROSS_LAND(true, "gross land"),
    WATER(false, "water")
    ;
    private boolean permeability;
    private String name;

    Textures(boolean permeability, String name) {
        this.permeability = permeability;
        this.name = name;
    }

    public boolean isPermeable() {
        return permeability;
    }

    public String getName() {
        return name;
    }
    public static Textures getTextureByName(String name) {
        for (Textures texture : values()) {
            if(texture.getName().equals(name)) {
                return texture;
            }
        }
        return null;
    }
}