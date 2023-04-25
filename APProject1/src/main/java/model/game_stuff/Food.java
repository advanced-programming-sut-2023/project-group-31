package model.game_stuff;

public enum Food {
    bread("bread"),
    meat("meat"),
    milk("milk"),
    rice("rice");

    private String name;

    Food(String name) {
        this.name = name;
    }
}
