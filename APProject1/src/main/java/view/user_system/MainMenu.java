package view.user_system;

import controller.user_system.LoginController;
import view.ViewUtils;
import view.user_system.commands.MainMenuCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.viewStyle.Colors;

import java.awt.*;
import java.util.regex.Matcher;

public class MainMenu extends ViewUtils {


    public static MenuSwitcherMessages run() {
        String input;
        Matcher matcher;
        System.out.println(Colors.RED +"--------Main menu-----"+Colors.RESET);
        while (true) {
            input=scanner.nextLine().trim();
            if ((matcher= MainMenuCommands.getMatcher(input,MainMenuCommands.LOGOUT))!=null) {
                LoginController.userLogout();
                return MenuSwitcherMessages.LOGIN;
            }else if((matcher= MainMenuCommands.getMatcher(input,MainMenuCommands.GOTO_PROFILE_MENU))!=null){
                return MenuSwitcherMessages.PROFILE;
            }else if(input.equals("start game")){
                return MenuSwitcherMessages.START_GAME;
            }else {
                System.out.println("Invalid command!");
            }
        }

    }
}
