package model.game_stuff.types;

import model.game_stuff.enums.Items;

import java.util.ArrayList;

public enum Troops{//assassins - fire towers - horseArchers,ladder man, ordinary - slinger , spear man
    ARCHER(PersonType.ARCHER,Nationality.EUROPEAN,10,"thrower",Items.BOW),
    CROSSBOW_MAN(PersonType.CROSSBOW_MAN,Nationality.EUROPEAN,10,"thrower",Items.CROSSBOW),
    SPEAR_MAN(PersonType.SPEAR_MAN,Nationality.EUROPEAN,10,"thrower",Items.SPEAR),
    PIKE_MAN(PersonType.PIKE_MAN,Nationality.EUROPEAN,10,"kicker",Items.PIKE),
    MACE_MAN(PersonType.MACE_MAN,Nationality.EUROPEAN,10,"kicker",Items.MACE),
    SWORDS_MAN(PersonType.SWORDSMAN,Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    KNIGHT(PersonType.KNIGHT,Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    TUNNELER(PersonType.TUNNELER,Nationality.NULL,10,"kicker",null),
    LADDER_MAN(PersonType.LADDER_MAN,Nationality.NULL,10,"kicker",null),
    ENGINEER(PersonType.ENGINEER,Nationality.NULL,10,"kicker",null),
    BLACK_MONK(PersonType.BLACK_MONK,Nationality.EUROPEAN,10,"kicker",Items.SWORDS),
    ARCHER_BOW(PersonType.ARABIAN_BOW,Nationality.ARAB,10,"thrower",Items.BOW),
    SLAVE(PersonType.SLAVE,Nationality.ARAB,10,"kicker",Items.FIRE),
    SLINGER(PersonType.SLINGER,Nationality.ARAB,10,"thrower",Items.BOW),
    ASSASSIN(PersonType.ASSASSIN,Nationality.ARAB,10,"assassins",Items.SWORDS),
    HORSE_ARCHER(PersonType.HORSE_ARCHER,Nationality.ARAB,10,"thrower",Items.BOW),
    ARABIAN_SWORDSMAN(PersonType.ARABIAN_SWORDSMAN,Nationality.ARAB,10,"kicker",Items.SWORDS),
    FIRE_THROWER(PersonType.FIRE_THROWER,Nationality.ARAB,10,"thrower",Items.BOW),
    CATAPULT(PersonType.CATAPULT,Nationality.NULL,10,null,null);
    // hes mikonam ke sarbazaye arab bayad barashoon tedad moshakhas beshe vali nemidoonam

//    private double damage;// very high 6 high 5 average 4 low 3 very low 2 no damage 1
//    private double defence;//very high 6 high 5 average 4 low 3 very low 2 very, very low 1
//    private double speed;//very high 5 high 4 average 3 slow 2 very slow 1
    private String name;
    private PersonType personType;
    private Nationality nationality;
    private Items weapon;
    private String type;
    private int cost;
    Troops(PersonType personType,Nationality nationality,int cost,String type,Items items) {
        this.personType = personType;
        this.name = personType.getName();
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
