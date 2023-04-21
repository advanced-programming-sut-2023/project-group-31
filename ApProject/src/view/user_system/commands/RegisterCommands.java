package view.user_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterCommands {
    REGISTER("user create(( -u (?<username>IN))|( -p (?<password>IN) (?<passwordConfirmation>IN))|( -p (?<password>random))|( –email (?<email>IN))|( -s (?<slogan>IN))))+"),
    PICK_QUESTION("question pick(( -q (?<questionNumber>IN))|( -a (?<answer>IN))|( -c (?<answerConfirm>IN)))+")
    ;
    private final String regex;

    RegisterCommands(String regex) {
        this.regex = editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }



    private String editRegex(String regex){
        String result;
        result=regex.replaceAll("[\\s]+","[\\s]+");
        result=regex.replaceAll("IN","([\\S]*)|(\"[^*]*\")");

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
