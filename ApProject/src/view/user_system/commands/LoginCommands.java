package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands{
    LOGIN("user login -u (?<username>IN) -p (?<password>IN)(?<stayLoggedIn> --stay-logged-in)?")
    ;
    private String regex;

    LoginCommands(String regex) {
        this.regex = CommandsUtils.editRegex(regex);
    }

    public static Matcher getMatcher(String input, LoginCommands commands){
        Matcher matcher= Pattern.compile(commands.regex).matcher(input);
        return matcher;
    }
}
