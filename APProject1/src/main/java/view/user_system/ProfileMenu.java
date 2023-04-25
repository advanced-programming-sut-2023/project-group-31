package view.user_system;

import controller.user_menu.ProfileController;
import view.ViewUtils;
import view.user_system.commands.ProfileCommands;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends ViewUtils {
    public static MenuSwitcherMessages run(Scanner scanner){
        String input;
        Matcher matcher;
        while(true){
            input=scanner.nextLine().trim();
            if(input.equalsIgnoreCase("exit")){
                return MenuSwitcherMessages.MAIN;
            }else if((matcher= ProfileCommands.getMatcher(input,ProfileCommands.DISPLAY_PROFILE))!=null){
                ViewUtils.putInHashmap(matcher,ProfileCommands.DISPLAY_PROFILE.getRegex());
                displayProfile();
            }else{
                System.out.println("Invalid command!");
            }

        }

    }

    private static void displayProfile() {
        result= ProfileController.profileDisplay();
        if(result.equals(UserMessages.MESSAGES)){
            System.out.println(result);
        }else{
            System.out.println("display profile failed: "+result);
        }
    }

    private static void profileChange(){

    }

    private static void profileDisplaySlogan(){

    }

}
