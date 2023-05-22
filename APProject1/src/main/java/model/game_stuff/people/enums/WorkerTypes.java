package model.game_stuff.people.enums;

import model.game_stuff.enums.Items;

public enum WorkerTypes {
    BREWERY(50,2,3,Items.ALE,"brewery_worker",3,2),
    BAKER(300,2,5,Items.BREAD,"baker",3,2),
    WHEAT_FARMER(100,1,2,Items.WHEAT,"Wheat farm",3,2),
    HUNTER(100,1,2,Items.MEAT,"Hunting post",3,2),
    GRAIN_FARMER(100,1,2,Items.HOPS,"Grain farm",3,2),
    DIARY_PRODUCER(100,1,2,Items.CHEESE,"Diary products",3,2),
    APPLE_GARDENER(100,1,2,Items.APPLE,"Apple garden",3,2),
    WOOD_CUTTER(100,1,5,Items.WOOD,"Wood cutter",3,2),
    IRON_MINER(300,3,2,Items.IRON,"Iron mine",1,2),
    QUARRY_WORKER(500,3,2,Items.STONE,"Quarry",1,2),
    BLACKSMITH(200,2,2,Items.SWORDS,"Black smith",2,2),
    FLETCHER(200,2,2,Items.BOW,"Fletcher",2,2),
    CROSSBOW_MAKER(200,2,2,Items.CROSSBOW,"Crossbow maker",2,2),
    POLE_TURNER(200,2,2,Items.SPEAR,"PoleTurner",2,2),
    ARMOURER(200,2,2,Items.METAL_ARMOR,"Armourer",2,2),
    LEATHER_ARMOURER(200,2,2,Items.LEATHER_ARMOR,"Leather armourer",2,2),
    STABLE_WORKER(200,2,4,Items.HORSE,"Stable",3,2),
    PIKE_MAKER(200,2,2,Items.PIKE,"Pike maker",2,2),
    MACE_MAKER(200,2,2,Items.MACE,"Mace maker",2,2);
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
    WorkerTypes(int hp, int turnsToWait, int amountOfProductToProduce, Items product, String name, int speed) {
        this.name = name;
        this.hp = hp;
        this.turnsToWait = turnsToWait;
        this.amountOfProductToProduce = amountOfProductToProduce;
        this.product = product;
        this.speed = speed;
        this.numberOfProductsToCarry = amountOfProductToProduce;
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
