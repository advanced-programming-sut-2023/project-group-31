package view.user_system;

import controller.ControllerUtils;
import controller.user_menu.LoginController;
import view.ViewUtils;
import view.user_system.commands.LoginCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends ViewUtils {


    public static MenuSwitcherMessages run(Scanner scanner) {
        if(ControllerUtils.isUserLoggedIn()){
            return MenuSwitcherMessages.MAIN;
        }
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.LOGIN)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.LOGIN.getRegex()));
                userLogin();
                if(LoginController.isUserLoggedIN()){
                    return MenuSwitcherMessages.MAIN;
                }
            } else if (input.matches("goto[\\s]+register[\\s]+menu")) {
                return MenuSwitcherMessages.REGISTER;
            } else {
                System.out.println("Invalid command!");
            }
        }

    }

    private static void userLogin() {
        result = LoginController.loginUser();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user login: " + UserMessages.SUCCESS.getTxt());
        } else if (result.equals(UserMessages.PASSWORD_IS_NOT_CORRECT)) {
            System.out.println("user login failed: " + result.getTxt());
            wrongPasswordMenu();
        } else {
            System.out.println("user login failed: " + result.getTxt());
        }

    }

    private static void wrongPasswordMenu() {
        System.out.println("Enter your password again or enter \"forgot my password\".");

        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.ENTER_PASSWORD)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.ENTER_PASSWORD.getRegex()));
                userLogin();
                return;
            } else if (LoginCommands.getMatcher(input, LoginCommands.FORGOT_PASSWORD) != null) {
                LoginController.getForgotPasswordQuestion();
                forgotPasswordMenu();
                return;
            } else if(input.equalsIgnoreCase("exit")){
                return;
            }else {
                System.out.println("Invalid command!");
            }
        }
    }


    private static void forgotPasswordMenu() {

        System.out.println(UserMessages.MenuMessage);
        String input;
        Matcher matcher;

            input = scanner.nextLine().trim();
            if (LoginController.checkForgotQuestionAnswerCorrectness(input).equals(UserMessages.CORRECT_ANSWER)) {
                System.out.println(UserMessages.CORRECT_ANSWER);
                changePasswordMenu();
            } else {
                System.out.println(UserMessages.WRONG_ANSWER);
            }

    }

    private static void changePasswordMenu() {
        System.out.println("Enter new password.");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input,LoginCommands.ENTER_NEW_PASSWORD)) != null){
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.LOGIN.getRegex()));
                changePassword();
                return;
            }else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void changePassword() {
        result=LoginController.changePassword();
        if(result.equals(UserMessages.SUCCESS)){
            System.out.println("change password: "+result.getTxt());
        }else{
            System.out.println("change password failed: "+result.getTxt());
        }
    }


    private static void user() {

    }

    private static void userCreate() {

    }

}
