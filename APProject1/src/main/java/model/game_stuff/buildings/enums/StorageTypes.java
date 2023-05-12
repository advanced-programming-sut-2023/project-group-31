package model.game_stuff.buildings.enums;

import model.game_stuff.enums.ItemTypes;
import model.game_stuff.enums.Items;

public enum StorageTypes {
     STOCKPILE("Stockpile",100,ItemTypes.RAW_MATERIAL),
     FOOD_STOCKPILE("Food stockpile",1000,ItemTypes.FOOD),
     ARMOURY("Armoury",100,ItemTypes.WEAPON),
     BARRACK("Barrack",75,ItemTypes.PERSON),
     MERCENARY_POST("Mercenary post",75,ItemTypes.PERSON);
    private String name;
    private int capacity;
    private ItemTypes productType;

    StorageTypes(String name, int capacity, ItemTypes productType) {
        this.name = name;
        this.capacity = capacity;
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public ItemTypes getProductType() {
        return productType;
    }
}
