package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {

    LOGOUT("user logout"), GOTO_PROFILE_MENU("profile menu");
    private final String regex;

    MainMenuCommands(String regex) {
        this.regex = CommandsUtils.editRegex(regex);
    }

    public static Matcher getMatcher(String input, MainMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
