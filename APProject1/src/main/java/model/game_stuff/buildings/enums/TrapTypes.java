package model.game_stuff.buildings.enums;

public enum TrapTypes {
    ;
    private int damage;
    private String name;
    TrapTypes (int damage, String name) {
        this.damage = damage;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
