package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MarketCommands {
    BUY("buy -i (?<item>.+) -a (?<amount>\\d+)"),
    SELL("sell -i (?<item>.+) -a (?<amount>\\d+)"),
    SHOWPRICELIST("show price list");
    String regex;
    private MarketCommands (String regex){
        this.regex=regex;
    }
    public static Matcher getMatcher(String input,MarketCommands command){
        Matcher matcher= Pattern.compile(command.regex).matcher(input);
        return matcher.matches()? matcher: null;
    }

    public String getRegex() {
        return regex;
    }
}
