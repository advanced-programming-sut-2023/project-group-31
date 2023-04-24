package view.user_system;

import controller.ControllerUtils;
import controller.user_menu.LoginController;
import view.ViewUtils;
import view.user_system.commands.LoginCommands;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

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
                if (userLogin()) {
                    return MenuSwitcherMessages.MAIN;
                }
            } else if (input.matches("goto[\\s]+register[\\s]+menu")) {
                return MenuSwitcherMessages.REGISTER;
            } else {
                System.out.println("Invalid command!");
            }
        }

    }

    private static boolean userLogin() {
        result = LoginController.loginUser();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user login: " + UserMessages.SUCCESS.getTxt());
        } else if (result.equals(UserMessages.PASSWORD_IS_NOT_CORRECT)) {
            System.out.println("user login failed: " + result.getTxt());
            wrongPasswordMenu();
        } else {
            System.out.println("user login failed: " + result.getTxt());
        }
        return false;
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
            } else if (LoginCommands.getMatcher(input,LoginCommands.FORGOT_PASSWORD)!=null)) {
                forgotPasswordMenu();
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void forgotPasswordMenu() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.ENTER_PASSWORD)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.ENTER_PASSWORD.getRegex()));
                userLogin();
            } else if (LoginCommands.getMatcher(input,LoginCommands.FORGOT_PASSWORD)!=null) {
                System.out.println();
                forgotPasswordMenu();
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

}


    private static void user() {

    }

    private static void userCreate() {

    }

}
