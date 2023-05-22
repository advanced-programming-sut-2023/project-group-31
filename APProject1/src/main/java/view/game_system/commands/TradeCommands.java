package view.game_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    CREATE_NEW_TRADE("create new trade"),
    ADD_ITEM_TO_GET("add to asked items(( -i (?<item>IN))|( -a (?<amount>\\d+)))+"),
    REMOVE_ITEM_FROM_GET("remove from asked items -i (?<item>IN)"),
    ADD_ITEM_TO_GIVE("add to provided items(( -i (?<item>IN))|( -a (?<amount>\\d+)))+"),
    REMOVE_ITEM_FROM_GIVE("remove from provided items -i (?<item>IN)"),
    ADD_AUDIENCE("add player to audiences -p (?<player>IN)"),
    REMOVE_AUDIENCE("remove player from audiences -p (?<player>)IN"),
    SUBMIT("submit( -m (?<message>IN))?"),
    SHOW_CURRENT_TRADE("show current trade"),
    SHOW_TRADE_LIST("trade list"),
    SHOW_MY_TRADE_HISTORY("trade history"),
    ACCEPT_TRADE("accept trade(( -i (?<id>\\d+))|( -m (?<message>IN)))+"),
    ;
    private final String regex;
    TradeCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, TradeCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
