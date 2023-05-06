package model.game_stuff.enums;

public enum Items {
    MEAT("meat", ItemTypes.FOOD),
    APPLE("apple", ItemTypes.FOOD),
    CHEESE("cheese", ItemTypes.FOOD),
    BREAD("bread", ItemTypes.FOOD),
    HOPS("hops", ItemTypes.FOOD),
    ALE("ale", ItemTypes.FOOD),
    WHEAT("wheat", ItemTypes.RAW_MATERIAL),
    FLOUR("flour", ItemTypes.RAW_MATERIAL),

    WOOD("wood", ItemTypes.RAW_MATERIAL),
    STONE("stone", ItemTypes.RAW_MATERIAL),
    IRON("iron", ItemTypes.RAW_MATERIAL),
    PITCH("pitch", ItemTypes.RAW_MATERIAL),

    BOW("bow", ItemTypes.WEAPON),
    CROSSBOW("crossbow", ItemTypes.WEAPON),
    SPEAR("spear", ItemTypes.WEAPON),
    FIRE("fire",ItemTypes.WEAPON),
    PIKE("pike", ItemTypes.WEAPON),
    MACE("mace", ItemTypes.WEAPON),
    SWORDS("swords", ItemTypes.WEAPON),
    LEATHER_ARMOR("leather armor", ItemTypes.WEAPON),
    METAL_ARMOR("metal armor", ItemTypes.WEAPON),
    HORSE("horse", ItemTypes.WEAPON),

    POPULARITY("popularity", ItemTypes.FACTOR),
    POPULARITY_GROWTH_RATE("popularity growth rate", ItemTypes.FACTOR),
    POPULATION("population", ItemTypes.FACTOR),
    POPULATION_GROWTH_RATE("population growth rate", ItemTypes.FACTOR),
    FEAR("fear", ItemTypes.FACTOR),
    FEAR_GROWTH_RATE("fear growth rate", ItemTypes.FACTOR),
    EFFICIENCY("efficiency", ItemTypes.FACTOR),
    GOLD("gold", ItemTypes.FACTOR),
    NUMBER_OF_PEASANTS("number of peasants", ItemTypes.FACTOR),
    ;
    private String name;
    private ItemTypes type;

    Items(String name, ItemTypes type) {
        this.name = name;
        this.type = type;
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
}
