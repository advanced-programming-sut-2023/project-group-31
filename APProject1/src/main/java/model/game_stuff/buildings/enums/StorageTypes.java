package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.enums.ItemTypes;
import model.game_stuff.enums.Items;
import model.game_stuff.types.Buildings;

public enum StorageTypes {
     STOCKPILE(Buildings.STOCKPILE,4000,ItemTypes.RAW_MATERIAL),
     Granary(Buildings.GRANARY,4000,ItemTypes.FOOD),
     ARMOURY(Buildings.ARMOURY,4000,ItemTypes.WEAPON),
    ;
    private String name;
    private int capacity;
    private String imagePath;
    private ItemTypes productType;
    private Buildings baseBuildingType;

    StorageTypes(Buildings building, int capacity, ItemTypes productType) {
        this.name = building.getName();
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name;
        this.capacity = capacity;
        this.productType = productType;
        baseBuildingType = building;
    }

    public Buildings getBaseBuildingType() {
        return baseBuildingType;
    }

    public String getImagePath() {
        return imagePath;
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

    public static StorageTypes getEnumByName(String name) {
        for (StorageTypes storageType : values()) {
            if(storageType.name.equalsIgnoreCase(name)){
                return storageType;
            }
        }
        return null;
    }
}
