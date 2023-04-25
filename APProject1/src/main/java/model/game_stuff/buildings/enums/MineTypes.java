package model.game_stuff.buildings.enums;

public enum MineTypes {
    ;
    private Good good;
    private String name;
    private double hp;
    private double rate;

    MineTypes (Good good, String name, double hp, double rate) {
        this.good = good;
        this.name = name;
        this.hp = hp;
        this.rate = rate;
    }
}
