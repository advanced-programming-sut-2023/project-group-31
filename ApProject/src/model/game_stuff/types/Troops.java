package model.game_stuff.types;

public enum Troops{//assassins - fire towers - horseArchers,ladder man, ordinary - slinger , spear man
    NULL("Null",null,0,0,0,10),
    ARCHER("Archer",Nationality.EUROPEAN,3,3,4,10),
    CROSSBOWMEN("Crossbowmen",Nationality.EUROPEAN,3,4,2,10),
    SPEARMEN("Spearmen",Nationality.EUROPEAN,4,2,3,10),
    PIKEMEN("Pikemen",Nationality.EUROPEAN,4,5,2,10),
    MACEMEN("Macemen",Nationality.EUROPEAN,5,4,3,10),
    SWORDSMEN("Swordsmen",Nationality.EUROPEAN,6,2,2,10),
    KNIGHT("Knight",Nationality.EUROPEAN,6,5,5,10),
    TUNNELER("Tunneler",Nationality.EUROPEAN,4,2,4,10),
    LADDERMEN("Laddermen",Nationality.EUROPEAN,1,2,4,10),
    ENGINEER("Engineer",Nationality.EUROPEAN,1,2,3,10),
    BLACKMONK("Black Monk",Nationality.EUROPEAN,4,4,2,10);



    private double damage;// very high 6 high 5 average 4 low 3 very low 2 no damage 1
    private double defence;//very high 6 high 5 average 4 low 3 very low 2 very very low 1
    private double speed;//very high 5 high 4 average 3 slow 2 very slow 1
    private  String name;
    private Nationality nationality;
    private int cost;
    Troops(String name,Nationality nationality,int damage, int defence, int speed,int cost) {
        this.damage = damage;
        this.defence = defence;
        this.speed = speed;
        this.name=name;
        this.nationality=nationality;
    }

    public String getName() {
        return name;
    }

    public double getDefence() {
        return this.defence;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }
}
