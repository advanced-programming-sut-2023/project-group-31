package view.user_system;

import controller.user_menu.LoginController;
import view.ViewUtils;
import view.user_system.commands.MainMenuCommands;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu extends ViewUtils {


    public static MenuSwitcherMessages run() {
        String input;
        Matcher matcher;
        System.out.println("--------Main menu-----");
        while (true) {
            input=scanner.nextLine().trim();
            if ((matcher= MainMenuCommands.getMatcher(input,MainMenuCommands.LOGOUT))!=null) {
                LoginController.userLogout();
                return MenuSwitcherMessages.LOGIN;
            }else if((matcher= MainMenuCommands.getMatcher(input,MainMenuCommands.GOTO_PROFILE_MENU))!=null){
                return MenuSwitcherMessages.PROFILE;
            }else {
                System.out.println("Invalid command!");
            }
        }

    }

    private static boolean startNewGame(Matcher matcher) {
        //TODO:
        return false;
    }

    private static boolean logout(Matcher matcher) {
        //TODO:
        return false;
    }

}
