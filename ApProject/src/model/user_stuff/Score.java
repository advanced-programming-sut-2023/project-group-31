package model.user_stuff;

import model.Game;
import model.User;

public class Score {
    private int amount;
    private User owner;
    private Game game;

    public Score(int amount, User owner, Game game) {
        this.amount = amount;
        this.owner = owner;
        this.game = game;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
