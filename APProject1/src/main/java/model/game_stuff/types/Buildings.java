package model.game_stuff.types;

import model.game_stuff.enums.Textures;

import java.util.ArrayList;
import java.util.List;

public enum Buildings {
    NULL(null,0,0,0,0,0),
    BIGSTONEGATEHOUSE("big stone gatehouse",20,0,0,0,2000),
    DRAWBRIDGE("drawbridge",0,10,0,0,1000),
    LOOKOUTTOWER("Lookout tower",10,0,0,0,1200),
    PREMITERTOWER("Premiter tower",10,0,0,0,1000),
    TURRET("Turret",15,0,0,0,1200),
    SQUARETURRET("Square turret",35,0,0,0,2500),
    CIRCULARTURRET("Circular turret",40,0,0,0,3000),
    ARMOURY("Armoury",0,5,0,0,200),
    BARRACK("Barrack",15,0,0,0,200),
    MERCENARYPOST("Mercenary post",0,10,0,0,200),
    ENGINEERGUILD("Engineer gulid",0,10,0,0,100),
    INN("Inn",0,20,1,0,100),
    KILLINGPIT("Killing pit",0,6,0,0,0),
    MARKET("Market",0,5,1,0,0),
    OXTETHER("Ox tether",0,5,1,0,0),
    MILL("Mill",0,20,3,0,0),
    QUARRY("Quarry",0,20,3,0,0,Textures.QUARRY),
    PITCHDITCH("Pitch ditch",0,20,1,0,0),
    IRONMINE("Iron mine",0,20,2,0,0,Textures.IRON),
    //SIEGETENT("Siege tent",),//nadarim
    STOCKPILE("Stockpile",0,0,0,0,0),
    //WATERPOT("Water pot",),//nadarim
    CATHEDRAL("Cathedral",0,0,0,0,1000),
    CHURCH("Church",0,0,0,0,250),
    HOVEL("Hovel",0,6,0,0,0),
    //PITCHRIG("Pitch rig",),//need to define pitch as object
    FOODSTOCKPILE("Food stockpile",0,5,0,0,0),
    WOODCUTTER("Wood cutter",0,3,1,0,0),
    ARMOURER("Armourer",0,20,1,0,100),
    LEATHER_ARMOURER("Leather armourer",0,20,1,0,100),
    MACE_MAKER("Mace maker",0,20,1,0,100),
    PIKE_MAKER("Pike maker",0,20,1,0,100),
    CROSSBOW_MAKER("Crossbow maker",0,20,1,0,100),
    FLETCHER("Fletcher",0,20,1,0,100),
    POLETURNER("Poleturner",0,10,1,0,100),
    BLACKSMITH("Black smith",0,20,1,0,100),
    OILSMELTER("Oil smelter",10,0,1,0,100),
    CAGEWARDOGS("Cage war dogs",0,10,0,0,100),
    STABLE("Stable",0,20,0,0,400),
    APPLEGARDEN("Apple garden",0,5,1,0,0),
    DIARYPRODUCTS("Diary products",0,10,1,0,0),
    GRAINFARM("Grain farm",0,15,1,0,0,Textures.GROSS),
    HUNTINGPOST("Hunting post",0,5,1,0,0),
    WHEATFARM("Wheat farm",0,15,1,0,0,Textures.GROSS),
    BAKERY("Bakery",0,10,1,0,0),
    BREWERY("Brewery",0,10,0,0,0);
    private String name;
    private double rockNeeded;
    private double woodNeeded;
    private double workerNeeded;
    private double ironNeeded;

    private double goldNeeded;

    private final Textures possibleTexture;

    Buildings(String name,double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded,double goldNeeded,Textures possibleTexture) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture=possibleTexture;
    }

    Buildings(String name,double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded,double goldNeeded) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.possibleTexture=null;
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


}
