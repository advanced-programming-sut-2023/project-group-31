package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TurnMenuCommands {

    ;
    private String regex;

    TurnMenuCommands(String regex) {
        this.regex = editRegex(regex);
    }

    public static Matcher getMatcher(String input, TurnMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    private String editRegex(String regex) {
        regex = regex.replaceAll("[\\s]+", "[\\s]+");
        regex = regex.replaceAll("I", "([\\S]*)|(\"[^*]*\")");
        return regex;
    }
}
