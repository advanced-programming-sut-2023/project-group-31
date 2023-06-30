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
    public void setTurnsToWait(int turnsToWait) {
        this.turnsToWait = turnsToWait;
    }
    public void reset() {
        turnCounter = 0;
    }
    public boolean isTheTurn() {
        if(turnCounter >= turnsToWait) {
            turnCounter = 0;
            return true;
        }
        turnCounter++;
        return false;
    }
}
