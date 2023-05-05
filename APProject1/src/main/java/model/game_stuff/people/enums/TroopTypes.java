package model.game_stuff.people.enums;

public enum TroopTypes {
    ;
    private int hp;
    private String name;
    private int damage;
    private int fightingRange;
    private int visionRange;

    TroopTypes(int hp, String name, int damage, int fightingRange, int visionRange) {
        this.hp = hp;
        this.name = name;
        this.damage = damage;
        this.fightingRange = fightingRange;
        this.visionRange = visionRange;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getFightingRange() {
        return fightingRange;
    }

    public int getVisionRange() {
        return visionRange;
    }
}
