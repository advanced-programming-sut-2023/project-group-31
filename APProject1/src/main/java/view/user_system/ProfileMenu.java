package view.user_system;

import controller.ControllerUtils;
import controller.user_system.ProfileController;
import view.ViewUtils;
import view.user_system.commands.ProfileCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends ViewUtils {
    public static MenuSwitcherMessages run(Scanner scanner) {
        if(!ControllerUtils.isUserLoggedIn()){
            return MenuSwitcherMessages.LOGIN;
        }
        System.out.println("_________Profile Menu_______");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                return MenuSwitcherMessages.MAIN;
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_USERNAME)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.CHANGE_USERNAME.getRegex());
                changeUsername();
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_NICKNAME)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.CHANGE_NICKNAME.getRegex());
                changeNickname();
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_PASSWORD)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.CHANGE_PASSWORD.getRegex());
                changePassword();
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_EMAIL)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.CHANGE_EMAIL.getRegex());
                changeEmail();
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_SLOGAN)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.CHANGE_SLOGAN.getRegex());
                changeSlogan();
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_PROFILE)) != null) {
                ViewUtils.putInHashmap(matcher, ProfileCommands.DISPLAY_PROFILE.getRegex());
                displayProfile();
            } else {
                System.out.println("Invalid command!");
            }

        }

    }


    private static void changeUsername() {
        result = ProfileController.profileChangeUsername();
        System.out.println(changeProfileMessage("username"));
    }

    private static void changeNickname() {
        result = ProfileController.profileChangeNickname();
        System.out.println(changeProfileMessage("nickname"));
    }

    private static void changePassword() {
        if(!CaptchaMenu.getObject().run()){
            System.out.println("change password failed: wrong captcha.");
            return;
        }
        result = ProfileController.profileChangePassword();
        System.out.println(changeProfileMessage("password"));
    }

    private static void changeEmail() {
        result = ProfileController.profileChangeEmail();
        System.out.println(changeProfileMessage("email"));
    }

    private static void changeSlogan() {
        result = ProfileController.profileChangeSlogan();
        System.out.println(changeProfileMessage("slogan"));
    }

    private static String changeProfileMessage(String field) {
        if (result.equals(UserMessages.SUCCESS)) {
            return (field + " change: " + result);
        } else {
            return (field + " change failed: " + result);
        }
    }


    private static void displayProfile() {
        result = ProfileController.profileDisplay();
        if (result.equals(UserMessages.MESSAGES)) {
            System.out.println(result);
        } else {
            System.out.println("display profile success: " + result);
        }
    }


    private static void profileDisplaySlogan() {

    }

}
