package view.user_system;

import controller.ControllerUtils;
import controller.user_menu.RegisterController;
import view.ViewUtils;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends ViewUtils {
    private static UserMessages result;

    public static MenuSwitcherMessages run(Scanner scanner) {

        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.REGISTER)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, RegisterCommands.REGISTER.getRegex()));
                userCreate();
            } else {
                System.out.println("Invalid command!");
            }
        }

    }

    private static void userCreate() {
        UserMessages result;
        result = RegisterController.userCreate();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user register: " + result.getTxt());
            pickQuestionMenu();
        } else if (result.equals(UserMessages.RANDOM_PASSWORD)) {

        } else if (result.equals(UserMessages.USER_EXITS_BEFORE)) {
            System.out.println("create user failed: " + result.getTxt());
            confirmUsernameMenu();
        } else {
            System.out.println("create user failed: " + result.getTxt());
        }
    }

    private static void confirmUsernameMenu() {

        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("YES")) {
                userCreate();
                return;
            } else if (input.equals("NO")) {
                System.out.println("OK! Returned to register menu.");
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void confirmPasswordMenu() {

        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("YES")) {
                userCreate();
                return;
            } else if (input.equals("NO")) {
                System.out.println("OK! Returned to register menu.");
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }


    private static void pickQuestionMenu() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.REGISTER)) != null) {
                ControllerUtils.setInputs(putInHashmap(matcher, RegisterCommands.PICK_QUESTION.getRegex()));
                pickQuestion();
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void pickQuestion() {

        result = RegisterController.pickQuestion();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("question pick: " + result.getTxt());
        } else {
            System.out.println("question pick failed: " + result.getTxt());
        }

    }

}