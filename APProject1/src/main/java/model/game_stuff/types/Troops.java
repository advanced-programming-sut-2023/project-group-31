package model.game_stuff.types;

public enum Troops{
    ;
    private double damage;
    private double defence;
    private double speed;

    Troops(int damage, int defence, int speed) {
        this.damage = damage;
        this.defence = defence;
        this.speed = speed;
    }

    public double getDefence() {
        return this.defence;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }
}
