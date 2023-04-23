package model.game_stuff;

public abstract class Building {
    protected double hp;
    protected String name;

    public Building() {
    }

    public void getDamaged(){

    }

    public double getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }
}
