package view.game_system.messages;

public enum MarketMessages {
    SUCCESS("successful!"),
    INVALID_GOOD_NAME("invalid good name"),
    NOT_ENOUGH_GOOD("not enough good from this type!"),
    NOT_ENOUGH_GOLD("not enough gold my lord!"),
    INVALID_COMMAND("invalid command!"),
    YOUR_STOCKPILE_IS_FULL("your stockpile is full my lord!");
    private String txt;
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    MarketMessages(String txt) {
        this.txt = txt;
        this.input = "";
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt + input;
    }

    public static MarketMessages getMessageByTxt(String txt) {
        for (MarketMessages tradeMessage : values()) {
            if(tradeMessage.txt.equals(txt)) {
                return tradeMessage;
            }
        }
        return null;
    }

    }
