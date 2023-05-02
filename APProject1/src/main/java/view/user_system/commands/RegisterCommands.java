package view.user_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterCommands {
    REGISTER("user create(( -u (?<username>IN))|( -p (?<password>IN) (?<passwordConfirmation>IN))|( -p (?<randomPassword>random))|( –email (?<email>IN))|( -s (?<slogan>IN)))+"),
    PICK_QUESTION("question pick -q <question-number> -a <answer> -c <answerConfirm>")
    ;
    private final String regex;

    RegisterCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);

    }

    public String getRegex() {
        return regex;
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
