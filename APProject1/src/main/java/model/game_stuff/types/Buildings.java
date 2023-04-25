package model.game_stuff.types;

public enum Buildings {
    NULL(null,0,0,0,0,0,false),
    SMALLSTONEGATEHOUSE("Small stone gatehouse",0,0,0,0,1000,true),
    BIGSTONEGATEHOUSE("Big stone gatehouse",20,0,0,0,2000,true),
    DRAWBRIDGE("Drawbridge",0,10,0,0,1000,false),
    LOOKOUTTOWER("Lookout tower",10,0,0,0,1200,),
    PREMITERTOWER("Premiter tower",10,0,0,0,1000,false),
    TURRET("Turret",15,0,0,0,1200,false),
    SQURETURRET("Squre turret",35,0,0,0,2500,true),
    CIRCULARTURRET("Circular turret",40,0,0,0,3000,true),
    ARMOURY("Armoury",0,5,0,0,200,false),
    BARRACK("Barrack",15,0,0,0,200,false),
    MERCENARYPOST("Mercenary post",0,10,0,0,200,false),
    ENGINEERGUILD("Engineer gulid",0,10,0,0,100,200,false),
    INN("Inn",0,20,1,0,100,200,false),
    KILLINGPIT("Killing pit",0,6,0,0,0,200,true),
    MARKET("Market",0,5,1,0,0,200,false),
    OXTETHER("Ox tether",0,5,1,0,0,200,false),
    MILL("Mill",0,20,3,0,0,200,false),
    QUARRY("Quarry",0,20,3,0,0,200,false),
    PITCHDITCH("Pitch ditch",0,20,1,0,0,200,false),
    IRONMINE("Iron mine",0,20,2,0,0,300,false),
    SIEGETENT("Siege tent",),//nadarim
    STOCKPILE("Stockpile",0,0,0,0,0,100,false),
    WATERPOT("Water pot",),//nadarim
    CATHEDRAL("Cathedral",0,0,0,0,1000,1500,false),
    CHURCH("Church",0,0,0,0,250,600,false),
    HOVEL("Hovel",0,6,0,0,0,200,false),
    PITCHRIG("Pitch rig",),//need to define pitch as object
    FOODSTOCKPILE("Food stockpile",0,5,0,0,0,0,false),
    WOODCUTTER("Wood cutter",0,3,1,0,0,100,false),
    ARMOURER("Armourer",0,20,1,0,100,200,false),
    FLETCHER("Fletcher",0,20,1,0,100,200,false),
    POLETURNER("Poleturner",0,10,1,0,100,200,false),
    BLACKSMITH("Black smith",0,20,1,0,100,200,false),
    OILSMELTER("Oil smelter",10,0,1,0,100,200,false),
    CAGEWARDOGS("Cage war dogs",0,10,0,0,100,100,false),
    STABLE("Stable",0,20,0,0,400,300,false),
    APPLEGARDEN("Apple garden",0,5,1,0,0,200,false),
    DIARYPRODUCTS("Diary products",0,10,1,0,0,200,false),
    GRAINFARM("Grain farm",0,15,1,0,0,200,false),
    HUNTINGPOST("Hunting post",0,5,1,0,0,200,false),
    WHEATFARM("Wheat farm",0,15,1,0,0,200,false),
    BAKERY("Bakery",0,10,1,0,0,300,false),
    BREWERY("Brewery",0,10,0,0,0,300,false);
    private String name;
    private double hp;
    private boolean enternecePermission;
    private double rockNeeded;
    private double woodNeeded;
    private double workerNeeded;
    private double ironNeeded;
    private double goldNeeded;
    Buildings(String name,double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded,double goldNeeded,double hp,boolean enternecePermission) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.hp=hp;
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
