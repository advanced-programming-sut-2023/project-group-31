package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Person;
import model.game_stuff.Waiter;
import model.game_stuff.buildings.Producer;
import model.game_stuff.buildings.Storage;
import model.game_stuff.people.enums.WorkerStates;
import model.game_stuff.people.enums.WorkerTypes;

import java.util.ArrayList;

public class Worker extends Person {
    protected Producer workHouse;
    //tabe add product ro dar government mi sazim
    protected WorkerTypes type;
    protected WorkerStates state;
    protected Waiter producingWaiter;
    protected Waiter transportingWaiter;
    protected Block destination;
    private int numberOfProductsCarrying;

    public Worker(Producer workHouse, WorkerTypes type) {
        super(workHouse.getOwner());
        this.workHouse = workHouse;
        this.type = type;
        producingWaiter = new Waiter(type.getTurnsToWait());
        state = WorkerStates.PRODUCING;
        name = type.getPersonType().getName();
        personType = type.getPersonType();
        speed = type.getSpeed();
        imagePath = type.getImagePath();
    }
    public boolean isInWorkHouse() {
        return position.equals(workHouse.getPosition());
    }
    //agar storage az bein raft bayad notify kone
    //notifier ha zakhire beshan vaqti nobatesh shod neshoon dade beshan
    protected void deliver() {
        Storage storage = (Storage) destination.getBuilding();
        if(storage.getCapacityLeft() >= numberOfProductsCarrying) {
            storage.addProduct(type.getProduct(), numberOfProductsCarrying);
            numberOfProductsCarrying = 0;
            state = WorkerStates.HEADING_BACK;
            destination = workHouse.getPosition();
            moveDestination = workHouse.getPosition();
            changeRout();
        } else {
            numberOfProductsCarrying -= storage.getCapacityLeft();
            storage.addProduct(type.getProduct(), storage.getCapacityLeft());
            setAppropriateStorage();
        }
    }
    protected void produce() {
        workHouse.addProduct(type.getAmountOfProductToProduce());
        if(workHouse.getNumberOfProductsAvailable() >= type.getNumberOfProductsToCarry()) {
            workHouse.addProduct(-1 * type.getNumberOfProductsToCarry());
            numberOfProductsCarrying = type.getNumberOfProductsToCarry();
            state = WorkerStates.HEADING_STORAGE;
            setAppropriateStorage();
        }
    }
    public void work() {
        switch (state) {
            case HEADING_BACK:
                if(!move()) {
                    if(position.equals(workHouse.getPosition())) {
                        state = WorkerStates.PRODUCING;
                    } else {
                        changeRout();
                        move();
                    }
                }
                break;
            case HEADING_STORAGE:
                if(destination == null) {
                    setAppropriateStorage();
                    return;
                } else {
                    if(!move()) {
                        if(position.equals(moveDestination)) {
                            deliver();
                        } else {
                            changeRout();
                            move();
                        }
                    }
                }
                break;
            case PRODUCING:
                if(workHouse.getNumberOfProductsAvailable() >= type.getNumberOfProductsToCarry()) {
                    workHouse.addProduct(-1 * type.getNumberOfProductsToCarry());
                    numberOfProductsCarrying = type.getNumberOfProductsToCarry();
                    state = WorkerStates.HEADING_STORAGE;
                    setAppropriateStorage();
                } else if(producingWaiter.isTheTurn()) {
                    produce();
                }
                break;
        }
    }
    public void setAppropriateStorage() {
        ArrayList<Storage> storages = null;
        switch (type.getProduct().getType()) {
            case RAW_MATERIAL:
                storages = owner.getStockpiles();
                break;
            case FOOD:
                storages = owner.getGranaries();
                break;
            case WEAPON:
                storages = owner.getWeaponries();
                break;
        }
        destination = findBestStorage(storages);
        moveDestination = destination;
        if(destination == null) {
            //notify the government
            return;
        }
        int distance = destination.getDistanceTo(position);
        changeRout();
    }

    public WorkerStates getState() {
        return state;
    }

    private Block findBestStorage(ArrayList<Storage> storages) {
        Storage bestStorage = null;
        int minimumDistance = 500;
        int distance;
        for (Storage storage : storages) {
            if(!storage.isFull() && (distance = storage.getPosition().getDistanceTo(this.position)) < minimumDistance) {
                minimumDistance = distance;
                bestStorage = storage;
            }
        }
        if( bestStorage == null) {
            return null;
        }
        return bestStorage.getPosition();
    }
    @Override
    public String toString() {
        return type.getPersonType().getName() + ":" + (100 * hp / type.getHp());
    }

    public void die() {
        position.setPerson(null);
        workHouse.setWorker(null);
    }
}
