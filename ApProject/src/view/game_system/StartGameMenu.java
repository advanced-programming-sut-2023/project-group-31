package view.game_system;

import controller.game_menu.StartGameController;
import view.game_system.commands.StartGameCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StartGameMenu {
    public static MenuSwitcherMessages run(Scanner scanner) {
        Matcher matcher;
        String command;
        while(true) {
            command = scanner.nextLine().trim();
            if(StartGameCommands.getMatcher(command, StartGameCommands.EXIT) != null) {
                return MenuSwitcherMessages.MAIN;
            } else if(StartGameCommands.getMatcher(command, StartGameCommands.SHOW_MAPS) != null) {
                System.out.println(StartGameController.showMaps());
            } else if(StartGameCommands.getMatcher(command, StartGameCommands.SHOW_PLAYERS) != null) {
                System.out.println(StartGameController.showAllPlayers());
            } else if (StartGameCommands.getMatcher(command, StartGameCommands.SHOW_CHOSEN_PLAYERS) != null) {
                System.out.println(StartGameController.showPlayersInTheGame());
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.CHOOSE_MAP)) != null) {
                chooseMap(matcher);
            } else if (StartGameCommands.getMatcher(command, StartGameCommands.SET_A_BLOCK_TEXTURE) != null) {
                setABlocksTexture(matcher);
            }
        }
    }

    private static void setABlocksTexture(Matcher matcher) {
        //TODO
    }

    private static void chooseMap(Matcher matcher) {
        String name = matcher.group("name").trim().replaceAll("\"","");
        StartGameMessages message = StartGameController.chooseMap(name);
        if(message == StartGameMessages.SUCCESS) {
            System.out.println("choose map successful!");
        }
        System.out.println(message.getTxt());
    }

    private void addPlayer(Matcher matcher) {
    }
    private void removePlayer(Matcher matcher) {
    }

    private boolean start() {

        return false;
    }
    private void setTexture(Matcher matcher) {

    }
    private void clear(Matcher matcher) {

    }
    private void dropRock(Matcher matcher) {

    }

    private void dropTree(Matcher matcher) {

    }

    private void dropLordHouse(Matcher matcher) {

    }

}
