package view.user_system;

import view.ViewUtils;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends ViewUtils{
    public static MenuSwitcherMessages run(Scanner scanner){
        String input;
        Matcher matcher;
        while(true) {
            input = scanner.nextLine();
            if ((matcher= RegisterCommands.getMatcher(input,RegisterCommands.REGISTER))!=null) {
                userCreate(matcher);
            }else{
                System.out.println("Invalid command!");
            }
        }

    }

    private static void userCreate(Matcher matcher){
        isFormatCurrent(matcher,RegisterCommands.REGISTER.getRegex());
    }

}