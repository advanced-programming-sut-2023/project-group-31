package model.game_stuff.buildings.enums;

public class WeaponryTypes {
    ;
    private Weapons weapon;
    private String name;
    private double hp;
    private double rate;

    WeaponryTypes (Weapons weapon, String name, double hp, double rate) {
        this.weapon = weapon;
        this.name = name;
        this.hp = hp;
        this.rate = rate;
    }
}
