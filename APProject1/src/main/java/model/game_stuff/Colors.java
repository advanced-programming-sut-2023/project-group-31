package model.game_stuff;

public enum Colors {
    RED("red"),
    GREEN("green"),
    BLUE("blue"),

    PINK("pink"),
    BLACK("black"),
    YELLOW("yellow"),

    LIGHT_BROWN("light brown"),
    WHITE("white");
    private String name;


    Colors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Colors getColorByName(String name) {
        for (Colors color : values()) {
            if(color.getName().equals(name))
                return color;
        }
        return null;
    }
    @Override
    public String toString() {
        return name;
    }
}
