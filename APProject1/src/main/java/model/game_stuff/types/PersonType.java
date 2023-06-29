package model.game_stuff.types;

public enum PersonType {
    //TODO KASH image path ham hamin ja bood
    //TODO ezafe kardan chiz haye moshtarak bein troop o worker: imagePath, speed,
    LORD("lord", 1000, 3),
    ARCHER("archer",200,4),
    CROSSBOW_MAN("crossbow man",200,3),
    SLINGER("slinger",200,3),
    ARABIAN_BOW("arabian bow",200,4),
    HORSE_ARCHER("horse archer",400,4),
    FIRE_THROWER("fire thrower",300, 3),
    SPEAR_MAN("spear man", 200, 3),
    PIKE_MAN("pike man",300,2),
    MACE_MAN("mace man",300, 2),
    SWORDSMAN("swordsman", 400, 1),
    KNIGHT("knight", 500, 3),
    TUNNELER("tunneler", 150, 2),
    LADDER_MAN("ladder man", 100, 4),
    ENGINEER("engineer", 150, 2),
    BLACK_MONK("black monk",200, 2),
    SLAVE("slave", 100, 4),
    ASSASSIN("assassin", 200, 2),
    ARABIAN_SWORDSMAN("arabian swordsman", 400, 1),
    CATAPULT("catapult", 400, 1),

    BREWERY("brewery", 50,3),
    BAKER("baker",50, 3),
    WHEAT_FARMER("wheat farmer", 50, 3,"farmer"),
    HUNTER("hunter", 50, 3),
    GRAIN_FARMER("grain farmer", 50, 3,"farmer"),
    DIARY_PRODUCER("diary producer", 50, 3,"farmer"),
    APPLE_GARDENER("apple gardener", 50, 3,"farmer"),
    WOOD_CUTTER("wood cutter", 50, 3),
    IRON_MINER("iron miner", 50, 1),
    QUARRY_WORKER("quarry worker", 50 ,1),
    BLACK_SMITH("black smith", 50 ,2),
    FLETCHER("fletcher", 50 ,3),
    CROSSBOW_MAKER("crossbow maker", 50 , 3,"fletcher"),
    POLE_TURNER("pole turner", 50, 2),
    ARMOURER("armourer", 50 , 2),
    LEATHER_ARMOURER("leather armourer", 50 , 2,"armourer"),
    STABLE_WORKER("stable worker", 50 , 2,"miller"),
    PIKE_MAKER("pike maker", 50 , 2,"pole turner"),
    MACE_MAKER("mace maker", 50 , 2,"pole turner"),
    ;
    private String name;
    private int hp;
    private int speed;
    private String imagePath;

    PersonType(String name, int hp, int speed) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.imagePath = "/Media/People/" + name + ".png";
    }

    PersonType(String name, int hp, int speed, String imagePath) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.imagePath = "/Media/People/" + imagePath + "/.png";
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public String getImagePath() {
        return imagePath;
    }
}
