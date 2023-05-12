package model.game_stuff;

import model.User;
import model.game_stuff.buildings.Storage;
import model.game_stuff.enums.Items;
import model.game_stuff.people.Worker;

import java.util.ArrayList;

public class Government {
    private Game game;
    private final User owner;
    private String name;
    private Possession possession;
    private final ArrayList<Storage> stockpiles;
    private final ArrayList<Storage > granaries;
    private final ArrayList<Storage> weaponries;
    private ArrayList<Building> buildings;
    private ArrayList<Troop> troops;
    private ArrayList<Worker> workers;
    private Block LordsHouse;
    private Colors color;
    //private int gold;
    //private int numberOfPeasants;
    private int population;
    private int populationGrowthRate;
    private int popularity;
    private int popularityGrowthRate;
    private int fearRate;
    private int efficiency;
    private int maxPopulation;
    private ArrayList<Trade> tradeHistory;

    {
        possession = new Possession();
        //gold = 0;
        //numberOfPeasants = 8;
        population = 8;
        populationGrowthRate = 2; //TODO: sakhtan e formul
        popularity = 100;
        popularityGrowthRate = 0;
        fearRate = 0;
        efficiency = 8; //TODO: rabete

        stockpiles = new ArrayList<>();
        granaries = new ArrayList<>();
        weaponries = new ArrayList<>();
        tradeHistory = new ArrayList<>();
        buildings = new ArrayList<>();
        workers = new ArrayList<>();
        troops = new ArrayList<>();
    }

    public Government(User owner, Colors color) {
        this.owner = owner;
        this.name = owner.getNickname();
        this.color = color;
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

    public ArrayList<Worker> getWorkers() {
        return workers;
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

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
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
        stockpiles.add(storage);
    }

    public void addWeaponry(Storage storage) {
        stockpiles.add(storage);
    }
    public void removeStockpile(Storage storage) {
        stockpiles.remove(storage);
    }

    public void removeGranary(Storage storage) {
        stockpiles.remove(storage);
    }

    public void removeWeaponry(Storage storage) {
        stockpiles.remove(storage);
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
}
