package view.game_system;


import controller.game_system.MercenaryController;
import main.java.view.game_system.commands.BarracksCommand;
import main.java.view.game_system.messages.BarracksMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

import static view.ViewUtils.putInHashmap;

public class MercenaryPostMenu {
    public static GameSwitcherMessages run(){
        String command;
        Matcher matcher;
        while (true){
            command=scanner.nextLine().trim();
            if(command.matches("exit")){
                return GameSwitcherMessages.GAME;
            }
            else if((matcher= BarracksCommand.getMatcher(command,BarracksCommand.CREATE_UNIT))!=null){
                createUnit(matcher);
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void createUnit(Matcher matcher) {
        controller.ControllerUtils.setInputs(putInHashmap(matcher,BarracksCommand.CREATE_UNIT.getRegex()));
        BarracksMessages message= MercenaryController.createUnit();
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
