package model.game_stuff;

import model.game_stuff.enums.Items;

import java.util.HashMap;
import java.util.HashSet;

public class Trade {
    private Game game;
    private String id;
    private HashMap<Items, Integer> providedItems;
    private Player owner;
    private HashSet<Player> audiences; //TODO: find a better name
    private String message;

    {
        providedItems = new HashMap<>();
        audiences = new HashSet<>();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Trade(Player owner) {
        this.owner=owner;
    }

    public String getId() {
        return id;
    }

    public HashMap<Items, Integer> getProvidedItems() {
        return providedItems;
    }


    public Player getOwner() {
        return owner;
    }

    public HashSet<Player> getAudiences() {
        return audiences;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // faghat inja logic be kar rafte:
    public void changeProvidedItems(Items item, int amount) {
        if(providedItems.keySet().contains(item)) {
            providedItems.replace(item,providedItems.get(item) + amount);
            return;
        }
        providedItems.put(item, amount);
    }



    public void addAudience(Player player) {
        audiences.add(player);
    }

    public void removeAudiences(Player player) {
        audiences.remove(player);
    }
}
