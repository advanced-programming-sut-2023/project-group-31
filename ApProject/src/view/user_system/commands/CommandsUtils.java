package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsUtils {
    public static Matcher getMatcher(String input,String regex){
        Matcher matcher= Pattern.compile(regex).matcher(input);
        return matcher;
    }
}
