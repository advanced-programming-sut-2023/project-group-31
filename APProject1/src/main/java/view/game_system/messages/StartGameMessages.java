package view.game_system.messages;

public enum StartGameMessages {
    SUCCESS("successful!"),
    MAP_NOT_FOUND("map not found!"),
    INVALID_COMMAND("invalid command!"),
    COORDINATE_OUT_OF_BOUND("coordinate out of bound!"),
    INVALID_TEXTURE_TYPE("invalid texture type!"),
    BLOCK_IS_NOT_EMPTY("the block is not empty: "),
    NO_SUCH_USER("no such user found!"),
    USER_IS_ALREADY_ADDED("user is already added!"),
    MAXIMUM_NUMBER_OF_USERS("can not add any more user!"),
    LORD_HOUSE_IS_SELECTED_BEFORE("the lord house is already selected for another player!"),
    INVALID_COLOR("invalid color!"),
    COLOR_IS_ALREADY_SET("player is already in the team!"),
    INVALID_LORD_HOUSE_NUMBER("invalid lord house number!"),
    TOO_FEW_PLAYERS("too few players"),
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
