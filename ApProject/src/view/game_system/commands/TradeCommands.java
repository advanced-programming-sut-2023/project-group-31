package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    TRADE("trade -t(?<resourceType>.+)-a(?<resourceAmount>\\d+)-p(?<price>\\S+)-m(?<message>.+)"),
    TRADELIST("trade list"),
    BACK("back"),
    TRADEHISTORY("trade history"),
    ACCEPTTRADE("trade accept -i(?<id>.+)-m(?<message>.+)");
    String regex;

    private TradeCommands(String regex){
      this.regex=regex;
    }
     public static Matcher getMatch(String input,TradeCommands command){
        Matcher matcher= Pattern.compile(command.regex).matcher(input);
             return matcher.matches()?matcher: null;
     }
}
