package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.Working;
import model.game_stuff.buildings.enums.ProducerTypes;
import model.game_stuff.people.Worker;
import model.game_stuff.people.enums.WorkerTypes;

public class Producer extends Building implements Working {
    private ProducerTypes type;
    private Worker worker;
    private int numberOfProductsAvailable;
    {

    }
    public Producer(ProducerTypes type, Government government) {
        super(government);
        this.type = type;
    }

    public int getNumberOfProductsAvailable() {
        return numberOfProductsAvailable;
    }
    public boolean isFull() {
        return numberOfProductsAvailable >= type.getCapacity();
    }
    public void setNumberOfProducts(int number) {
        numberOfProductsAvailable = number;
    }
    public void addProduct(int amount) {
        numberOfProductsAvailable += amount;
        if(numberOfProductsAvailable > type.getCapacity()) {
            numberOfProductsAvailable = type.getCapacity();
        }
    }

    public void work() {
        if (worker == null) {
            owner.giveWorker(this);
            if(worker == null) {
                return;
            }
        }
        worker.work();
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public WorkerTypes getWorkerType() {
        return type.getWorkerType();
    }

    @Override
    public String toString() {
        return  type.getName() + "\n" +
            "productsAvailable: " + numberOfProductsAvailable + " / " + type.getCapacity() + "\n"
            + worker.getState().getName() + "\n" +
            "hp: " + hp + " / " + maxHp;
    }
}
