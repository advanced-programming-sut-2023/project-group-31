package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterCommands {
    REGISTER("user create(( -u (?<username>I))|( -p (?<password>I) (?<passwordConfirmation>I))|( -p (?<password>random))|( –email (?<email>I))|( -s (?<slogan>I))))+"),
    PICK_QUESTION("question pick -q <question-number> -a <answer> -c <answerConfirm>")
    ;
    private String regex;

    RegisterCommands(String regex) {
        this.regex = editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }



    private String editRegex(String regex){
        String result;
        result=regex.replaceAll("[\\s]+","[\\s]+");
        result=regex.replaceAll("I","([\\S]*)|(\"[^*]*\")");

        return result;
    }

    public static Matcher getMatcher(String input, RegisterCommands command){
        Matcher matcher=Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()){
            return matcher;
        }else{
            return null;
        }
    }
}
