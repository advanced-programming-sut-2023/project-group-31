package model.game_stuff.people.enums;

public enum WorkerStates {
    HEADING_BACK ("heading back"),
    HEADING_STORAGE ("taking products to storage"),
    PRODUCING ("working"),
    HEADING_BACK_WITH_WOOD("coming back with wood"),
    SEARCHING_WOOD("searching for trees")
    ;
    private String name;

    WorkerStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
