package model.game_stuff.enums;

public enum Items {
    MEAT("meat", ItemTypes.FOOD,0),
    APPLE("apple", ItemTypes.FOOD,0),
    CHEESE("cheese", ItemTypes.FOOD,0),
    BREAD("bread", ItemTypes.FOOD,0),
    HOPS("hops", ItemTypes.FOOD,0),
    ALE("ale", ItemTypes.FOOD,0),
    WHEAT("wheat", ItemTypes.RAW_MATERIAL,0),
    FLOUR("flour", ItemTypes.RAW_MATERIAL,0),

    WOOD("wood", ItemTypes.RAW_MATERIAL,0),
    STONE("stone", ItemTypes.RAW_MATERIAL,0),
    IRON("iron", ItemTypes.RAW_MATERIAL,0),
    PITCH("pitch", ItemTypes.RAW_MATERIAL,0),

    BOW("bow", ItemTypes.WEAPON,0),
    CROSSBOW("crossbow", ItemTypes.WEAPON,0),
    SPEAR("spear", ItemTypes.WEAPON,0),
    FIRE("fire",ItemTypes.WEAPON,0),
    PIKE("pike", ItemTypes.WEAPON,0),
    MACE("mace", ItemTypes.WEAPON,0),
    SWORDS("swords", ItemTypes.WEAPON,0),
    LEATHER_ARMOR("leather armor", ItemTypes.WEAPON,0),
    METAL_ARMOR("metal armor", ItemTypes.WEAPON,0),
    HORSE("horse", ItemTypes.WEAPON,0),

    POPULARITY("popularity", ItemTypes.FACTOR,0),
    POPULARITY_GROWTH_RATE("popularity growth rate", ItemTypes.FACTOR,0),
    POPULATION("population", ItemTypes.FACTOR,0),
    POPULATION_GROWTH_RATE("population growth rate", ItemTypes.FACTOR,0),
    FEAR("fear", ItemTypes.FACTOR,0),
    FEAR_GROWTH_RATE("fear growth rate", ItemTypes.FACTOR,0),
    EFFICIENCY("efficiency", ItemTypes.FACTOR,0),
    GOLD("gold", ItemTypes.FACTOR,0),
    NUMBER_OF_PEASANTS("number of peasants", ItemTypes.FACTOR,0),

    MAX_POPULARITY("max popularity", ItemTypes.FACTOR, 0),
    FEAR_POPULARITY("fear popularity", ItemTypes.FACTOR, 0),
    FOOD_POPULARITY("food popularity", ItemTypes.FACTOR, 0),
    TAX_POPULARITY("tax popularity", ItemTypes.FACTOR, 0),
    RELIGION_POPULARITY("religion popularity", ItemTypes.FACTOR, 0),
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
