package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.StorageTypes;
import model.game_stuff.enums.Items;

import java.util.HashMap;

public class Storage extends Building {
    private StorageTypes type;
    private HashMap<Items, Integer> properties;
    private int amountOfProducts;
    {
        amountOfProducts = 0;
        properties = new HashMap<>();
    }
    public Storage(Government government, StorageTypes type) {
        super(government);
        this.type = type;
        owner.addBuilding(this);
        switch (type.getProductType()) {
            case FOOD:
                owner.addGranary(this);
                break;
            case RAW_MATERIAL:
                owner.addStockpile(this);
                break;
            case WEAPON:
                owner.addWeaponry(this);
                break;
        }
        name = type.getName();
        imagePath = type.getImagePath();
        baseBuildingType = type.getBaseBuildingType();
    }

    public HashMap<Items, Integer> getProperties() {
        return properties;
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
        return type.getCapacity() - amountOfProducts;
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
        if (properties.get(product) == 0) {
            properties.remove(product);
        }
        amountOfProducts -= amount;
    }

    @Override
    public String toString() {
        String output = type.getName() + "\n" +
            "hp: " + hp + " / " + maxHp + "\n" +
            "capacity: " + amountOfProducts + " / " + type.getCapacity();
        for (Items item : properties.keySet()) {
            output += "\n" + item.getName() + ": " + properties.get(item);
        }
        return output;
    }
}
