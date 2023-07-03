package model.game_stuff.buildings.enums;

import model.game_stuff.types.Buildings;

public enum TrapTypes {
    CAGED_WAR_DOGS(100,Buildings.CAGED_WAR_DOGS),
    OIL_SMELTER(200,Buildings.OIL_SMELTER),
    PITCH_DITCH(500,Buildings.PITCH_DITCH),
    KILLING_PIT(150,Buildings.KILLING_PIT);
    private int damage;
    private String name;
    private Buildings baseBuildingType;
    private String imagePath;
    TrapTypes (int damage, Buildings building) {
        this.damage = damage;
        this.name = building.getName();
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name;
        baseBuildingType = building;
    }

    public Buildings getBaseBuildingType() {
        return baseBuildingType;
    }

    public String getName() {
        return name;
    }

    public static TrapTypes getEnumByName(String name) {
        for (TrapTypes trapTypes : values()) {
            if(trapTypes.name.equalsIgnoreCase(name)){
                return trapTypes;
            }
        }
        return null;
    }

    public int getDamage() {
        return damage;
    }

    public String getImagePath() {
        return imagePath;
    }
}
