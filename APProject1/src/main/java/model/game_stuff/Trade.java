package model.game_stuff;

import model.game_stuff.enums.Items;

import java.util.ArrayList;
import java.util.HashMap;

public class Trade {
    private static int idCounter = 1001;
    //dar kol hame trade ha yek ja nistan va agar accept shan faghat dar history melat mimoonan
    private static ArrayList<Trade> liveTrades;
    private int id; //mahdoodiat tedad e trade darim
    private HashMap<Items, Integer> providedItems;
    private HashMap<Items, Integer> askedItems;
    private Government owner;
    private Government askedGovernment;
    private String ownersMessage;
    private String othersMessage;
    private boolean isWatched;

    //TODO: hazf tavabe be dard nakhor

    static {
        liveTrades = new ArrayList<>();
    }
    {
        providedItems = new HashMap<>();
        askedItems = new HashMap<>();
        isWatched = false;
    }

    //kami logic
    public Trade(Government owner, Government askedGovernment) {
        this.owner = owner;
        this.askedGovernment = askedGovernment;
        this.id = idCounter;
        idCounter++;
        liveTrades.add(this);
    }

    public void removeTrade() {
        liveTrades.remove(this);
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

    public Government getOwner() {
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

    public boolean isWatched() {
        return isWatched;
    }

    public Government getAskedPlayer() {
        return askedGovernment;
    }

    public void setProvidedItems(HashMap<Items, Integer> providedItems) {
        this.providedItems = new HashMap<>(providedItems);
    }

    public void setAskedItems(HashMap<Items, Integer> askedItems) {
        this.askedItems = new HashMap<>(askedItems);
    }

    public static ArrayList<Trade> getLiveTrades() {
        return liveTrades;
    }
    public static Trade getTradeById(int id) {
        for (Trade liveTrade : liveTrades) {
            if(liveTrade.id == id) {
                return liveTrade;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Trade :" + id + "{\n" +
                "from :" + owner.getName() + " " + owner.getColor().getName() + (ownersMessage == null ? "" : (": " + ownersMessage)) + "\n" +
                "providedItems=" + providedItems + "\n" +
                "askedItems=" + askedItems + "\n" +
                "to :" + askedGovernment.getName() + " " + askedGovernment.getColor().getName() + (othersMessage == null ? "" : (": " + othersMessage)) + "\n" +
                "}\n";
    }
}
