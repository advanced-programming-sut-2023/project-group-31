package model.game_stuff.buildings.enums;

import model.game_stuff.enums.Items;

public enum ProducerTypes {
    BREWERY(200,2,3,Items.ALE,3,"Brewery"),
    BAKERY(300,2,5,Items.BREAD,5,"Bakery"),
    WHEAT_FARM(100,1,2,Items.WHEAT,2,"Wheat farm"),
    HUNTING_POST(100,1,2,Items.MEAT,2,"Hunting post"),
    GRAIN_FARM(100,1,2,Items.HOPS,2,"Grain farm"),
    DIARY_PRODUCTS(100,1,2,Items.CHEESE,2,"Diary products"),
    APPLE_GARDEN(100,1,2,Items.APPLE,2,"Apple garden"),
    WOOD_CUTTER(100,1,5,Items.WOOD,5,"Wood cutter"),
    IRON_MINE(300,3,2,Items.IRON,2,"Iron mine"),
    QUARRY(500,3,2,Items.STONE,2,"Quarry"),
    BLACKSMITH(200,2,2,Items.SWORDS,2,"Black smith"),
    FLETCHER(200,2,2,Items.BOW,2,"Fletcher"),
    CROSSBOW_MAKER(200,2,2,Items.CROSSBOW,2,"Crossbow maker"),
    POLE_TURNER(200,2,2,Items.SPEAR,2,"PoleTurner"),
    ARMOURER(200,2,2,Items.METAL_ARMOR,2,"Armourer"),
    LEATHER_ARMOURER(200,2,2,Items.LEATHER_ARMOR,2,"Leather armourer"),
    STABLE(200,2,4,Items.HORSE,1,"Stable"),
    PIKE_MAKER(200,2,2,Items.PIKE,2,"Pike maker"),
    MACE_MAKER(200,2,2,Items.MACE,2,"Mace maker");
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
