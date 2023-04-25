package model.game_stuff.buildings.enums;

public enum GateHouseTypes {
    ;
    private int hp;
    private String name;

    GateHouseTypes(int hp, String name) {
        this.hp = hp;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}
