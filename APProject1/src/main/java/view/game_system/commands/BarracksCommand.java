package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BarracksCommand {
    CREATE_UNIT("createunit -t (?<type>.+) -c (?<amount>.+)");
    String regex;
    BarracksCommand(String regex){
        this.regex=regex;
    }

    public String getRegex() {
        return regex;
    }
    public static Matcher getMatcher(String input,BarracksCommand command){
        Matcher matcher= Pattern.compile(command.regex).matcher(input);
        return matcher.matches()?matcher : null;
    }
}
