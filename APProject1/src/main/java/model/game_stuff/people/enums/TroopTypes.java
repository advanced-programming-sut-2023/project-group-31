package model.game_stuff.people.enums;

public enum TroopTypes {

    ARCHER("Archer",300,2250,4,4,6),
    CROSSBOWMEN("Crossbowmen",300,300,2,4,6),
    ARCHERBOW("Archer Bow",300,2250,4,4,6),
    HORSEARCHERS("Horse Archers",300,3000,5,4,6),
    FIRETHROWERS("Fire Throwers",500,2250,5,4,6),
    SPEARMEN("Spearmen",400,1500,3,1,2),
    PIKEMEN("Pikemen",400,3750,2,1,2),
    MACEMEN("Macemen",500,3000,3,1,2),
    SWORDSMEN("Swordsmen",600,1500,1,1,2),
    KNIGHT("Knight",600,3750,5,1,2),
    TUNNELER("Tunneler",400,0,4,1,2),
    LADDERMEN("Laddermen",400,0,4,1,2),
    ENGINEER("Engineer",400,0,3,1,2),
    BLACKMONK("Black Monk",400,3000,2,1,2),
    SLAVES("Slaves",200,750,4,1,2),
    ASSASSINS("Assassins",400,3000,3,1,2),
    ARABIANSWORDSMEN("Arabian Swordsmen",500,3750,5,1,2),
    CATAPULT("Catapult",1000,3000,2,4,6);
    private int hp;
    private String name;
    private int damage;
    private int fightingRange;
    private int visionRange;
    private int speed;

    TroopTypes(String name,int hp, int damage, int speed,int fightingRange, int visionRange) {
        this.hp = hp;
        this.name = name;
        this.speed=speed;
        this.damage = damage;
        this.fightingRange = fightingRange;
        this.visionRange = visionRange;
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
