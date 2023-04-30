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

    @Override
    public String toString() {
        return name;
    }
}
