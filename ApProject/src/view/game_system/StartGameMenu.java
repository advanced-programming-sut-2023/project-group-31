package view.game_system;

import controller.ControllerUtils;
import controller.game_menu.StartGameController;
import view.game_system.commands.StartGameCommands;
import view.game_system.commands.TradeCommands;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.MenuSwitcherMessages;
import view.ViewUtils;

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
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_A_BLOCK_TEXTURE)) != null) {
                setABlocksTexture(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_TEXTURE)) != null) {

            }
        }
    }

    private static void setABlocksTexture(Matcher matcher) {
        ControllerUtils.setInputs(ViewUtils.putInHashmap(matcher, StartGameCommands.SET_A_BLOCK_TEXTURE.getRegex()));
        StartGameMessages message = StartGameController.setABlockTexture();
        if(message == StartGameMessages.SUCCESS) {
            System.out.println("set texture successful!");
            return;
        }
        System.out.println(message.getTxt());
    }

    private static void chooseMap(Matcher matcher) {
        String name = matcher.group("name").trim().replaceAll("\"","");
        StartGameMessages message = StartGameController.chooseMap(name);
        if(message == StartGameMessages.SUCCESS) {
            System.out.println("choose map successful!");
            return;
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
