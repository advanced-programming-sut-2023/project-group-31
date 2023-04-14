package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterCommands {
    ;
    private String regex;

    RegisterCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterCommands commands){
        Matcher matcher= Pattern.compile(commands.regex).matcher(input);
        return matcher;
    }
}
