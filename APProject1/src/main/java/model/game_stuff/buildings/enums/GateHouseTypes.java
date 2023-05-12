package model.game_stuff.buildings.enums;

public enum GateHouseTypes {
    SMALL_STONE_GATEHOUSE(1000,"Small stone gatehouse"),
    BIG_STONE_GATEHOUSE(2000,"Big stone gatehouse"),
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
