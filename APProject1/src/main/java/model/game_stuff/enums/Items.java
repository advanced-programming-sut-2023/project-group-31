package model.game_stuff.enums;

public enum Items {
    MEAT("meat", ItemTypes.FOOD,10),
    APPLE("apple", ItemTypes.FOOD,10),
    CHEESE("cheese", ItemTypes.FOOD,10),
    BREAD("bread", ItemTypes.FOOD,10),
    HOPS("hops", ItemTypes.FOOD,10),
    ALE("ale", ItemTypes.FOOD,10),
    WHEAT("wheat", ItemTypes.RAW_MATERIAL,15),
    FLOUR("flour", ItemTypes.RAW_MATERIAL,15),

    WOOD("wood", ItemTypes.RAW_MATERIAL,15),
    STONE("stone", ItemTypes.RAW_MATERIAL,15),
    IRON("iron", ItemTypes.RAW_MATERIAL,15),
    PITCH("pitch", ItemTypes.RAW_MATERIAL,15),

    BOW("bow", ItemTypes.WEAPON,20),
    CROSSBOW("crossbow", ItemTypes.WEAPON,20),
    SPEAR("spear", ItemTypes.WEAPON,20),
    FIRE("fire",ItemTypes.WEAPON,20),
    PIKE("pike", ItemTypes.WEAPON,20),
    MACE("mace", ItemTypes.WEAPON,20),
    SWORDS("swords", ItemTypes.WEAPON,20),
    LEATHER_ARMOR("leather armor", ItemTypes.WEAPON,20),
    METAL_ARMOR("metal armor", ItemTypes.WEAPON,20),
    HORSE("horse", ItemTypes.WEAPON,20),

    POPULARITY("popularity", ItemTypes.FACTOR,0),
    POPULARITY_GROWTH_RATE("popularity growth rate", ItemTypes.FACTOR,0),
    POPULATION("population", ItemTypes.FACTOR,0),
    POPULATION_GROWTH_RATE("population growth rate", ItemTypes.FACTOR,0),
    FEAR("fear", ItemTypes.FACTOR,0),
    FEAR_GROWTH_RATE("fear growth rate", ItemTypes.FACTOR,0),
    EFFICIENCY("efficiency", ItemTypes.FACTOR,0),
    GOLD("gold", ItemTypes.FACTOR,0),
    NUMBER_OF_PEASANTS("number of peasants", ItemTypes.FACTOR,0),
    ;
    private String name;
    private ItemTypes type;
    private int cost;

    Items(String name, ItemTypes type,int cost) {
        this.name = name;
        this.type = type;
        this.cost=cost;
    }

    public String getName() {
        return name;
    }

    public ItemTypes getType() {
        return type;
    }
    public static Items getItemByName(String name) {
        for (Items item : values()) {
            if(item.name.equals(name))
                return item;
        }
        return null;
    }

    public int getCost() {
        return cost;
    }
}
