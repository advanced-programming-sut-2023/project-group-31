package view.user_system;

import controller.ControllerUtils;
import controller.user_system.RegisterController;
import view.ViewUtils;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;
import view.viewStyle.Colors;

import java.io.IOException;
import java.util.regex.Matcher;

public class RegisterMenu extends ViewUtils {


    public static MenuSwitcherMessages run() throws IOException {
        if (ControllerUtils.isUserLoggedIn()) {
            return MenuSwitcherMessages.MAIN;
        }
        System.out.println("\n" + Colors.GREEN.getBackgroundColorCode() + "_________Register Menu_______" + Colors.RESET.getBackgroundColorCode() + "\n");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();

            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.REGISTER)) != null) {
                putInHashmap(matcher, RegisterCommands.REGISTER.getRegex());
                userCreate();
            } else if (input.matches("goto[\\s]+login[\\s]+menu")) {
                return MenuSwitcherMessages.LOGIN;
            } else {
                System.out.println("Invalid command!");
            }

        }

    }

    private static void userCreate() throws IOException {
        if (!CaptchaMenu.getObject().run()) {
            System.out.println("register failed: wrong captcha.");
            return;
        }
        UserMessages result;
        result = RegisterController.userCreate();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user register: " + result.getTxt());
            pickQuestionMenu();
        } else if (result.equals(UserMessages.RANDOM_PASSWORD)) {

            confirmUsernameOrPasswordMenu(false);
        } else if (result.equals(UserMessages.USER_EXITS_BEFORE)) {
            System.out.println("create user failed: " + result.getTxt());
            confirmUsernameOrPasswordMenu(true);
        } else {
            System.out.println("create user failed: " + result.getTxt());
        }
    }

    private static void confirmUsernameOrPasswordMenu(boolean isUsername) throws IOException {
        System.out.println("Do you like this?");
        if (isUsername)
            System.out.println(ControllerUtils.getInputs().get("username"));
        else
            System.out.println(ControllerUtils.getInputs().get("password"));
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equals("YES")) {
                if (!isUsername) {
                    correctConfirmQuestion();
                }
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

    private static boolean correctConfirmQuestion() {
        String input;
        Matcher matcher;
        System.out.println("enter password again");
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equals(ControllerUtils.getInputs().get("password"))) {
                return true;
            }
        }
    }

    private static void pickQuestionMenu() throws IOException {
        String input;
        Matcher matcher;
        System.out.println("pick one of these questions.");
        int index = 0;
        System.out.println(RegisterController.getForgotPasswordQuestions());
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.PICK_QUESTION)) != null) {
                String username = ControllerUtils.getInputs().get("username");
                ControllerUtils.setInputs(putInHashmap(matcher, RegisterCommands.PICK_QUESTION.getRegex()));
                pickQuestion(username);
                if (userResult.equals(UserMessages.SUCCESS)) return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void pickQuestion(String username) throws IOException {

        userResult = RegisterController.pickQuestion(username);
        if (userResult.equals(UserMessages.SUCCESS)) {
            System.out.println("question pick: " + userResult.getTxt());
        } else {
            System.out.println("question pick failed: " + userResult.getTxt());
        }

    }

}