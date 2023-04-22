package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    ADD_REQUEST("trade ((?<get> -get ( -t (?<resourceType>I) -a (?<resourceAmount>\\d+) )+)|(?<give> -give ( -t (?<resourceType>I) -a (?<resourceAmount>\\d+) )+)|( -m (?<message>I)|( -p (?<player>I))+"),
    SHOW_TRADE_LIST("trade list"),
    SHOW_MY_TRADE_HISTORY("trade history"),
    ACCEPT_TRADE("trade accept (( -i (?<id>\\d+))|( -m (?<message>I)))+"),
    ;
    private String regex;
    TradeCommands(String regex) {
        this.regex = editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, TradeCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    private String editRegex(String regex) {
        regex = regex.replaceAll("[\\s]+", "[\\s]+");
        regex = regex.replaceAll("I", "([\\S]*)|(\"[^*]*\")");
        return regex;
    }
}
