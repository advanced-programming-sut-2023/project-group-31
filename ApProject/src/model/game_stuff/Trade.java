package model.game_stuff;

import model.game_stuff.enums.Items;

import java.util.HashMap;
import java.util.HashSet;

public class Trade {
    private static int idCounter = 1001;
    private int id; //mahdoodiat tedad e trade darim
    private HashMap<Items, Integer> providedItems;
    private HashMap<Items, Integer> askedItems;
    private Player owner;
    private Player askedPlayer;
    private String ownersMessage;
    private String othersMessage;
    private boolean isWatched;

    {
        providedItems = new HashMap<>();
        askedItems = new HashMap<>();
        isWatched = false;
    }

    public Trade(Player owner, Player askedPlayer) {
        this.owner = owner;
        this.askedPlayer = askedPlayer;
        this.id = idCounter;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public HashMap<Items, Integer> getProvidedItems() {
        return providedItems;
    }

    public HashMap<Items, Integer> getAskedItems() {
        return askedItems;
    }

    public Player getOwner() {
        return owner;
    }


    public String getOwnersMessage() {
        return ownersMessage;
    }

    public void setOwnersMessage(String message) {
        this.ownersMessage = message;
    }

    public String getOthersMessage() {
        return othersMessage;
    }

    public void setOthersMessage(String othersMessage) {
        this.othersMessage = othersMessage;
    }

    // faghat inja ye kam logic be kar rafte:
    public void changeProvidedItems(Items item, int amount) {
        if(providedItems.keySet().contains(item)) {
            providedItems.replace(item,providedItems.get(item) + amount);
            return;
        }
        providedItems.put(item, amount);
    }

    public void changeAskedItems(Items item, int amount) {
        if(askedItems.keySet().contains(item)) {
            askedItems.replace(item,askedItems.get(item) + amount);
            return;
        }
        askedItems.put(item, amount);
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

    public Player getAskedPlayer() {
        return askedPlayer;
    }

    @Override
    public String toString() {
        return "Trade :" + id + "{\n" +
                owner.getName() + " " + owner.getColor().getName() + (ownersMessage == null ? "" : (": " + ownersMessage)) + "\n" +
                "providedItems=" + providedItems + "\n" +
                "askedItems=" + askedItems + "\n" +
                askedPlayer.getName() + " " + askedPlayer.getColor().getName() + (othersMessage == null ? "" : (": " + othersMessage)) + "\n" +
                "}\n";
    }
}
