package view.game_system;

import controller.ControllerUtils;
import controller.game_system.EngineerGuildController;
import view.ViewUtils;
import view.game_system.messages.BarracksMessages;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.commands.*;
import java.util.Scanner;
import java.util.regex.Matcher;

import static view.ViewUtils.putInHashmap;

public class EngineerGuildMenu extends ViewUtils {
    public static GameSwitcherMessages run(){
        String command ;
        Matcher matcher;
        while (true){
            command=scanner.nextLine().trim();
            if((matcher=BarracksCommand.getMatcher(command,BarracksCommand.CREATE_UNIT))!=null){
                createUnite(matcher);
                continue;
            }
            else if(command.matches("exit")){
                return GameSwitcherMessages.GAME;
            }
            else {
                System.out.printf("Invalid command!");
            }
        }
    }

    private static void createUnite(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher,BarracksCommand.CREATE_UNIT.getRegex()));
        BarracksMessages message= EngineerGuildController.createUnit();
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