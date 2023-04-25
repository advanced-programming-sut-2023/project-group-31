package model.game_stuff.buildings.enums;

public enum FarmType {
    ;
    private Food food;
    private String name;
    private double hp;
    private double rate;

    FarmType(Food food, String name, double hp, double rate) {
        this.food = food;
        this.name = name;
        this.hp = hp;
        this.rate = rate;
    }
}
