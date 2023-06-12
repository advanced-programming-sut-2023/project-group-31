package model.game_stuff.buildings.enums;

import model.game_stuff.enums.Items;
import model.game_stuff.people.enums.WorkerTypes;

public enum ProducerTypes {
    BREWERY(200,2,"Brewery",WorkerTypes.BREWERY, "Food processors"),
    BAKERY(300,4, "Bakery",WorkerTypes.BAKER, "Food processors"),
    WHEAT_FARM(100,1,"Wheat farm",WorkerTypes.WHEAT_FARMER, "Farm"),
    HUNTING_POST(100,1,"Hunting post",WorkerTypes.HUNTER, "Farm"),
    GRAIN_FARM(100,1,"Grain farm",WorkerTypes.GRAIN_FARMER, "Farm"),
    DIARY_PRODUCTS(100,1,"Diary products",WorkerTypes.DIARY_PRODUCER, "Farm"),
    APPLE_GARDEN(100,1,"Apple garden", WorkerTypes.APPLE_GARDENER, "Farm"),
    WOOD_CUTTER(100,1,"Wood cutter",WorkerTypes.WOOD_CUTTER, "Industry"),
    IRON_MINE(300,3,"Iron mine",WorkerTypes.IRON_MINER, "Industry"),
    QUARRY(500,3,"Quarry",WorkerTypes.QUARRY_WORKER, "Industry"),
    BLACKSMITH(200,2,"Black smith",WorkerTypes.BLACKSMITH, "Weapons"),
    FLETCHER(200,2,"Fletcher",WorkerTypes.FLETCHER, "Weapons"),
    CROSSBOW_MAKER(200,2, "Crossbow maker",WorkerTypes.CROSSBOW_MAKER, "Weapons"),
    POLE_TURNER(200,2,"PoleTurner",WorkerTypes.POLE_TURNER, "Weapons"),
    ARMOURER(200,2,"Armourer",WorkerTypes.ARMOURER, "Weapons"),
    LEATHER_ARMOURER(200,2,"Leather armourer",WorkerTypes.LEATHER_ARMOURER, "Weapons"),
    STABLE(200,2,"Stable",WorkerTypes.STABLE_WORKER, "Castle"),
    PIKE_MAKER(200,2,"Pike maker",WorkerTypes.PIKE_MAKER, "Weapons"),
    MACE_MAKER(200,2,"Mace maker",WorkerTypes.MACE_MAKER, "Weapons");
    private String name;
    private int hp;
    private int capacity;
    private WorkerTypes workerType;
    private String imagePath;

    ProducerTypes(int hp, int capacity, String name, WorkerTypes workerType, String category) {
        this.name = name;
        this.hp = hp;
        this.capacity = capacity;
        this.workerType = workerType;
        imagePath = "/Media/Buildings/" + category + "/" + name + "/" + name + ".png";
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

    public int getCapacity() {
        return capacity;
    }

    public WorkerTypes getWorkerType() {
        return workerType;
    }

    public static ProducerTypes getEnumByName(String name) {
        for (ProducerTypes producerType : values()) {
            if(producerType.name.equalsIgnoreCase(name)){
                return producerType;
            }
        }
        return null;
    }
}
