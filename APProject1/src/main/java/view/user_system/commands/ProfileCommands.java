package view.user_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    DISPLAY_PROFILE("profile display( (?<field>[\\S]+))?"),
    CHANGE_USERNAME("profile change( -u (?<username>IN))"),
    CHANGE_NICKNAME("profile change( -n (?<nickname>IN))"),
    CHANGE_PASSWORD("profile change password( -o (?<oldPassword>IN))( -n (?<newPassword>IN))"),
    CHANGE_EMAIL("profile change( -e (?<email>IN))"),
    CHANGE_SLOGAN("profile change slogan( -s (?<slogan>IN))"),
    PROFILE_DISPLAY_RANK("profile display rank");
    private final String regex;

    ProfileCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
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
