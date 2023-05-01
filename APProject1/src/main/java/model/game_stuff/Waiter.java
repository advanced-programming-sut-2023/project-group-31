package model.game_stuff;

public class Waiter {
    private int turnsToWait;
    private int turnCounter;

    {
        turnCounter = 0;
    }

    public Waiter(int turnsToWait) {
        this.turnsToWait = turnsToWait;
    }

    public int getTurnsToWait() {
        return turnsToWait;
    }
    public int getTurnCounter() {
        return turnCounter;
    }
    public void setTurnsToWait(int turnsToWait) {
        this.turnsToWait = turnsToWait;
    }
    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }
    public boolean isTheTurn() {
        if(turnCounter == turnsToWait) {
            turnCounter = 0;
            return true;
        }
        turnCounter++;
        return false;
    }
}
