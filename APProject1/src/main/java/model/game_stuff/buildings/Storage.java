package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.StorageTypes;
import model.game_stuff.enums.Items;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building {
    private StorageTypes type;
    private HashMap<Items, Integer> properties;
    private int amountOfProducts;
    {
        amountOfProducts = 0;
    }
    public Storage(Government government, StorageTypes type) {
        super(government);
        this.type = type;
    }

    public StorageTypes getType() {
        return type;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }
    public boolean isFull() {
        return amountOfProducts >= type.getCapacity();
    }
    public int getCapacityLeft() {
        return amountOfProducts - type.getCapacity();
    }
    public void addProduct(Items product, int amount) {
        if (properties.containsKey(product)){
            properties.replace(product, properties.get(product) + amount);
        } else {
            properties.put(product, amount);
        }
        amountOfProducts += amount;
    }
    public void removeProduct(Items product, int amount) {
        properties.replace(product, properties.get(product) - amount);
        if(properties.get(product) == 0) {
            properties.remove(product);
        }
        amountOfProducts -= amount;
    }

    public String getName() {
        return type.getName();
    }
}
