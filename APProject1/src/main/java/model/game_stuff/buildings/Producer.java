package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.Rate;
import model.game_stuff.buildings.enums.ProducerTypes;
import model.game_stuff.enums.Items;
import model.game_stuff.troops.Transporter;

import java.util.ArrayList;

public class Producer extends Building {
    private ProducerTypes type;
    private ArrayList<Transporter> workers;
    private Rate rate;
    private int numberOfProductsAvailable;
    {

    }
    public Producer(ProducerTypes type, Government government) {
        super(government);
        this.type = type;
        rate = new Rate(type.getTurnsToWait(),type.getAmountOfProductToProduce());
    }
    public void produce() {
        if(rate.GoToNextTurn() && numberOfProductsAvailable < type.getCapacity()) {
            numberOfProductsAvailable += rate.getAmountOfThingToProduce();
            if(numberOfProductsAvailable > type.getCapacity()) {
                numberOfProductsAvailable = type.getCapacity();
            }
        }
    }
}
