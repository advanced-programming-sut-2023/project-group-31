package model.game_stuff.types;

public enum PersonType {
    //TODO KASH image path ham hamin ja bood
    //TODO ezafe kardan chiz haye moshtarak bein troop o worker: imagePath, speed,
    LORD("lord"),
    ARCHER("archer"),
    CROSSBOW_MAN("crossbow man"),
    SLINGER("slinger"),
    ARABIAN_BOW("arabian bow"),
    HORSE_ARCHER("horse archer"),
    FIRE_THROWER("fire thrower"),
    SPEAR_MAN("spear man"),
    PIKE_MAN("pike man"),
    MACE_MAN("mace man"),
    SWORDSMAN("swordsman"),
    KNIGHT("knight"),
    TUNNELER("tunneler"),
    LADDER_MAN("ladder man"),
    ENGINEER("engineer"),
    BLACK_MONK("black monk"),
    SLAVE("slave"),
    ASSASSIN("assassin"),
    ARABIAN_SWORDSMAN("arabian swordsman"),
    CATAPULT("catapult"),

    BREWERY("brewery"),
    BAKER("baker"),
    WHEAT_FARMER("wheat farmer"),
    HUNTER("hunter"),
    GRAIN_FARMER("grain farmer"),
    DIARY_PRODUCER("diary producer"),
    APPLE_GARDENER("apple gardener"),
    WOOD_CUTTER("wood cutter"),
    IRON_MINER("iron miner"),
    QUARRY_WORKER("quarry worker"),
    BLACK_SMITH("black smith"),
    FLETCHER("fletcher"),
    CROSSBOW_MAKER("crossbow maker"),
    POLE_TURNER("pole turner"),
    ARMOURER("armourer"),
    LEATHER_ARMOURER("leather armourer"),
    STABLE_WORKER("stable worker"),
    PIKE_MAKER("pike maker"),
    MACE_MAKER("mace maker"),
    ;
    private String name;

    PersonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
