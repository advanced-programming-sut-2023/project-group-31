package model.game_stuff;

public class Rate {
    private int turnsToWait;
    private int amountOfThingToProduce;
    private int turnCounter;

    {
        turnCounter = 0;
    }

    public Rate(int turnsToWait, int amountOfThingToProduce) {
        this.turnsToWait = turnsToWait;
        this.amountOfThingToProduce = amountOfThingToProduce;
    }

    public int getTurnsToWait() {
        return turnsToWait;
    }

    public int getAmountOfThingToProduce() {
        return amountOfThingToProduce;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnsToWait(int turnsToWait) {
        this.turnsToWait = turnsToWait;
    }

    public void setAmountOfThingToProduce(int amountOfThingToProduce) {
        this.amountOfThingToProduce = amountOfThingToProduce;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public boolean GoToNextTurn() {
        turnCounter++;
        if(turnCounter == turnsToWait) {
            turnCounter = 0;
            return true;
        }
        return false;
    }
}
