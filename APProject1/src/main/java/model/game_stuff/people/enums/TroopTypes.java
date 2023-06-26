package model.game_stuff.people.enums;

import model.game_stuff.ImagePackage;

public enum TroopTypes {

    LORD("lord", 3000,200,3,1,3),
    ARCHER("archer",300,2250,4,4,6),
    CROSSBOW_MAN("crossbow man",300,300,2,4,6),
    SLINGER("slinger",300,300,2,4,6),
    ARABIAN_BOW("arabian Bow",300,2250,4,4,6),
    HORSE_ARCHER("horse archer",300,3000,5,4,6),
    FIRE_THROWER("fire thrower",500,2250,5,4,6),
    SPEAR_MAN("spear man",400,1500,3,1,2),
    PIKE_MAN("pike man",400,3750,2,1,2),
    MACE_MAN("mace man",500,3000,3,1,2),
    SWORDSMAN("swordsman",600,1500,1,1,2),
    KNIGHT("knight",600,3750,5,1,2),
    TUNNELER("tunneler",400,0,4,1,2),
    LADDER_MAN("ladder man",400,0,4,1,2),
    ENGINEER("engineer",400,0,3,1,2),
    BLACK_MONK("black monk",400,3000,2,1,2),
    SLAVE("slave",200,750,4,1,2),
    ASSASSIN("assassin",400,3000,3,1,2),
    ARABIAN_SWORDSMAN("arabian swordsman",500,3750,5,1,2),
    CATAPULT("catapult",1000,3000,2,4,6);

    private int hp;
    private String name;
    private int damage;
    private int fightingRange;
    private int visionRange;
    private int speed;
    private ImagePackage imagePackage;

    TroopTypes(String name,int hp, int damage, int speed,int fightingRange, int visionRange) {
        this.hp = hp;
        this.name = name;
        this.speed=speed;
        this.damage = damage;
        this.fightingRange = fightingRange;
        this.visionRange = visionRange;
        imagePackage = new ImagePackage("Media/People/Troops/" + name);
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getFightingRange() {
        return fightingRange;
    }

    public int getVisionRange() {
        return visionRange;
    }

    public ImagePackage getImagePackage() {
        return imagePackage;
    }

    public int getSpeed() {
        return speed;
    }
    public static TroopTypes getTroopByName(String TroopName){
        for (TroopTypes value : TroopTypes.values()) {
            if(value.getName().equals(TroopName)){
                return value;
            }
        }
        return null;
    }
}
