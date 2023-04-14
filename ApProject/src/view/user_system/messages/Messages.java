package view.user_system.messages;

public enum Messages {
    INVALID_USERNAME_FORMAT("Invalid username format: "),
    //TODO: add enums
    ;
    private String txt;
    private String input; //the part of input in witch error occurred

    Messages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void addInput(String input) {
        txt+= input;
    }

    public String getTxt() {
        return txt;
    }
}
