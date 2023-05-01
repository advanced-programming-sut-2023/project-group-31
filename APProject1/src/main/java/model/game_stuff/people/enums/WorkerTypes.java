package model.game_stuff.people.enums;

import model.game_stuff.enums.Items;

public enum WorkerTypes {
    WOOD_CUTTER(10, 1, 3, Items.WOOD, "wood cutter", 3,2)
    ;
    private String name;
    private int hp;
    private int turnsToWait;
    private int amountOfProductToProduce;
    private Items product;
    private int speed;
    private int numberOfProductsToCarry;

    WorkerTypes(int hp, int turnsToWait, int amountOfProductToProduce, Items product, String name, int speed, int numberOfProductsToCarry) {
        this.name = name;
        this.hp = hp;
        this.turnsToWait = turnsToWait;
        this.amountOfProductToProduce = amountOfProductToProduce;
        this.product = product;
        this.speed = speed;
        this.numberOfProductsToCarry = numberOfProductsToCarry;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getTurnsToWait() {
        return turnsToWait;
    }

    public int getAmountOfProductToProduce() {
        return amountOfProductToProduce;
    }

    public Items getProduct() {
        return product;
    }

    public int getSpeed() {
        return speed;
    }

    public int getNumberOfProductsToCarry() {
        return numberOfProductsToCarry;
    }
}
