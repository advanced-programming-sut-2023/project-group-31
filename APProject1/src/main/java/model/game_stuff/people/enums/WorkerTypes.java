package model.game_stuff.people.enums;

import model.game_stuff.enums.Items;
import model.game_stuff.types.PersonType;

public enum WorkerTypes {
    BREWERY(50,2,3,Items.ALE,PersonType.BREWERY,6,2,"brewer"),
    BAKER(300,2,5,Items.BREAD,PersonType.BAKER,6,2,"baker"),
    WHEAT_FARMER(100,1,2,Items.WHEAT,PersonType.WHEAT_FARMER,6,2,"farmer"),
    HUNTER(100,1,2,Items.MEAT,PersonType.HUNTER,6,2,"hunter"),
    GRAIN_FARMER(100,1,2,Items.HOPS,PersonType.GRAIN_FARMER,6,2,"farmer"),
    DIARY_PRODUCER(100,1,2,Items.CHEESE,PersonType.DIARY_PRODUCER,6,2,"farmer"),
    APPLE_GARDENER(100,1,2,Items.APPLE,PersonType.APPLE_GARDENER,6,2,"farmer"),
    WOOD_CUTTER(100,1,5,Items.WOOD,PersonType.WOOD_CUTTER,6,2,"wood cutter"),
    IRON_MINER(300,3,2,Items.IRON,PersonType.IRON_MINER,4,2,"iron miner"),
    QUARRY_WORKER(500,3,2,Items.STONE,PersonType.QUARRY_WORKER,5,2,"quarry worker"),
    BLACK_SMITH(200,2,2,Items.SWORDS,PersonType.BLACK_SMITH,5,2,"black smith"),
    FLETCHER(200,2,2,Items.BOW,PersonType.FLETCHER,5,2,"fletcher"),
    CROSSBOW_MAKER(200,2,2,Items.CROSSBOW,PersonType.CROSSBOW_MAKER,5,2,"fletcher"),
    POLE_TURNER(200,2,2,Items.SPEAR,PersonType.POLE_TURNER,5,2,"pole turner"),
    ARMOURER(200,2,2,Items.METAL_ARMOR,PersonType.ARMOURER,5,2,"armourer"),
    LEATHER_ARMOURER(200,2,2,Items.LEATHER_ARMOR,PersonType.LEATHER_ARMOURER,5,2,"armourer"),
    STABLE_WORKER(200,2,4,Items.HORSE,PersonType.STABLE_WORKER,3,5,"miller"),
    PIKE_MAKER(200,2,2,Items.PIKE,PersonType.PIKE_MAKER,2,5,"pole turner"),
    MACE_MAKER(200,2,2,Items.MACE,PersonType.MACE_MAKER,2,5, "pole turner");
    ;
    private PersonType personType;
    private int hp;
    private int turnsToWait;
    private int amountOfProductToProduce;
    private Items product;
    private int speed;
    private int numberOfProductsToCarry;
    private String imagePath;

    WorkerTypes(int hp, int turnsToWait, int amountOfProductToProduce, Items product, PersonType personType, int speed, int numberOfProductsToCarry, String path) {
        this.personType = personType;
        this.hp = hp;
        this.turnsToWait = turnsToWait;
        this.amountOfProductToProduce = amountOfProductToProduce;
        this.product = product;
        this.speed = speed;
        this.numberOfProductsToCarry = numberOfProductsToCarry;
        imagePath = "Media/People/Workers/" + path;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getHp() {
        return hp;
    }

    public PersonType getPersonType() {
        return personType;
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
