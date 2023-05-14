package view.game_system.messages;

public enum TurnMessages {
    INVALID_BUILDING_TYPE("Your building type is invalid!"),
    OUT_OF_MAP("Your building coordinate is out of map!"),
    BANNED_AREA("you can not put the building on that area!"),

    THERE_IS_BUILDING_ON_AREA("There is building on that area!"),
    SUCCESS("Success"), EMPTY_PLACE("There is no building here."),
    MESSAGE(""),
    MERCENARY_POST(""),
    BARRACK(""),
    ENEMY_IS_CLOSE("Enemy soldiers are very close to the wall."),
    NOT_ENOUGH_REQUIREMENT("not enough requirement"),
    GAME_FINISHED("game finished!"),
    INVALID_COMMAND("invalid command!"),
    COORDINATE_OUT_OF_BOUND("coordinate out of bounds!"),
    NO_UNIT_FOUND("no unit found!"),
    ;
    private String txt;
    TurnMessages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public TurnMessages setAndGetTxt(String txt){
        this.txt=txt;
        return this;
    }

    public static TurnMessages getMessageByTxt(String txt) {
        for (TurnMessages turnMessage : values()) {
            if(turnMessage.txt.equals(txt)) {
                return turnMessage;
            }
        }
        return null;
    }
}
