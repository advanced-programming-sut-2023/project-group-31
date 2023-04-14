package model.game_stuff;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {
    private int popularity;
    private int fear;
    private HashMap<Food, Double> foods;
    private HashMap<Good,Double> goods;
    private int taxRate;


    //TODO set default fields for kingdom
    public Kingdom() {
        this.popularity = 0;
        this.fear = 0;
        this.foods = new HashMap<Food, Double>();
        this.taxRate = 0;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public HashMap<Food, Double> getFoods() {
        return foods;
    }

    public void setFoods(HashMap<Food, Double> foods) {
        this.foods = foods;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }
}
