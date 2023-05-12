package view.game_system.messages;

public enum UnitMessages {
    MESSAGE(""),
    EMPTY_BLOCK(""), SUCCESS("success");
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
