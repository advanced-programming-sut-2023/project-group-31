package model.game_stuff.buildings.enums;

public enum FactorRiserTypes {
    CATHEDRAL("Cathedral",1,2,2000),
    CHURCH("Church",1,2,1200);
    private String name;
    private int turnsToWait;
    private int amountToRise;
    private int hp;

    FactorRiserTypes(String name, int turnsToWait, int amountToRise, int hp) {
        this.turnsToWait = turnsToWait;
        this.amountToRise = amountToRise;
        this.hp = hp;
        this.name = name;
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
