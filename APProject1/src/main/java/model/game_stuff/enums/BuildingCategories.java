package model.game_stuff.enums;

public enum BuildingCategories {
    CASTLE("Castle"),
    FARM("Farm"),
    INDUSTRY("Industry"),
    WEAPON("Weapon"),
    TOWN("Town"),
    FOOD_PROCESSOR("Food processor"),
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
