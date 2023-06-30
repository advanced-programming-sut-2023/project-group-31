package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.types.Buildings;

public enum GateHouseTypes {
    SMALL_STONE_GATEHOUSE(1000, Buildings.SMALL_STONE_GATEHOUSE),
    BIG_STONE_GATEHOUSE(2000, Buildings.BIG_STONE_GATEHOUSE),
    DRAWBRIDGE(1200, Buildings.DRAWBRIDGE),
    ;
    private int hp;
    private String name;
    private String imagePath;

    GateHouseTypes(int hp, Buildings building) {
        this.hp = hp;
        this.name = building.getName();
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name + "0";
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public static GateHouseTypes getEnumByName(String name) {
        for (GateHouseTypes gateHouseType : values()) {
            if(gateHouseType.name.equalsIgnoreCase(name)){
                return gateHouseType;
            }
        }
        return null;
    }
}
