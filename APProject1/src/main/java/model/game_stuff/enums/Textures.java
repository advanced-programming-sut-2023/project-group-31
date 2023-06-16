package model.game_stuff.enums;


import view.viewStyle.Colors;

public enum Textures {
    GROUND(true, "ground", Colors.YELLOW),
    SAND(true, "sand", Colors.YELLOW),
    QUARRY(true, "quarry", Colors.YELLOW),
    CLIFF(false, "cliff", Colors.RED),
    IRON(true, "iron", Colors.WHITE),
    GROSS(true, "gross", Colors.GREEN),
    MEADOW(true, "meadow", Colors.GREEN),
    GROSS_LAND(true, "gross land", Colors.GREEN),
    WATER(false, "water", Colors.BLUE);
    private boolean permeability;
    private String name;

    private Colors color;


    Textures(boolean permeability, String name, Colors color) {
        this.permeability = permeability;
        this.name = name;
        this.color = color;
    }

    public boolean isPermeable() {
        return permeability;
    }

    public String getName() {
        return name;
    }

    public Colors getColor() {
        return color;
    }

    public static Textures getTextureByName(String name) {
        for (Textures texture : values()) {
            if (texture.getName().equals(name)) {
                return texture;
            }
        }
        return null;
    }
}