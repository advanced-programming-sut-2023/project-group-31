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
        // ^\\< baray e ine ke group hay e dakheli ro nagire
        Matcher groups = Pattern.compile("\\(\\?\\<(?<groupName>[\\S]+)\\>[^\\<]+\\)").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();
        while (groups.find()) {
            groupName = groups.group("groupName");
            //agar groupName tekrari bood ehtemalan error khahad dad!
            //be nazaram inputs ro static bezar ba'd tooye putInHashmap message return kon agar success bood tooye menu inputs ro bede be setInput
            if(inputs.keySet().contains(groupName)) {
                //error bede
                continue;
            }
            //what!?
            inputs.put(groupName, matcher.group(groupName).trim().replaceFirst("\"","").replaceFirst("(?s)"+"\""+"(?!.*?"+"\""+")", ""));

        }
        inputs.put("command",regex.substring(0,regex.indexOf("(")));
        return inputs;
    }

    //bere tooye controller
    public static String checkFormatErrors(HashMap<String, String> inputs) {

        for (String groupName : inputs.keySet()) {
            if (InputFormats.getInputFormat(groupName) == null) {
                System.out.println("system has an error!______");
                return "error";
            } else if (inputs.get(groupName) == null && (InputFormats.getInputFormat(groupName).isCompulsory())) {
                return "Not enough messages!";
            } else if (inputs.get(groupName) != null && !isFormatCurrent(inputs.get(groupName), InputFormats.getInputFormat(groupName))) {
                return groupName+" format!";
            }
        }
        return null;
    }

}
