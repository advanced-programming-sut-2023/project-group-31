package view.game_system.messages;

public enum UnitMessages {
    MESSAGE(""),
    EMPTY_BLOCK(""),
    SUCCESS("successful!"),
    INVALID_COMMAND("invalid command!"),
    NO_SUCH_TROOP("no such troop!"),
    COORDINATE_OUT_OF_BOUND("the coordinate is out of bound!"),
    NOTHING_TO_ATTACK_TO("there is nothing to attack to"),
    ;
    private String txt;
    UnitMessages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public UnitMessages setAndGetTxt(String txt){
        this.txt=txt;
        return this;
    }
}
