package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {

    ;
    private String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
