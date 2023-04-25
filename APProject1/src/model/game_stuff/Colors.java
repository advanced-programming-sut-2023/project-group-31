package model.game_stuff;

public enum Colors {
    ;
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
