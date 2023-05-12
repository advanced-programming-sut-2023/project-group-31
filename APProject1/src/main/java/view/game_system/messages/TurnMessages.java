package view.game_system.messages;

public enum TurnMessages {
    INVALID_BUILDING_TYPE("Your building type is invalid!"),
    OUT_OF_MAP("Your building coordinate is out of map!"),
    BANNED_AREA("you can not put the building on that area!"),

    THERE_IS_BUILDING_ON_AREA("There is building on that area!")
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
