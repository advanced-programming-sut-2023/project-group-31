package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands{
    ;
    private String regex;

    LoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginCommands commands){
        Matcher matcher= Pattern.compile(commands.regex).matcher(input);
        return matcher;
    }
}
