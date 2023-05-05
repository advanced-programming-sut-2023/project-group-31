package model.game_stuff.types;

import java.util.ArrayList;

public enum Troops{//assassins - fire towers - horseArchers,ladder man, ordinary - slinger , spear man
    NULL("Null",null,10,null),
    ARCHER("Archer",Nationality.EUROPEAN,10,"thrower"),
    CROSSBOWMEN("Crossbowmen",Nationality.EUROPEAN,10,"thrower"),
    SPEARMEN("Spearmen",Nationality.EUROPEAN,10,"thrower"),
    PIKEMEN("Pikemen",Nationality.EUROPEAN,10,"kicker"),
    MACEMEN("Macemen",Nationality.EUROPEAN,10,"kicker"),
    SWORDSMEN("Swordsmen",Nationality.EUROPEAN,10,"kicker"),
    KNIGHT("Knight",Nationality.EUROPEAN,10,"kicker"),
    TUNNELER("Tunneler",Nationality.EUROPEAN,10,"kicker"),
    LADDERMEN("Laddermen",Nationality.EUROPEAN,10,"kicker"),
    ENGINEER("Engineer",Nationality.EUROPEAN,10,"kicker"),
    BLACKMONK("Black Monk",Nationality.EUROPEAN,10,"kicker"),
    ARCHERBOW("Archer Bow",Nationality.ARAB,10,"thrower"),
    SLAVES("Slaves",Nationality.ARAB,10,"kicker"),
    SLINGERS("Slingers",Nationality.ARAB,10,"thrower"),
    ASSASSINS("Assassins",Nationality.ARAB,10,"kicker"),
    HORSEARCHERS("Horse Archers",Nationality.ARAB,10,"thrower"),
    ARABIANSWORDSMEN("Arabian Swordsmen",Nationality.ARAB,10,"kicker"),
    FIRETHROWERS("Fire Throwers",Nationality.ARAB,10,"thrower");
    // hes mikonam ke sarbazaye arab bayad barashoon tedad moshakhas beshe vali nemidoonam

//    private double damage;// very high 6 high 5 average 4 low 3 very low 2 no damage 1
//    private double defence;//very high 6 high 5 average 4 low 3 very low 2 very, very low 1
//    private double speed;//very high 5 high 4 average 3 slow 2 very slow 1
    private  String name;
    private Nationality nationality;
    private String type;
    private int cost;
    Troops(String name,Nationality nationality,int cost,String type) {
        this.name=name;
        this.nationality=nationality;
        this.cost=cost;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getType() {
        return type;
    }
    public static Troops getTroopsByName(String name){
        for (Troops value : Troops.values()) {
            if(name.equals(value.getName())){
                return value;
            }
        }
        return null;
    }
}
