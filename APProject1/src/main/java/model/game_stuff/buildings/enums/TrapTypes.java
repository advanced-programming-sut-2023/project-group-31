package model.game_stuff.buildings.enums;

public enum TrapTypes {
    CAGE_WAR_DOGS(100,"Cage war dogs"),
    OIL_SMELTER(200,"Oil smelter"),
    PITCH_DITCH(500,"Pitch ditch"),
    KILLING_PIT(150,"Killing pit");
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
