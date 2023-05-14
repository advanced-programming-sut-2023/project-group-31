package model.game_stuff.types;

import model.game_stuff.enums.Items;

import java.util.ArrayList;

public enum Troops{//assassins - fire towers - horseArchers,ladder man, ordinary - slinger , spear man
    NULL("Null",null,10,null,null),
    ARCHER("Archer",Nationality.EUROPEAN,10,"thrower",Items.BOW),
    CROSSBOWMEN("Crossbowmen",Nationality.EUROPEAN,10,"thrower",Items.CROSSBOW),
    SPEARMEN("Spearmen",Nationality.EUROPEAN,10,"thrower",Items.SPEAR),
    PIKEMEN("Pikemen",Nationality.EUROPEAN,10,"kicker",Items.PIKE),
    MACEMEN("Macemen",Nationality.EUROPEAN,10,"kicker",Items.MACE),
    SWORDSMEN("Swordsmen",Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    KNIGHT("Knight",Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    TUNNELER("Tunneler",Nationality.NULL,10,"kicker",null),
    LADDERMEN("Laddermen",Nationality.NULL,10,"kicker",null),
    ENGINEER("Engineer",Nationality.NULL,10,"kicker",null),
    BLACKMONK("Black Monk",Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    ARCHERBOW("Archer Bow",Nationality.ARAB,10,"thrower",Items.BOW),
    SLAVES("Slaves",Nationality.ARAB,10,"kicker",Items.FIRE),
    SLINGERS("Slingers",Nationality.ARAB,10,"thrower",Items.BOW),
    ASSASSINS("Assassins",Nationality.ARAB,10,"assassins",Items.SWORDS),
    HORSEARCHERS("Horse Archers",Nationality.ARAB,10,"thrower",Items.BOW),
    ARABIANSWORDSMEN("Arabian Swordsmen",Nationality.ARAB,10,"kicker",Items.SWORDS),
    FIRETHROWERS("Fire Throwers",Nationality.ARAB,10,"thrower",Items.BOW),
    CATAPULT("Catapult",Nationality.NULL,10,null,null);
    // hes mikonam ke sarbazaye arab bayad barashoon tedad moshakhas beshe vali nemidoonam

//    private double damage;// very high 6 high 5 average 4 low 3 very low 2 no damage 1
//    private double defence;//very high 6 high 5 average 4 low 3 very low 2 very, very low 1
//    private double speed;//very high 5 high 4 average 3 slow 2 very slow 1
    private  String name;
    private Nationality nationality;
    private Items weapon;
    private String type;
    private int cost;
    Troops(String name,Nationality nationality,int cost,String type,Items items) {
        this.name=name;
        this.nationality=nationality;
        this.cost=cost;
        weapon=items;
        this.type=type;
    }

    public Items getWeapon() {
        return weapon;
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
