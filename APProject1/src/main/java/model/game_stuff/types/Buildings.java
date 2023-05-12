package model.game_stuff.types;

import model.game_stuff.enums.Textures;

import java.util.ArrayList;
import java.util.List;

public enum Buildings {
    NULL(1, 1, null, 0, 0, 0, 0, 0),
    SMALLSTONEGATEHOUSE(2, 1, "small stone gatehouse", 0, 0, 0, 0, 2000),
    BIGSTONEGATEHOUSE(2, 3, "big stone gatehouse", 20, 0, 0, 0, 2000),
    DRAWBRIDGE(1, 2, "drawbridge", 0, 10, 0, 0, 1000),
    LOOKOUTTOWER(2, 2, "Lookout tower", 10, 0, 0, 0, 1200),
    PREMITERTOWER(2, 2, "Premiter tower", 10, 0, 0, 0, 1000),
    TURRET(2, 2, "Turret", 15, 0, 0, 0, 1200),
    SQUARETURRET(2, 2, "Square turret", 35, 0, 0, 0, 2500),
    CIRCULARTURRET(2, 2, "Circular turret", 40, 0, 0, 0, 3000),
    ARMOURY(2, 2, "Armoury", 0, 5, 0, 0, 200),
    BARRACK(2, 2, "Barrack", 15, 0, 0, 0, 200),
    MERCENARYPOST(2, 2, "Mercenary post", 0, 10, 0, 0, 200),
    ENGINEERGUILD(2, 2, "Engineer gulid", 0, 10, 0, 0, 100),
    INN(2, 2, "Inn", 0, 20, 1, 0, 100),
    KILLINGPIT(2, 2, "Killing pit", 0, 6, 0, 0, 0),
    MARKET(2, 2, "Market", 0, 5, 1, 0, 0),
    OXTETHER(2, 2, "Ox tether", 0, 5, 1, 0, 0),
    MILL(2, 2, "Mill", 0, 20, 3, 0, 0),
    QUARRY(2, 2, "Quarry", 0, 20, 3, 0, 0, Textures.QUARRY),
    PITCHDITCH(2, 2, "Pitch ditch", 0, 20, 1, 0, 0),
    IRONMINE(2, 2, "Iron mine", 0, 20, 2, 0, 0, Textures.IRON),
    //SIEGETENT("Siege tent",),//nadarim
    STOCKPILE(2, 2, "Stockpile", 0, 0, 0, 0, 0),
    //WATERPOT("Water pot",),//nadarim
    CATHEDRAL(2, 2, "Cathedral", 0, 0, 0, 0, 1000),
    CHURCH(3, 3, "Church", 0, 0, 0, 0, 250),
    HOVEL(2, 2, "Hovel", 0, 6, 0, 0, 0),
    //PITCHRIG("Pitch rig",),//need to define pitch as object
    FOODSTOCKPILE(2, 2, "Food stockpile", 0, 5, 0, 0, 0),
    WOODCUTTER(2, 2, "Wood cutter", 0, 3, 1, 0, 0),
    ARMOURER(2, 2, "Armourer", 0, 20, 1, 0, 100),
    LEATHER_ARMOURER(2, 2, "Leather armourer", 0, 20, 1, 0, 100),
    MACE_MAKER(2, 2, "Mace maker", 0, 20, 1, 0, 100),
    PIKE_MAKER(2, 2, "Pike maker", 0, 20, 1, 0, 100),
    CROSSBOW_MAKER(2, 2, "Crossbow maker", 0, 20, 1, 0, 100),
    FLETCHER(2, 2, "Fletcher", 0, 20, 1, 0, 100),
    POLETURNER(2, 2, "Poleturner", 0, 10, 1, 0, 100),
    BLACKSMITH(2, 2, "Black smith", 0, 20, 1, 0, 100),
    OILSMELTER(2, 2, "Oil smelter", 10, 0, 1, 0, 100),
    CAGEWARDOGS(2, 2, "Cage war dogs", 0, 10, 0, 0, 100),
    STABLE(2, 2, "Stable", 0, 20, 0, 0, 400),
    APPLEGARDEN(3, 3, "Apple garden", 0, 5, 1, 0, 0, Textures.GROSS),
    DIARYPRODUCTS(2, 2, "Diary products", 0, 10, 1, 0, 0),
    GRAINFARM(3, 3, "Grain farm", 0, 15, 1, 0, 0, Textures.GROSS),
    HUNTINGPOST(2, 2, "Hunting post", 0, 5, 1, 0, 0),
    WHEATFARM(3, 3, "Wheat farm", 0, 15, 1, 0, 0, Textures.GROSS),
    BAKERY(2, 2, "Bakery", 0, 10, 1, 0, 0),
    BREWERY(2, 2, "Brewery", 0, 10, 0, 0, 0);
    private String name;
    private double rockNeeded;
    private double woodNeeded;
    private double workerNeeded;
    private double ironNeeded;

    private double goldNeeded;

    private int length;

    private int width;

    private final Textures possibleTexture;

    Buildings(int length, int width, String name, double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded, double goldNeeded, Textures possibleTexture) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture = possibleTexture;
    }

    Buildings(int length, int width, String name, double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded, double goldNeeded) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture = null;
    }

    public void setRockNeeded(double rockNeeded) {
        this.rockNeeded = rockNeeded;
    }

    public void setWoodNeeded(double woodNeeded) {
        this.woodNeeded = woodNeeded;
    }

    public void setWorkerNeeded(double workerNeeded) {
        this.workerNeeded = workerNeeded;
    }

    public void setIronNeeded(double ironNeeded) {
        this.ironNeeded = ironNeeded;
    }

    public String getName() {
        return name;
    }

    public double getRockNeeded() {
        return rockNeeded;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public double getWoodNeeded() {
        return woodNeeded;
    }

    public double getWorkerNeeded() {
        return workerNeeded;
    }

    public double getIronNeeded() {
        return ironNeeded;
    }

    public double getGoldNeeded() {
        return goldNeeded;
    }

    public Textures getPossibleTexture() {
        return possibleTexture;
    }

    public static Buildings getBuildingByName(String name) {
        for (Buildings building : values()) {
            if (building.name.equalsIgnoreCase(name)) {
                return building;
            }
        }
        return null;
    }

    public boolean isAreaPossible(Textures texture) {
        if ((!texture.equals(Textures.CLIFF))&&(!texture.equals(Textures.WATER))&&(possibleTexture == null || texture==possibleTexture)) {
            return true;
        }
        return false;
    }


}
