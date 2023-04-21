package view.user_system;

import controller.ControllerUtils;
import controller.user_menu.LoginController;
import view.ViewUtils;
import view.user_system.commands.LoginCommands;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends ViewUtils {
    public static MenuSwitcherMessages run(Scanner scanner) {

        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.LOGIN)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.LOGIN.getRegex()));
                if(userLogin()){
                    return MenuSwitcherMessages.MAIN;
                };
            }else if(input.matches("goto[\\s]+register[\\s]+menu")){
                return MenuSwitcherMessages.REGISTER;
            }else {
                System.out.println("Invalid command!");
            }
        }

    }

    private static boolean userLogin(){
        result= LoginController.loginUser();
        return false;
    }
    private static void user(){

    }
    private static void userCreate(){

    }

}
