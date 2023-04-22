package view;

import model.User;
import view.user_system.commands.InputFormats;
import view.user_system.messages.UserMessages;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {
    protected static Scanner scanner;
    protected static UserMessages result;

    public static HashMap<String, String> putInHashmap(Matcher matcher, String regex) {
        Matcher groups = Pattern.compile("\\(\\?\\<(?<groupName>[\\S]+)\\>.+\\)").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();
        while (groups.find()) {
            groupName = groups.group("groupName");
            inputs.put(groupName, matcher.group(groupName).replaceFirst("\"","").replaceFirst("(?s)"+"\""+"(?!.*?"+"\""+")", ""));

        }
        inputs.put("command",regex.substring(0,regex.indexOf("(")));
        return inputs;
    }



}
