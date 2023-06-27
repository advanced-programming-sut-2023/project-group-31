package model.game_stuff.people.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.enums.Items;

public enum WorkerTypes {
    BREWERY(50,2,3,Items.ALE,"brewer",3,2,"brewer"),
    BAKER(300,2,5,Items.BREAD,"baker",3,2,"baker"),
    WHEAT_FARMER(100,1,2,Items.WHEAT,"wheat farmer",3,2,"farmer"),
    HUNTER(100,1,2,Items.MEAT,"hunter",3,2,"hunter"),
    GRAIN_FARMER(100,1,2,Items.HOPS,"grain farmer",3,2,"farmer"),
    DIARY_PRODUCER(100,1,2,Items.CHEESE,"diary producer",3,2,"farmer"),
    APPLE_GARDENER(100,1,2,Items.APPLE,"apple gardener",3,2,"farmer"),
    WOOD_CUTTER(100,1,5,Items.WOOD,"wood cutter",3,2,"wood cutter"),
    IRON_MINER(300,3,2,Items.IRON,"iron miner",1,2,"iron miner"),
    QUARRY_WORKER(500,3,2,Items.STONE,"quarry worker",1,2,"quarry worker"),
    BLACKSMITH(200,2,2,Items.SWORDS,"black smith",2,2,"black smith"),
    FLETCHER(200,2,2,Items.BOW,"fletcher",2,2,"fletcher"),
    CROSSBOW_MAKER(200,2,2,Items.CROSSBOW,"crossbow maker",2,2,"fletcher"),
    POLE_TURNER(200,2,2,Items.SPEAR,"Pole Turner",2,2,"pole turner"),
    ARMOURER(200,2,2,Items.METAL_ARMOR,"armourer",2,2,"armourer"),
    LEATHER_ARMOURER(200,2,2,Items.LEATHER_ARMOR,"leather armourer",2,2,"armourer"),
    STABLE_WORKER(200,2,4,Items.HORSE,"stable worker",3,2,"miller"),
    PIKE_MAKER(200,2,2,Items.PIKE,"pike maker",2,2,"pole turner"),
    MACE_MAKER(200,2,2,Items.MACE,"mace maker",2,2, "pole turner");
    ;
    private String name;
    private int hp;
    private int turnsToWait;
    private int amountOfProductToProduce;
    private Items product;
    private int speed;
    private int numberOfProductsToCarry;
    private ImagePackage imagePackage;

    WorkerTypes(int hp, int turnsToWait, int amountOfProductToProduce, Items product, String name, int speed, int numberOfProductsToCarry, String path) {
        this.name = name;
        this.hp = hp;
        this.turnsToWait = turnsToWait;
        this.amountOfProductToProduce = amountOfProductToProduce;
        this.product = product;
        this.speed = speed;
        this.numberOfProductsToCarry = numberOfProductsToCarry;
        imagePackage = new ImagePackage("Media/People/Workers/" + path);
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

    public ImagePackage getImagePackage() {
        return imagePackage;
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
