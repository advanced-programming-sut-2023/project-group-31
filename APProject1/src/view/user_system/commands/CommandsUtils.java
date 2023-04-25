package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsUtils {
    protected static String editRegex(String regex){
        String result;
        result=regex.replaceAll("[\\s]+","[\\s]+");
        result=regex.replaceAll("IN","([\\S]*)|(\"[^*]*\")");

        return result;
    }
}
