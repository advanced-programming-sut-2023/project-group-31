package view.game_system.messages;

public enum TradeMessages {
    SUCCESS("successful!"),
    ID_DOES_NOT_EXIST("id not found!"),
    INVALID_RESOURCE_TYPE("invalid resource type: "),
    NOT_ENOUGH_RESOURCE("not enough : "),
    INVALID_COMMAND("invalid command!"),
    INVALID_TRADE("invalid trade!"),
    YOU_CAN_NOT_TRADE_WITH_YOURSELF("can't trade with yourself!"),
    CHOOSE_YOUR_AUDIENCE("choose your audience!"),
    NO_SUCH_PLAYER("no such player in the game!"),
    NO_SUCH_TRADE_ID("no such trade id found!"),
    ;
    private String txt;
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    TradeMessages(String txt) {
        this.txt = txt;
        this.input = "";
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt + input;
    }

    public static TradeMessages getMessageByTxt(String txt) {
        for (TradeMessages tradeMessage : values()) {
            if(tradeMessage.txt.equals(txt)) {
                return tradeMessage;
            }
        }
        return null;
    }

}
