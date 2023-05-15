package model.game_stuff;

import model.User;
import model.game_stuff.buildings.Producer;
import model.game_stuff.buildings.Storage;
import model.game_stuff.buildings.enums.StorageTypes;
import model.game_stuff.enums.ItemTypes;
import model.game_stuff.enums.Items;
import model.game_stuff.people.Troop;
import model.game_stuff.people.Worker;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private Game game;
    private final User owner;
    private Troop lord;
    private String name;
    private Possession possession;
    private final ArrayList<Storage> stockpiles;
    private final ArrayList<Storage > granaries;
    private final ArrayList<Storage> weaponries;
    private ArrayList<Building> buildings;
    private ArrayList<Working> workingsBuildings;
    private ArrayList<Troop> troops;
    private HashMap<Items, Integer> popularityFactors;
    private Block LordsHouse;
    private Colors color;
    //private int gold;
    //private int numberOfPeasants;
    private int population;
    private int populationGrowthRate;
    private int turnsToWaitForNewPeasant;
    private int popularityGrowthRate;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int religionRate;
    private int fearRate;
    private double efficiency;
    private int maxPopulation;
    private Waiter populationWaiter;
    private ArrayList<Trade> tradeHistory;

    {
        possession = new Possession(this);
        //gold = 0;
        //numberOfPeasants = 8;
        population = 8;
        possession.setPeasant(8);
        populationGrowthRate = 2; //TODO: sakhtan e formul
        popularity = 100;
        foodRate=0;
        fearRate = 0;
        religionRate=0;
        popularityGrowthRate=0;
        taxRate=0;
        efficiency = 1; //TODO: rabete
        turnsToWaitForNewPeasant = 2;
        maxPopulation = 16;

        stockpiles = new ArrayList<>();
        granaries = new ArrayList<>();
        weaponries = new ArrayList<>();
        tradeHistory = new ArrayList<>();
        buildings = new ArrayList<>();
        troops = new ArrayList<>();
        workingsBuildings = new ArrayList<>();
        populationWaiter = new Waiter(2);
        popularityFactors = new HashMap<>();

        popularityFactors.put(Items.FEAR_POPULARITY, 0);
    }

    public Government(User owner, Colors color) {
        this.owner = owner;
        this.name = owner.getUsername();
        this.color = color;
    }


    public ArrayList<Working> getWorkingsBuildings() {
        return workingsBuildings;
    }

    public void setLord(Troop lord) {
        this.lord = lord;
    }

    public Troop getLord() {
        return lord;
    }

    public void giveWorker(Producer producer) {
        if(possession.getPeasant() > 0) {
            possession.setPeasant(possession.getPeasant() - 1);
            producer.setWorker(new Worker(producer, producer.getWorkerType()));
        }
    }

    public void nextTurn() {
        fixPopularityGrowthRate();
        risePopularity();
        setTurnsToWaitForNewPeasant();
        fixPopulation();
    }

    private void fixPopularityGrowthRate() {
        popularityGrowthRate = fearRate * 4 + foodRate * 4 + religionRate - taxRate * 2;
    }

    private void risePopularity() {
        popularity += popularityGrowthRate;
        if(popularity > 100) {
            popularity = 100;
        } else if(popularity < 0) {
            popularity = 0;
        }
    }

    private void setTurnsToWaitForNewPeasant() {
        if(popularity >= 70 || popularity <= 30) {
            populationWaiter.setTurnsToWait(2);
        } else {
            populationWaiter.setTurnsToWait(4);
        }
    }
    private void fixPopulation() {
        if(popularity >= 55) {
            if(populationWaiter.isTheTurn() && population < maxPopulation) {
                possession.setPeasant(possession.getPeasant() + 1);
            }
        } else if(popularity <= 45) {
            if(populationWaiter.isTheTurn() && population > 0) {
                possession.setPeasant(possession.getPeasant() - 1);
            }
        }
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }
    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public model.game_stuff.Possession getPossession() {
        return possession;
    }

    public User getOwner() {
        return owner;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public ArrayList<Storage> getStockpiles() {
        return stockpiles;
    }

    public ArrayList<Storage> getGranaries() {
        return granaries;
    }

    public ArrayList<Storage> getWeaponries() {
        return weaponries;
    }

    /*public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getNumberOfPeasants() {
        return numberOfPeasants;
    }

    public void setNumberOfPeasants(int numberOfPeasants) {
        this.numberOfPeasants = numberOfPeasants;
    }*/

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulationGrowthRate() {
        return populationGrowthRate;
    }

    public void setPopulationGrowthRate(int populationGrowthRate) {
        this.populationGrowthRate = populationGrowthRate;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public int getPopularityGrowthRate() {
        return popularityGrowthRate;
    }

    public void setPopularityGrowthRate(int popularityGrowthRate) {
        this.popularityGrowthRate = popularityGrowthRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void addStockpile(Storage storage) {
        stockpiles.add(storage);
    }

    public void addGranary(Storage storage) {
        granaries.add(storage);
    }

    public void addWeaponry(Storage storage) {
        weaponries.add(storage);
    }
    public void removeStockpile(Storage storage) {
        stockpiles.remove(storage);
    }

    public void removeGranary(Storage storage) {
        granaries.remove(storage);
    }

    public void removeWeaponry(Storage storage) {
        weaponries.remove(storage);
    }

    public Block getLordsHouse() {
        return LordsHouse;
    }

    public void setLordsHouse(Block lordsHouse) {
        LordsHouse = lordsHouse;
    }
    public void addTrade(Trade trade) {
        tradeHistory.add(trade);
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getReligionRate() {
        return religionRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setReligionRate(int religionRate) {
        this.religionRate = religionRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }
    public int getNumberOfAnItem(Items item) {
        return possession.getItem(item);
    }

    public void reduceItem(Items item, int amount) {
        if(item.getType().equals(ItemTypes.FOOD)) {
            getFromStorage(granaries, item, amount);
        } else if(item.getType().equals(ItemTypes.RAW_MATERIAL)) {
            getFromStorage(stockpiles, item, amount);
        } else if(item.getType().equals(ItemTypes.WEAPON)) {
            getFromStorage(weaponries, item, amount);
        }
    }

    private void getFromStorage(ArrayList<Storage> storages, Items item, int amount) {
        Storage storage;
        int amountAvailable;
        for(int i = 0; amount > 0; i++) {
            storage = storages.get(i);
            amountAvailable = storage.getProperties().get(item);
            if(amount > amountAvailable) {
                storage.removeProduct(item, amountAvailable);
                amount -= amountAvailable;
            } else {
                storage.removeProduct(item, amount);
                amount = 0;
            }
        }
    }

    public void addItem(Items item, int amount) {
        if(item.getType().equals(ItemTypes.FOOD)) {
            getFromStorage(granaries, item, amount);
        } else if(item.getType().equals(ItemTypes.RAW_MATERIAL)) {
            getFromStorage(stockpiles, item, amount);
        } else if(item.getType().equals(ItemTypes.WEAPON)) {
            getFromStorage(weaponries, item, amount);
        }
    }

    public void addToStorage(ArrayList<Storage> storages, Items item, int amount) {
        Storage storage;
        int amountAvailable;
        for(int i = 0; amount > 0; i++) {
            storage = storages.get(i);
            amountAvailable = storage.getCapacityLeft();
            if(amount > amountAvailable) {
                storage.addProduct(item, amountAvailable);
                amount -= amountAvailable;
            } else {
                storage.addProduct(item, amount);
                amount = 0;
            }
        }
    }

    public void terminate() {
        for (Building building : buildings) {
            building.terminate();
        }
        for (Troop troop : troops) {
            troop.die();
        }
        game.getPlayers().remove(this);
    }
}
