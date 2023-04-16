package view;

import view.user_system.commands.InputFormats;
import view.user_system.messages.Messages;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {

    public static boolean isFormatCurrent(String input, InputFormats inputFormat) {
        if (inputFormat.getFormat() == null) {
            return true;
        }
        return input.matches(inputFormat.getFormat());
    }

    public static HashMap<String, String> putInHashmap(Matcher matcher, String regex) {
        Matcher groups = Pattern.compile("\\(\\?\\<(?<groupName>[\\S]+)\\>.+\\)").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();
        while (groups.find()) {
            groupName = groups.group("groupName");
            inputs.put(groupName, matcher.group(groupName));
        }
        return inputs;
    }

    public static String checkFormatErrors(HashMap<String, String> inputs) {

        for (String groupName : inputs.keySet()) {
            if(InputFormats.getInputFormat(groupName)==null){
                System.out.println("system has an error!______");
                return "error";
            }else if (inputs.get(groupName) != null&& !isFormatCurrent(inputs.get(groupName),InputFormats.getInputFormat(groupName))) {
                return groupName;
            }
        }
        return null;
    }

}
