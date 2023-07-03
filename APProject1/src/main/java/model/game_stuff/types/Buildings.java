package model.game_stuff.types;

import model.game_stuff.enums.BuildingCategories;
import model.game_stuff.enums.Textures;

public enum Buildings {
    SMALL_STONE_GATEHOUSE(2, 1, "small stone gatehouse", BuildingCategories.CASTLE, 0, 0, 0, 0, 2000),
    BIG_STONE_GATEHOUSE(2, 3, "big stone gatehouse", BuildingCategories.CASTLE, 20, 0, 0, 0, 2000),
    DRAWBRIDGE(1, 2, "drawbridge", BuildingCategories.CASTLE, 0, 10, 0, 0, 1000),
    LOOKOUT_TOWER(2, 2, "lookout tower", BuildingCategories.CASTLE, 10, 0, 0, 0, 1200),
    PERIMETER_TOWER(2, 2, "perimeter tower", BuildingCategories.CASTLE, 10, 0, 0, 0, 1000),
    DEFENCE_TURRET(2, 2, "defence turret", BuildingCategories.CASTLE, 15, 0, 0, 0, 1200),
    SQUARE_TOWER(2, 2, "square tower", BuildingCategories.CASTLE, 35, 0, 0, 0, 2500),
    ROUND_TOWER(2, 2, "round tower", BuildingCategories.CASTLE, 40, 0, 0, 0, 3000),
    ARMOURY(2, 2, "armoury", BuildingCategories.CASTLE, 0, 5, 0, 0, 200),
    BARRACKS(2, 2, "barracks", BuildingCategories.CASTLE, 15, 0, 0, 0, 200),
    MERCENARY_POST(2, 2, "mercenary post", BuildingCategories.CASTLE, 0, 10, 0, 0, 200),
    ENGINEER_GUID(2, 2, "engineer guid", BuildingCategories.CASTLE, 0, 10, 0, 0, 100),
    INN(2, 2, "inn", BuildingCategories.FOOD_PROCESSOR, 0, 20, 1, 0, 100),
    KILLING_PIT(2, 2, "killing pit", BuildingCategories.CASTLE, 0, 6, 0, 0, 0),
    MARKET(2, 2, "market", BuildingCategories.INDUSTRY, 0, 5, 1, 0, 0),
    OX_TETHER(2, 2, "ox tether", BuildingCategories.INDUSTRY, 0, 5, 1, 0, 0),
    MILL(2, 2, "mill", BuildingCategories.FOOD_PROCESSOR, 0, 20, 3, 0, 0),
    QUARRY(2, 2, "quarry", BuildingCategories.INDUSTRY, 0, 20, 3, 0, 0, Textures.QUARRY),
    PITCH_DITCH(2, 2, "pitch ditch", BuildingCategories.CASTLE, 0, 20, 1, 0, 0),
    IRON_MINE(2, 2, "iron mine", BuildingCategories.INDUSTRY, 0, 20, 2, 0, 0, Textures.IRON),
    //SIEGETENT("Siege tent",),//nadarim
    STOCKPILE(2, 2, "stockpile", BuildingCategories.INDUSTRY, 0, 0, 0, 0, 0),
    //WATERPOT("Water pot",),//nadarim
    CATHEDRAL(2, 2, "cathedral", BuildingCategories.TOWN, 0, 0, 0, 0, 1000),
    CHURCH(3, 3, "church", BuildingCategories.TOWN, 0, 0, 0, 0, 250),
    HOVEL(2, 2, "hovel", BuildingCategories.TOWN, 0, 6, 0, 0, 0),
    //PITCHRIG("Pitch rig",),//need to define pitch as object
    GRANARY(2, 2, "granary", BuildingCategories.FOOD_PROCESSOR, 0, 5, 0, 0, 0),
    WOODCUTTER(2, 2, "woodcutter", BuildingCategories.INDUSTRY, 0, 3, 1, 0, 0),
    ARMOURER(2, 2, "armourer", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    //to get removed
    LEATHER_ARMOURER(2, 2, "leather armourer", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    //blacksmith
    MACE_MAKER(2, 2, "mace maker", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    //pole turner
    PIKE_MAKER(2, 2, "pike maker", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    //fletcher
    CROSSBOW_MAKER(2, 2, "crossbow maker", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    FLETCHER(2, 2, "fletcher", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    POLE_TURNER(2, 2, "pole turner", BuildingCategories.WEAPON, 0, 10, 1, 0, 100),
    BLACKSMITH(2, 2, "black smith", BuildingCategories.WEAPON, 0, 20, 1, 0, 100),
    OIL_SMELTER(2, 2, "oil smelter", BuildingCategories.CASTLE, 10, 0, 1, 0, 100),
    CAGED_WAR_DOGS(2, 2, "caged war dogs", BuildingCategories.CASTLE, 0, 10, 0, 0, 100),
    STABLE(2, 2, "stable", BuildingCategories.CASTLE, 0, 20, 0, 0, 400),
    APPLE_ORCHARD(3, 3, "apple orchard", BuildingCategories.FARM, 0, 5, 1, 0, 0, Textures.GROSS),
    DIARY_FARMER(2, 2, "diary farmer", BuildingCategories.FARM, 0, 10, 1, 0, 0),
    HOPS_FARMER(3, 3, "hops farmer", BuildingCategories.FARM, 0, 15, 1, 0, 0, Textures.GROSS),
    HUNTER_POST(2, 2, "hunter post", BuildingCategories.FARM, 0, 5, 1, 0, 0),
    WHEAT_FARMER(3, 3, "wheat farmer", BuildingCategories.FARM, 0, 15, 1, 0, 0, Textures.GROSS),
    BAKERY(2, 2, "bakery", BuildingCategories.FOOD_PROCESSOR, 0, 10, 1, 0, 0),
    BREWER(2, 2, "brewer", BuildingCategories.FOOD_PROCESSOR, 0, 10, 0, 0, 0),
    WALL(1, 1,"wall", BuildingCategories.CASTLE, 5, 0, 0, 0 , 0);
    private String name;
    private double rockNeeded;
    private double woodNeeded;
    private double workerNeeded;
    private double ironNeeded;
    private BuildingCategories category;

    private double goldNeeded;

    private int height;

    private int width;

    private final Textures possibleTexture;

    Buildings(int height, int width, String name, BuildingCategories category, double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded, double goldNeeded, Textures possibleTexture) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture = possibleTexture;
        this.height = height;
        this.width = width;
        this.name = name;
        this.goldNeeded = goldNeeded;
        this.category = category;
    }

    Buildings(int height, int width, String name, BuildingCategories category, double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded, double goldNeeded) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture = null;
        this.height = height;
        this.width = width;
        this.name = name;
        this.goldNeeded = goldNeeded;
        this.category = category;
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

    public int getHeight() {
        return height;
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

    public BuildingCategories getCategory() {
        return category;
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
        if ((!texture.equals(Textures.CLIFF))&&(!texture.equals(Textures.WATER))&&
            (possibleTexture == null || texture==possibleTexture)) {
            return true;
        }
        return false;
    }

    public String getUrl() {
        if(this == SMALL_STONE_GATEHOUSE || this == DRAWBRIDGE || this == BIG_STONE_GATEHOUSE) {
            return Buildings.class.getResource("/Media/Buildings/" + category.getStr() + "/" + name + "/" + name + "0.png").toString();
        }
        return Buildings.class.getResource("/Media/Buildings/" + category.getStr() + "/" + name + "/" + name + ".png").toString();
    }
}
