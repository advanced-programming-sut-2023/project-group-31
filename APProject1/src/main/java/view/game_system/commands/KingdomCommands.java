package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum KingdomCommands {
    SHOW_POPULARITY_FACTOR("show popularity factor"),
    SHOW_POPULARITY("show popularity"),
    SHOW_FOOD_LIST("show food list"),
    FOOD_RATE("food rate -r (?<rateNumber>.+)"),
    FOOD_RATE_SHOW("food rate show"),
    TAX_RATE("tax rate -r (?<rateNumber>.+)"),
    TAX_RATE_SHOW("tax rate show"),
    FEAR_RATE("fear rate -r (?<rateNumber>.+)");
    String regex;
    KingdomCommands(String regex){
        this.regex=regex;
    }

    public String getRegex() {
        return regex;
    }
    public static Matcher getMatcher(String input,KingdomCommands command){
        Matcher matcher= Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ?matcher: null;
    }
}
