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

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, LoginCommands command){
        Matcher matcher=Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()){
            return matcher;
        }else{
            return null;
        }
    }
}
