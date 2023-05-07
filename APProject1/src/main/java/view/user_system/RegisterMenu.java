package view.user_system;

import controller.ControllerUtils;
import controller.user_system.RegisterController;
import view.ViewUtils;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.regex.Matcher;

public class RegisterMenu extends ViewUtils {


    public static MenuSwitcherMessages run() {
        if(ControllerUtils.isUserLoggedIn()){
            return MenuSwitcherMessages.MAIN;
        }
        System.out.println("_________Register Menu_______");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();

            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.REGISTER)) != null) {
                putInHashmap(matcher, RegisterCommands.REGISTER.getRegex());
                userCreate();
            }else if(input.matches("goto[\\s]+login[\\s]+menu")){
                return MenuSwitcherMessages.LOGIN;
            }else {
                System.out.println("Invalid command!");
            }

        }

    }

    private static void userCreate() {
        if(!CaptchaMenu.getObject().run()){
            System.out.println("register failed: wrong captcha.");
            return;
        }
        UserMessages result;
        result = RegisterController.userCreate();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user register: " + result.getTxt());
            pickQuestionMenu();
        } else if (result.equals(UserMessages.RANDOM_PASSWORD)) {
            confirmUsernameOrPasswordMenu();
        } else if (result.equals(UserMessages.USER_EXITS_BEFORE)) {
            System.out.println("create user failed: " + result.getTxt());
            confirmUsernameOrPasswordMenu();
        }else {
            System.out.println("create user failed: " + result.getTxt());
        }
    }

    private static void confirmUsernameOrPasswordMenu() {

        String input;
        while (true) {
            input = scanner.nextLine().trim();
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
        System.out.println("pick one of these questions.");
        int index=0;
        System.out.println(RegisterController.getForgotPasswordQuestions());
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = RegisterCommands.getMatcher(input, RegisterCommands.PICK_QUESTION)) != null) {
                String username=ControllerUtils.getInputs().get("username");
                ControllerUtils.setInputs(putInHashmap(matcher, RegisterCommands.PICK_QUESTION.getRegex()));
                pickQuestion(username);
                if(result.equals(UserMessages.SUCCESS)) return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void pickQuestion(String username) {

        result = RegisterController.pickQuestion(username);
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("question pick: " + result.getTxt());
        } else {
            System.out.println("question pick failed: " + result.getTxt());
        }

    }

}