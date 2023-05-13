package view.user_system;

import controller.ControllerUtils;
import controller.user_system.LoginController;
import view.ViewUtils;
import view.user_system.commands.LoginCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends ViewUtils {


    public static MenuSwitcherMessages run(Scanner scanner) {
        if (ControllerUtils.isUserLoggedIn()) {
            return MenuSwitcherMessages.MAIN;
        }
        System.out.println("--------login menu-------");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.LOGIN)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.LOGIN.getRegex()));
                userLogin();
                if (LoginController.isUserLoggedIN()) {
                    return MenuSwitcherMessages.MAIN;
                }
            } else if (input.matches("goto[\\s]+register[\\s]+menu")) {
                return MenuSwitcherMessages.REGISTER;
            } else {
                System.out.println("Invalid command!");
                continue;
            }
            System.out.println("login failed please try again!");
        }

    }

    private static void userLogin() {
        if (!CaptchaMenu.getObject().run()) {
            System.out.println("login failed: wrong captcha");
            return;
        }
        userResult = LoginController.loginUser();
        if (userResult.equals(UserMessages.SUCCESS)) {
            System.out.println("user login: " + UserMessages.SUCCESS.getTxt());
        } else if (userResult.equals(UserMessages.PASSWORD_IS_NOT_CORRECT)) {
            System.out.println("user login failed: " + userResult.getTxt());
            wrongPasswordMenu();
        } else {
            System.out.println("user login failed: " + userResult.getTxt());
        }

    }

    private static void wrongPasswordMenu() {
        System.out.println("Enter your password again or enter \"forgot my password\".");

        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.ENTER_PASSWORD)) != null) {
                ControllerUtils.putInput("password",matcher.group("password"));
                userLogin();
                return;
            } else if (LoginCommands.getMatcher(input, LoginCommands.FORGOT_PASSWORD) != null) {
                LoginController.getForgotPasswordQuestion();
                forgotPasswordMenu();
                return;
            } else if (input.equalsIgnoreCase("exit")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }


    private static void forgotPasswordMenu() {

        System.out.println("Answer this question!");
        System.out.println(UserMessages.MESSAGE);
        String input;
        Matcher matcher;
        int wrongAnswers = 0;
        while (true) {
            input = scanner.nextLine().trim();
            if (LoginController.checkForgotQuestionAnswerCorrectness(input).equals(UserMessages.CORRECT_ANSWER)) {
                System.out.println(UserMessages.CORRECT_ANSWER);
                changePasswordMenu();
                if(ControllerUtils.isUserLoggedIn()){
                    return;
                }
            } else {
                System.out.println(UserMessages.WRONG_ANSWER);
                wrongAnswers++;
            }
            if (wrongAnswers > 3) {
                return;
            }
        }


    }

    private static void changePasswordMenu() {
        System.out.println("Enter new password.");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = LoginCommands.getMatcher(input, LoginCommands.ENTER_NEW_PASSWORD)) != null) {
                String username = ControllerUtils.getInputs().get("username");
                ControllerUtils.setInputs(putInHashmap(matcher, LoginCommands.ENTER_NEW_PASSWORD.getRegex()));
                ControllerUtils.putInput("username", username);
                changePassword();
                if(ControllerUtils.isUserLoggedIn()){
                    return;
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void changePassword() {
        userResult = LoginController.changePassword();
        if (userResult.equals(UserMessages.SUCCESS)) {
            System.out.println("change password: " + userResult.getTxt());
        } else {
            System.out.println("change password failed: " + userResult.getTxt());
        }
    }
}
