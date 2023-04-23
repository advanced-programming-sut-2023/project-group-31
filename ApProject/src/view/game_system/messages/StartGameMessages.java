package view.game_system.messages;

public enum StartGameMessages {
    SUCCESS("successful!"),
    MAP_NOT_FOUND("map not found!"),
    INVALID_COMMAND("invalid command!"),
    COORDINATE_OUT_OF_BOUND("coordinate out of bound!"),
    INVALID_TEXTURE_TYPE("invalid texture type!"),
    BLOCK_IS_NOT_EMPTY("the block is not empty!"),
    ;
    private String txt;
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    StartGameMessages(String txt) {
        this.txt = txt;
        this.input = "";
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt + input;
    }
}
