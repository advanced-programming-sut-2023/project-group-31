package model.game_stuff;

import model.game_stuff.enums.Items;

import java.util.HashMap;

public class Possession {
    private Government owner;
    private HashMap<Items, Integer> items;
    private int gold;
    private int peasant;
//
//    private int meat;
//    private int apple;
//    private int cheese;
//    private int bread;
//    private int hops;
//    private int ale;
//    private int wheat;
//    private int flour;
//
//    private int wood;
//    private int stone;
//    private int iron;
//    private int pitch;
//
//    private int bow;
//    private int crossbow;
//    private int spear;
//    private int pike;
//    private int swords;
//    private int mace;
//    private int leatherArmor;
//    private int metalArmor;
//    private int horse;

    {
        items = new HashMap<>();
        items.put(Items.MEAT,0);
        items.put(Items.APPLE,0);
        items.put(Items.CHEESE,0);
        items.put(Items.BREAD,0);
        items.put(Items.ALE,0);
        items.put(Items.HOPS,0);
        items.put(Items.WHEAT,0);
        items.put(Items.FLOUR,0);
        items.put(Items.WOOD,0);
        items.put(Items.STONE,0);
        items.put(Items.IRON,0);
        items.put(Items.PITCH,0);
        items.put(Items.BOW,0);
        items.put(Items.CROSSBOW,0);
        items.put(Items.SPEAR,0);
        items.put(Items.PIKE,0);
        items.put(Items.SWORDS,0);
        items.put(Items.MACE,0);
        items.put(Items.LEATHER_ARMOR,0);
        items.put(Items.METAL_ARMOR,0);
        items.put(Items.HORSE,0);
    }
    public Possession(Government player){
        this.owner=player;
    }
    public int getItem(Items item) {
        return items.get(item);
    }

    public void setItem(Items target,int amount) {
        int getFirstAmount=items.get(target);
        amount=amount+getFirstAmount;
        items.remove(target);
        items.put(target,amount);
    }
    //private int cow;

    public int getGold() {
        return gold;
    }
    public int getPeasant() {
        return peasant;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
//    public int getMeat() {
//        return meat;
//    }
//
//    public int getApple() {
//        return apple;
//    }
//
//    public int getCheese() {
//        return cheese;
//    }
//
//    public int getBread() {
//        return bread;
//    }
//
//    public int getHops() {
//        return hops;
//    }
//
//    public int getAle() {
//        return ale;
//    }
//
//    public int getWheat() {
//        return wheat;
//    }
//
//    public int getFlour() {
//        return flour;
//    }
//
//    public int getWood() {
//        return wood;
//    }
//
//    public int getStone() {
//        return stone;
//    }
//
//    public int getIron() {
//        return iron;
//    }
//
//    public int getPitch() {
//        return pitch;
//    }
//
//    public int getBow() {
//        return bow;
//    }
//
//    public int getCrossbow() {
//        return crossbow;
//    }
//
//    public int getSpear() {
//        return spear;
//    }
//
//    public int getPike() {
//        return pike;
//    }
//
//    public int getSwords() {
//        return swords;
//    }
//
//    public int getMace() {
//        return mace;
//    }
//
//    public int getLeatherArmor() {
//        return leatherArmor;
//    }
//
//    public int getMetalArmor() {
//        return metalArmor;
//    }
//
//    public int getHorse() {
//        return horse;
//    }
//    public void setMeat(int meat) {
//        this.meat = meat;
//    }
//
//    public void setApple(int apple) {
//        this.apple = apple;
//    }
//
//    public void setCheese(int cheese) {
//        this.cheese = cheese;
//    }
//
//    public void setBread(int bread) {
//        this.bread = bread;
//    }
//
//    public void setHops(int hops) {
//        this.hops = hops;
//    }
//
//    public void setAle(int ale) {
//        this.ale = ale;
//    }
//
//    public void setWheat(int wheat) {
//        this.wheat = wheat;
//    }
//
//    public void setFlour(int flour) {
//        this.flour = flour;
//    }
//
//    public void setWood(int wood) {
//        this.wood = wood;
//    }
//
//    public void setStone(int stone) {
//        this.stone = stone;
//    }
//
//    public void setIron(int iron) {
//        this.iron = iron;
//    }
//
//    public void setPitch(int pitch) {
//        this.pitch = pitch;
//    }
//
//    public void setBow(int bow) {
//        this.bow = bow;
//    }
//
//    public void setCrossbow(int crossbow) {
//        this.crossbow = crossbow;
//    }
//
//    public void setSpear(int spear) {
//        this.spear = spear;
//    }
//
//    public void setPike(int pike) {
//        this.pike = pike;
//    }
//
//    public void setSwords(int swords) {
//        this.swords = swords;
//    }
//
//    public void setMace(int mace) {
//        this.mace = mace;
//    }
//
//    public void setLeatherArmor(int leatherArmor) {
//        this.leatherArmor = leatherArmor;
//    }
//
//    public void setMetalArmor(int metalArmor) {
//        this.metalArmor = metalArmor;
//    }
//
//    public void setHorse(int horse) {
//        this.horse = horse;
//    }

    public void setPeasant(int peasant) {
        int toSum=peasant-this.peasant;
        owner.setPopulation(owner.getPopulation()+toSum);
        this.peasant = peasant;
    }
}
