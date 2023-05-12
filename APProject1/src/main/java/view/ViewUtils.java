package view;

import controller.ControllerUtils;
import view.user_system.messages.UserMessages;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {
    protected static Scanner scanner;
    protected static UserMessages userResult;


    public static String editRegex(String regex){
        String result;
        result=regex.replaceAll("[\\s]+","[\\s]+");
        result=regex.replaceAll("IN","([\\\\S]*)|(\"[^*]*\")");
        return result;
    }

    public static HashMap<String, String> putInHashmap(Matcher matcher, String regex) {
        Matcher groups = Pattern.compile("\\(\\?\\<(?<groupName>[^\\)]+)\\>").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();

        while (groups.find()) {
            groupName = groups.group("groupName");
            if(matcher.group(groupName)!=null) {
                inputs.put(groupName, matcher.group(groupName).replaceFirst("\"", "").replaceFirst("(?s)" + "\"" + "(?!.*?" + "\"" + ")", ""));
            }
        }

        ControllerUtils.setInputs(inputs);

        inputs.put("command",regex.substring(0,regex.indexOf("(")));
        return inputs;
    }



}