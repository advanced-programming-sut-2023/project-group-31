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
    OTHER_ONE_RESOURCE_SHORTAGE("the other side doesn't have enough resources!"),
    PLAYER_IS_ALREADY_ADDED("player is already added!"),
    NO_SUCH_PLAYER_ADDED_BEFORE("no such player added before!"),
    NO_SUCH_ITEM("no such item!"),
    ITEM_IS_ALREADY_ADDED("item is already added!"),
    NO_SUCH_ITEM_ADDED_BEFORE("no such item added before!"),
    NO_AUDIENCE("there is no audience!"),
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
