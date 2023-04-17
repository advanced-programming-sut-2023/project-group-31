package view.user_system;

import controller.ControllerUtils;
import controller.user_menu.RegisterController;
import view.ViewUtils;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends ViewUtils{
    private static UserMessages result;

    public static MenuSwitcherMessages run(Scanner scanner){

        String input;
        Matcher matcher;
        while(true) {
            input = scanner.nextLine().trim();
            if ((matcher= RegisterCommands.getMatcher(input,RegisterCommands.REGISTER))!=null) {
                userCreate(matcher);
            }else{
                System.out.println("Invalid command!");
            }
        }

    }

    private static void userCreate(Matcher matcher){
        UserMessages result;
        ControllerUtils.setInputs(putInHashmap(matcher,RegisterCommands.REGISTER.getRegex()));
        result=RegisterController.userCreate();
        if(result.equals(UserMessages.SUCCESS)){
            System.out.println("user register: "+result.getTxt());
        }else if(result.equals(UserMessages.CONFIRM)) {
            pickQuestionMenu();
        }else if(result.equals(UserMessages.USER_EXITS_BEFORE)){
            confirmUsernameMenu();
        }else{
            System.out.println("create user failed: "+result.getTxt());
        }
    }

    private static void confirmUsernameMenu() {
        Matcher matcher;
        String input;
        while(true){
            input=scanner.nextLine();
            if((matcher= RegisterCommands.getMatcher(input,RegisterCommands.REGISTER))!=null){
                confirmUsername(matcher);
            }else{
                System.out.println("Invalid command!");
            }
        }
    }

    private static void confirmUsername(Matcher matcher) {

    }

    private static void pickQuestionMenu(){
        String input;
        Matcher matcher;
        while(true){
            input=scanner.nextLine();
            if((matcher= RegisterCommands.getMatcher(input,RegisterCommands.REGISTER))!=null){
                pickQuestion(matcher);
            }else{
                System.out.println("Invalid command!");
            }
        }
    }

    private static void pickQuestion(Matcher matcher){

        ControllerUtils.setInputs(putInHashmap(matcher,RegisterCommands.PICK_QUESTION.getRegex()));
        result=RegisterController.pickQuestion();
        if(result.equals(UserMessages.SUCCESS)){
            System.out.println("question pick: "+result.getTxt());
        }else{
            System.out.println("question pick failed: "+result.getTxt());
        }
    }

}