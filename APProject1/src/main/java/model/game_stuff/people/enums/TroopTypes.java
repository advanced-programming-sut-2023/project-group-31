package model.game_stuff.people.enums;

import model.game_stuff.types.PersonType;

public enum TroopTypes {

    LORD(PersonType.LORD, 3000,200,3,1,3),
    ARCHER(PersonType.ARCHER,300,2250,4,4,6),
    CROSSBOW_MAN(PersonType.CROSSBOW_MAN,300,300,2,4,6),
    SLINGER(PersonType.SLINGER,300,300,2,4,6),
    ARABIAN_BOW(PersonType.ARABIAN_BOW,300,2250,4,4,6),
    HORSE_ARCHER(PersonType.HORSE_ARCHER,300,3000,5,4,6),
    FIRE_THROWER(PersonType.FIRE_THROWER,500,2250,5,4,6, true),
    SPEAR_MAN(PersonType.SPEAR_MAN,400,1500,3,1,2),
    PIKE_MAN(PersonType.PIKE_MAN,400,3750,2,1,2),
    MACE_MAN(PersonType.MACE_MAN,500,3000,3,1,2),
    SWORDSMAN(PersonType.SWORDSMAN,600,1500,1,1,2),
    KNIGHT(PersonType.KNIGHT,600,3750,5,1,2),
    TUNNELER(PersonType.TUNNELER,400,0,4,1,2),
    LADDER_MAN(PersonType.LADDER_MAN,400,0,4,1,2),
    ENGINEER(PersonType.ENGINEER,400,0,3,1,2),
    BLACK_MONK(PersonType.BLACK_MONK,400,3000,2,1,2),
    SLAVE(PersonType.SLAVE,200,750,4,1,2, true),
    ASSASSIN(PersonType.ASSASSIN,400,3000,3,1,2),
    ARABIAN_SWORDSMAN(PersonType.ARABIAN_SWORDSMAN,500,3750,5,1,2),
    CATAPULT(PersonType.CATAPULT,1000,3000,2,4,6);

    private int hp;
    private PersonType personType;
    private int damage;
    private int fightingRange;
    private int visionRange;
    private int speed;
    private String imagePath;
    private boolean canFire;

    TroopTypes(PersonType personType, int hp, int damage, int speed, int fightingRange, int visionRange) {
        this.hp = hp;
        this.personType = personType;
        this.speed=speed;
        this.damage = damage;
        this.fightingRange = fightingRange;
        this.visionRange = visionRange;
        imagePath = "Media/People/Troops/" + personType.getName();
        canFire = false;
    }

    TroopTypes(PersonType personType, int hp, int damage, int speed, int fightingRange, int visionRange, boolean canFire) {
        this.hp = hp;
        this.personType = personType;
        this.speed=speed;
        this.damage = damage;
        this.fightingRange = fightingRange;
        this.visionRange = visionRange;
        imagePath = "Media/People/Troops/" + personType.getName();
        this.canFire = true;
    }

    public int getHp() {
        return hp;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public boolean canFire() {
        return canFire;
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

    public String getImagePath() {
        return imagePath;
    }

    public int getSpeed() {
        return speed;
    }
    public static TroopTypes getTroopByName(String TroopName){
        for (TroopTypes value : TroopTypes.values()) {
            if(value.getPersonType().getName().equals(TroopName)){
                return value;
            }
        }
        return null;
    }
}
