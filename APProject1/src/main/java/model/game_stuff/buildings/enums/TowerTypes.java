package model.game_stuff.buildings.enums;

public enum TowerTypes {
    LOOKOUT_TOWER("lookout tower",1200,300,350,6),
    PREMITER_TOWER("Premiter tower",1200,250,300,6),
    TURRET("Turret",1500,150,180,10),
    SQUARE_TURRET("Square turret",2000,180,200,15),
    CIRCULAR_TURRET("Circular turret",2000,180,200,15),
    DRAWBRIDGE("Drawbridge",1200,120,150,4),
    WALL("wall", 1000,100, 100, 1);
    private String name;
    private int hp;
    private int fireRange;
    private int defendRange;
    private int capacity;

    TowerTypes(String name, int hp, int fireRange, int defendRange, int capacity) {
        this.name = name;
        this.hp = hp;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.capacity = capacity;
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
