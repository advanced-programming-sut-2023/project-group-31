package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    ADD_REQUEST("trade ((?<get> -get ( -t (?<resourceType>I) -a (?<resourceAmount>\\d+) )+)|(?<give> -give ( -t (?<resourceType>I) -a (?<resourceAmount>\\d+) )+)|( -m (?<message>I)|( -p (?<player>I))+"),
    CREATE_NEW_TRADE("create new trade"),
    ADD_ITEM_TO_GET("add to asked items(( -i (?<item>IN))|( -a (?<amount>\\d+)))+"),
    REMOVE_ITEM_FROM_GET("remove from asked items -i (?<item>IN)"),
    ADD_ITEM_TO_GIVE("add to provided items(( -i (?<item>IN))|( -a (?<amount>\\d+)))+"),
    REMOVE_ITEM_FROM_GIVE("remove from provided items -i (?<item>IN)"),
    ADD_AUDIENCE("add player to audiences -p (?<player>IN)"),
    REMOVE_AUDIENCE("remove player from audiences -p (?<player>)IN"),
    SUBMIT("submit"),
    SHOW_CURRENT_TRADE("show current trade"),
    SHOW_TRADE_LIST("trade list"),
    SHOW_MY_TRADE_HISTORY("trade history"),
    ACCEPT_TRADE("trade accept(( -i (?<id>\\d+))|( -m (?<message>I)))+"),
    ;
    private final String regex;
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
