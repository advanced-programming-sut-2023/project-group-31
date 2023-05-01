package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.ProducerTypes;
import model.game_stuff.people.Worker;

import java.util.ArrayList;

public class Producer extends Building {
    private ProducerTypes type;
    private ArrayList<Worker> workers;
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

    @Override
    public String toString() {
        return "Producer{" +
                "type=" + type +
                ", workers=" + workers +
                ", numberOfProductsAvailable=" + numberOfProductsAvailable +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", blocks=" + blocks +
                '}';
    }
}
