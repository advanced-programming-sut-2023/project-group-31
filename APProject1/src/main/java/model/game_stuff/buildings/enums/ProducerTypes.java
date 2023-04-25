package model.game_stuff.buildings.enums;

import model.game_stuff.Rate;
import model.game_stuff.enums.Items;

import java.util.Random;

public enum ProducerTypes {
    ;
    private String name;
    private int hp;
    private int turnsToWait;
    private int amountOfProductToProduce;
    private Items product;
    private int capacity;

    ProducerTypes(int hp, int turnsToWait, int amountOfProductToProduce, Items product, int capacity, String name) {
        this.name = name;
        this.hp = hp;
        this.turnsToWait = turnsToWait;
        this.amountOfProductToProduce = amountOfProductToProduce;
        this.product = product;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }
}
