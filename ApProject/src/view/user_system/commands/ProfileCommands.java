package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    ;
    private String regex;

    ProfileCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileCommands commands){
        Matcher matcher= Pattern.compile(commands.regex).matcher(input);
        return matcher;
    }
}
