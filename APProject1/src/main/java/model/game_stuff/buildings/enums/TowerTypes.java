package model.game_stuff.buildings.enums;

public enum TowerTypes {
    ;
    private String name;
    private int hp;
    private int fireRange;
    private int defendRange;

    TowerTypes(String name, int hp, int fireRange, int defendRange) {
        this.name = name;
        this.hp = hp;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }
}
