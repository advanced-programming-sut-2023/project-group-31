package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.types.Buildings;

public enum FactorRiserTypes {
    CATHEDRAL(Buildings.CATHEDRAL,1,2,2000),
    CHURCH(Buildings.CHURCH,1,2,1200);
    //TODO rast o rist kardan factor haye government
    private String name;
    private int turnsToWait;
    private int amountToRise;
    private ImagePackage imagePackage;
    private Buildings buildingCost;
    private int hp;

    FactorRiserTypes(Buildings building, int turnsToWait, int amountToRise, int hp) {
        this.turnsToWait = turnsToWait;
        this.amountToRise = amountToRise;
        this.hp = hp;
        this.name = building.getName();
        buildingCost = building;
        imagePackage = new ImagePackage("Media/Buildings/" + building.getCategory() + "/" + name + "/" + name);
    }

    public ImagePackage getImagePackage() {
        return imagePackage;
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
