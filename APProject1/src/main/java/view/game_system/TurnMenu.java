package view.game_system;

import controller.game_system.TurnController;
import view.ViewUtils;
import view.game_system.commands.TurnCommands;
import view.game_system.messages.TurnMessages;
import view.viewStyle.Colors;

import java.util.regex.Matcher;

public class TurnMenu extends ViewUtils {
    private static TurnMessages result;
    public static void run() {
        String command;
        Matcher matcher;
        System.out.println("\n"+Colors.GREEN.getBackgroundColorCode()+"Welcome to the great game of stronghold crusader!"
                +Colors.RESET.getBackgroundColorCode()+"\n");
        announceCurrentPlayer();
        while (true) {
            command = scanner.nextLine();
            if ((matcher = TurnCommands.getMatcher(command, TurnCommands.DROP_BUILDING)) != null) {
                putInHashmap(matcher, TurnCommands.DROP_BUILDING.getRegex());
                dropBuilding();
            } else if ((matcher = TurnCommands.getMatcher(command, TurnCommands.REPAIR)) != null) {
                putInHashmap(matcher, TurnCommands.REPAIR.getRegex());
                repair();
            } else if ((matcher = TurnCommands.getMatcher(command, TurnCommands.SELECT_BUILDING)) != null) {
                putInHashmap(matcher, TurnCommands.SELECT_BUILDING.getRegex());
                selectBuilding();
            } else if (command.equalsIgnoreCase("goto map menu")) {
                MapMenu.run();
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void announceCurrentPlayer() {
        System.out.println(TurnController.getCurrentPlayer().getName() + " plays!\n---------------------------");
    }

    private static void selectBuilding() {
        result = TurnController.selectBuilding();
        if (result.equals(TurnMessages.EMPTY_PLACE)) {
            System.out.println(result.getTxt());
        } else if (result.equals(TurnMessages.BARRACK)) {
            BarracksMenu.run();
        } else if (result.equals(TurnMessages.MERCENARY_POST)) {
            MercenaryPostMenu.run();
        }
    }

    private static void repair() {
        result = TurnController.repair();
    }

    private static void dropBuilding() {
        result = TurnController.dropBuilding();
        if (result.equals(TurnMessages.SUCCESS)) {
            System.out.println("building created successfully.");
        } else {
            System.out.println(result.getTxt());
        }
    }
}
