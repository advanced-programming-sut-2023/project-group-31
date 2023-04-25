package model.game_stuff.buildings.enums;

public enum FactorRiserTypes {
    ;
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
}
