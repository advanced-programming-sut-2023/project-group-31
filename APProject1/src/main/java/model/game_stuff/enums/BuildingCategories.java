package model.game_stuff.enums;

public enum BuildingCategories {
    CASTLE("castle"),
    FARM("farm"),
    INDUSTRY("industry"),
    WEAPON("weapon"),
    TOWN("town"),
    FOOD_PROCESSOR("food processor"),
    ;
    private String str;

    BuildingCategories(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
