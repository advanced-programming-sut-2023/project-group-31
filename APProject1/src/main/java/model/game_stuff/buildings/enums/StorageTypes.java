package model.game_stuff.buildings.enums;

import model.game_stuff.enums.ItemTypes;
import model.game_stuff.enums.Items;

public enum StorageTypes {
    ;
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
