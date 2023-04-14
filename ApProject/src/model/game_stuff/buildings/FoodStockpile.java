package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Food;

import java.util.ArrayList;

public class FoodStockpile extends Building {
    ArrayList<Food> foods;

    public FoodStockpile() {
        this.foods = new ArrayList<Food>();
    }
    public void addFood(){

    }
    public Food getFood(){
        return null;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}
