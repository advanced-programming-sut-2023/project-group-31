package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.enums.BuildingCategories;
import model.game_stuff.types.Buildings;

public enum TowerTypes {
    LOOKOUT_TOWER(Buildings.LOOKOUT_TOWER,1200,300,350,6),
    PERIMETER_TOWER(Buildings.PERIMETER_TOWER,1200,250,300,6),
    DEFENCE_TURRET(Buildings.DEFENCE_TURRET,1500,150,180,10),
    SQUARE_TOWER(Buildings.SQUARE_TOWER,2000,180,200,15),
    ROUND_TOWER(Buildings.ROUND_TOWER,2000,180,200,15),
    WALL(Buildings.WALL, 1000,100, 100, 1);
    private String name;
    private String imagePath;
    private int hp;
    private int fireRange;
    private int defendRange;
    private int capacity;
    private Buildings baseBuildingType;

    TowerTypes(Buildings building, int hp, int fireRange, int defendRange, int capacity) {
        this.name = building.getName();
        imagePath = "Media/Buildings/" + building.getCategory() + "/" + name + "/" + name;
        this.hp = hp;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.capacity = capacity;
        baseBuildingType = building;
    }

    public Buildings getBaseBuildingType() {
        return baseBuildingType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getCapacity() {
        return capacity;
    }


    public static TowerTypes getEnumByName(String name) {
        for (TowerTypes towerTypes : values()) {
            if(towerTypes.name.equalsIgnoreCase(name)){
                return towerTypes;
            }
        }
        return null;
    }

}
