package model.game_stuff.enums;

public enum Direction {
    UP(0, 1, "up"),
    DOWN(0, -1, "down"),
    RIGHT(1,0, "right"),
    LEFT(-1, 0, "left"),
    ;
    private int x, y;
    private String name;

    Direction(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
