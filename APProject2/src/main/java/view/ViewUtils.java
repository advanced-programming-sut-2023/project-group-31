package view;

import controller.ControllerUtils;
import javafx.stage.Stage;
import view.user_system.messages.UserMessages;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {
    public static Stage stage;
    protected static Scanner scanner;
    protected static UserMessages userResult;


    public static String editRegex(String regex){
        String result;
        result=regex.replaceAll("[\\s]+","[\\\\s]+");
        result=result.replaceAll("IN","([\\\\S]*)|(\"[^*]*\")");
        return result;
    }

    public static HashMap<String, String> putInHashmap(Matcher matcher, String regex) {
        Matcher groups = Pattern.compile("\\(\\?\\<(?<groupName>[^\\)]+)\\>").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();

        while (groups.find()) {
            groupName = groups.group("groupName");
            if(matcher.group(groupName)!=null) {
                inputs.put(groupName, matcher.group(groupName).replaceFirst("\"", "").replaceFirst("(?s)" + "\"" + "(?!.*?" + "\"" + ")", "").trim());
            }
        }

        ControllerUtils.setInputs(inputs);

        inputs.put("command",regex.substring(0,regex.indexOf("(")));
        return inputs;
    }
    public static String fixDoubleQuotes(String input) {
        input = input.trim();
        if(input.charAt(0) == '\"') {
            input.replaceAll("\"", "");
        }
        return input;
    }
}