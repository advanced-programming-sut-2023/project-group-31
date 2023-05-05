package view.game_system;

import controller.game_menu.BarrackController;
import main.java.view.game_system.commands.BarracksCommand;
import main.java.view.game_system.messages.BarracksMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BarracksMenu extends view.ViewUtils {
    public static view.game_system.messages.GameSwitcherMessages run(Scanner scanner){
        String command;
        Matcher matcher;
        while (true){
            command=scanner.nextLine().trim();
            if(command.matches("exit")){
                return view.game_system.messages.GameSwitcherMessages.GAME;
            }
            else if((matcher= BarracksCommand.getMatcher(command,BarracksCommand.CREATE_UNIT))!=null){
                createUnit(matcher);
            }
        }
    }

    private static void createUnit(Matcher matcher) {
        controller.ControllerUtils.setInputs(putInHashmap(matcher,BarracksCommand.CREATE_UNIT.getRegex()));
        BarracksMessages message= BarrackController.createUnit();
        if(message==BarracksMessages.INVALID_COMMAND){
            System.out.println("Invalid command!");
            return;
        }
        else if(message==BarracksMessages.SUCCESS){
            System.out.println("create unit "+matcher.group("type")+"successfully done");
            return;
        }
        System.out.println("create unit failed : "+message.getCommand());
    }
}
