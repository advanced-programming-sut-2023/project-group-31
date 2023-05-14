package model.game_stuff.buildings.enums;

import model.game_stuff.enums.Items;
import model.game_stuff.people.enums.WorkerTypes;

public enum ProducerTypes {
    BREWERY(200,2,"Brewery",WorkerTypes.BREWERY),
    BAKERY(300,2, "Bakery",WorkerTypes.BAKER),
    WHEAT_FARM(100,1,"Wheat farm",WorkerTypes.WHEAT_FARMER),
    HUNTING_POST(100,1,"Hunting post",WorkerTypes.HUNTER),
    GRAIN_FARM(100,1,"Grain farm",WorkerTypes.GRAIN_FARMER),
    DIARY_PRODUCTS(100,1,"Diary products",WorkerTypes.DIARY_PRODUCER),
    APPLE_GARDEN(100,1,"Apple garden", WorkerTypes.APPLE_GARDENER),
    WOOD_CUTTER(100,1,"Wood cutter",WorkerTypes.WOOD_CUTTER),
    IRON_MINE(300,3,"Iron mine",WorkerTypes.IRON_MINER),
    QUARRY(500,3,"Quarry",WorkerTypes.QUARRY_WORKER),
    BLACKSMITH(200,2,"Black smith",WorkerTypes.BLACKSMITH),
    FLETCHER(200,2,"Fletcher",WorkerTypes.FLETCHER),
    CROSSBOW_MAKER(200,2, "Crossbow maker",WorkerTypes.CROSSBOW_MAKER),
    POLE_TURNER(200,2,"PoleTurner",WorkerTypes.POLE_TURNER),
    ARMOURER(200,2,"Armourer",WorkerTypes.ARMOURER),
    LEATHER_ARMOURER(200,2,"Leather armourer",WorkerTypes.LEATHER_ARMOURER),
    STABLE(200,2,"Stable",WorkerTypes.STABLE_WORKER),
    PIKE_MAKER(200,2,"Pike maker",WorkerTypes.PIKE_MAKER),
    MACE_MAKER(200,2,"Mace maker",WorkerTypes.MACE_MAKER);
    private String name;
    private int hp;
    private int capacity;
    private WorkerTypes workerType;

    ProducerTypes(int hp, int capacity, String name, WorkerTypes workerType) {
        this.name = name;
        this.hp = hp;
        this.capacity = capacity;
        this.workerType = workerType;
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
