package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.enums.BuildingCategories;
import model.game_stuff.types.Buildings;

public enum TrapTypes {
    CAGED_WAR_DOGS(100,Buildings.CAGED_WAR_DOGS),
    OIL_SMELTER(200,Buildings.OIL_SMELTER),
    PITCH_DITCH(500,Buildings.PITCH_DITCH),
    KILLING_PIT(150,Buildings.KILLING_PIT);
    private int damage;
    private String name;
    private ImagePackage imagePackage;
    private Buildings buildingCosts;
    TrapTypes (int damage, Buildings building) {
        this.damage = damage;
        this.buildingCosts = building;
        this.name = building.getName();
        imagePackage = new ImagePackage("Media/Buildings/" + buildingCosts.getCategory() + "/" + name + "/" + name);
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

    public ImagePackage getImagePackage() {
        return imagePackage;
    }
}
