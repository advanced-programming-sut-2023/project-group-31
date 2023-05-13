package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.ProducerTypes;
import model.game_stuff.people.Worker;

public class Producer extends Building {
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
            //search for worker
            return;
        }
        worker.work();
    }

    @Override
    public String toString() {
        return  type.getName() + "\n" +
            "productsAvailable: " + numberOfProductsAvailable + " / " + type.getCapacity() + "\n"
            + worker.getState().getName() + "\n" +
            "hp: " + hp + " / " + maxHp;
    }
}
