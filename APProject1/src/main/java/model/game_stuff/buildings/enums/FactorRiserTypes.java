package model.game_stuff.buildings.enums;

import model.game_stuff.types.Buildings;

public enum FactorRiserTypes {
    CATHEDRAL(Buildings.CATHEDRAL,1,2,2000),
    CHURCH(Buildings.CHURCH,1,2,1200);
    //TODO rast o rist kardan factor haye government
    private String name;
    private int turnsToWait;
    private int amountToRise;
    private String imagePath;
    private int hp;
    private Buildings baseBuildingType;

    FactorRiserTypes(Buildings building, int turnsToWait, int amountToRise, int hp) {
        this.turnsToWait = turnsToWait;
        this.amountToRise = amountToRise;
        this.hp = hp;
        this.name = building.getName();
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name;
        baseBuildingType = building;
    }

    public Buildings getBaseBuildingType() {
        return baseBuildingType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public int getTurnsToWait() {
        return turnsToWait;
    }


    public int getAmountToRise() {
        return amountToRise;
    }

    public int getHp() {
        return hp;
    }

    public static FactorRiserTypes getEnumByName(String name) {
        for (FactorRiserTypes factorRiserTypes : values()) {
            if(factorRiserTypes.name.equalsIgnoreCase(name)){
                return factorRiserTypes;
            }
        }
        return null;
    }
}
