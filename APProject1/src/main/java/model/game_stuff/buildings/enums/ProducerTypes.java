package model.game_stuff.buildings.enums;

import model.game_stuff.ImagePackage;
import model.game_stuff.enums.ImageItem;
import model.game_stuff.enums.Items;
import model.game_stuff.people.enums.WorkerTypes;
import model.game_stuff.types.Buildings;

public enum ProducerTypes {
    BREWER(200,2,Buildings.BREWER,WorkerTypes.BREWERY),
    BAKERY(300,4, Buildings.BAKERY,WorkerTypes.BAKER),
    WHEAT_FARMER(100,1,Buildings.WHEAT_FARMER,WorkerTypes.WHEAT_FARMER),
    HUNTING_POST(100,1, Buildings.HUNTER_POST,WorkerTypes.HUNTER),
    HOPS_FARMER(100,1,Buildings.HOPS_FARMER,WorkerTypes.GRAIN_FARMER),
    DIARY_FARMER(100,1,Buildings.DIARY_FARMER,WorkerTypes.DIARY_PRODUCER),
    APPLE_ORCHARD(100,1,Buildings.APPLE_ORCHARD, WorkerTypes.APPLE_GARDENER),
    WOODCUTTER(100,1,Buildings.WOODCUTTER,WorkerTypes.WOOD_CUTTER),
    IRON_MINE(300,3,Buildings.IRON_MINE,WorkerTypes.IRON_MINER),
    QUARRY(500,3, Buildings.QUARRY,WorkerTypes.QUARRY_WORKER),
    BLACKSMITH(200,2, Buildings.BLACKSMITH,WorkerTypes.BLACKSMITH),
    FLETCHER(200,2,Buildings.FLETCHER,WorkerTypes.FLETCHER),
    CROSSBOW_MAKER(200,2, Buildings.CROSSBOW_MAKER,WorkerTypes.CROSSBOW_MAKER),
    POLE_TURNER(200,2, Buildings.CROSSBOW_MAKER,WorkerTypes.POLE_TURNER),
    ARMOURER(200,2,Buildings.ARMOURER,WorkerTypes.ARMOURER),
    LEATHER_ARMOURER(200,2,Buildings.LEATHER_ARMOURER,WorkerTypes.LEATHER_ARMOURER),
    STABLE(200,2,Buildings.STABLE,WorkerTypes.STABLE_WORKER),
    PIKE_MAKER(200,2,Buildings.PIKE_MAKER,WorkerTypes.PIKE_MAKER),
    MACE_MAKER(200,2,Buildings.MACE_MAKER,WorkerTypes.MACE_MAKER);
    private String name;
    private Buildings buildingCost;
    private ImagePackage imagePackage;
    private int hp;
    private int capacity;
    private WorkerTypes workerType;

    ProducerTypes(int hp, int capacity, Buildings building, WorkerTypes workerType) {
        this.name = building.getName();
        buildingCost = building;
        imagePackage = new ImagePackage("Media/Buildings/" + building.getCategory() + "/" + name + "/" + name);
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

    public ImagePackage getImagePackage() {
        return imagePackage;
    }
}
