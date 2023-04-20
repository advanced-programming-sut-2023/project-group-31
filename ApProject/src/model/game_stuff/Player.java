package model.game_stuff;

import model.User;

import java.util.ArrayList;

public class Player {
    private final User owner;
    private final ArrayList<Block> stockpilePositions;
    private final ArrayList<Block> granaryPositions;
    private final ArrayList<Block> weaponryPositions;
    private Block LordsHouse;
    private Colors color;
    private int gold;
    private int numberOfPeasants;
    private int population;
    private int populationGrowthRate;
    private int popularity;
    private int popularityGrowthRate;
    private int fearRate;
    private int efficiency;

    {
        gold = 0;
        numberOfPeasants = 8;
        population = 8;
        populationGrowthRate = 2; //TODO: sakhtan e formul
        popularity = 100;
        popularityGrowthRate = 0;
        fearRate = 0;
        efficiency = 8; //TODO: rabete

        stockpilePositions = new ArrayList<>();
        granaryPositions = new ArrayList<>();
        weaponryPositions = new ArrayList<>();
    }

    public Player(User owner, Colors color) {
        this.owner = owner;
        this.color = color;
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

    public ArrayList<Block> getStockpilePositions() {
        return stockpilePositions;
    }

    public ArrayList<Block> getGranaryPositions() {
        return granaryPositions;
    }

    public ArrayList<Block> getWeaponryPositions() {
        return weaponryPositions;
    }

    public int getGold() {
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
    }

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

    public void addStockpile(Block block) {
        stockpilePositions.add(block);
    }

    public void addGranary(Block block) {
        stockpilePositions.add(block);
    }

    public void addWeaponry(Block block) {
        stockpilePositions.add(block);
    }
    public void removeStockpile(Block block) {
        stockpilePositions.remove(block);
    }

    public void removeGranary(Block block) {
        stockpilePositions.remove(block);
    }

    public void removeWeaponry(Block block) {
        stockpilePositions.remove(block);
    }

    public Block getLordsHouse() {
        return LordsHouse;
    }

    public void setLordsHouse(Block lordsHouse) {
        LordsHouse = lordsHouse;
    }
}
