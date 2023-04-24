package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    DISPLAY_PROFILE("display profile (?<field>[\\S]*)")
    ;
    private final String regex;

    ProfileCommands(String regex) {
        this.regex = CommandsUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, ProfileCommands command){
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
