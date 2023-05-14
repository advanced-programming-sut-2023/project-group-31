package view.game_system;

import controller.ControllerUtils;
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
            } else if (TurnCommands.getMatcher(command, TurnCommands.NEXT_TURN) != null) {
                if(nextTurn()) {
                    return;
                }
            } else if (TurnCommands.getMatcher(command, TurnCommands.EXIT) != null) {
                System.out.println("narrow!\tto ham mese man nemitooni davoom biari... my lord!");
                return;
            }  else if ((matcher = TurnCommands.getMatcher(command, TurnCommands.SELECT_UNIT)) != null) {
                selectUnit(matcher);
            } else if ((matcher = TurnCommands.getMatcher(command, TurnCommands.SELECT_MULTIPLE_UNITS)) != null) {
                selectMultipleUnit(matcher);
            } else if (TurnCommands.getMatcher(command, TurnCommands.GOVERNMENT_MENU) != null) {
                KingdomMenu.run();
            } else if (TurnCommands.getMatcher(command, TurnCommands.TRADE_MENU) != null) {
                TradeMenu.run();
            } else if (command.equalsIgnoreCase("goto map menu")) {
                MapMenu.run();
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void selectMultipleUnit(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher, TurnCommands.SELECT_MULTIPLE_UNITS.getRegex()));
        TurnMessages message = TurnController.selectMultipleUnits();
        if(message == TurnMessages.SUCCESS) {
            UnitMenu.run();
        } else if(message == TurnMessages.INVALID_COMMAND){
            System.out.println("invalid command!");
        } else {
            System.out.println("select unit failed: " + message.getTxt());
        }
    }

    private static void selectUnit(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher, TurnCommands.SELECT_UNIT.getRegex()));
        TurnMessages message = TurnController.selectUnit();
        if(message == TurnMessages.SUCCESS) {
            UnitMenu.run();
        } else if(message == TurnMessages.INVALID_COMMAND){
            System.out.println("invalid command!");
        } else {
            System.out.println("select unit failed: " + message.getTxt());
        }
    }

    private static boolean nextTurn() {
        TurnMessages message = TurnController.nextTurn();
        if(message == TurnMessages.GAME_FINISHED) {
            System.out.println(TurnMessages.GAME_FINISHED.getTxt());
            return true;
        }
        if(message == TurnMessages.SUCCESS) {
            announceCurrentPlayer();
            return false;
        }
        System.out.println("next turn failed: " + message.getTxt());
        return false;
    }

    private static void announceCurrentPlayer() {
        System.out.println(TurnController.getCurrentPlayer().getName() + " plays!\n---------------------------");
    }

    private static void selectBuilding() {
        result = TurnController.selectBuilding();
        System.out.println(result.getTxt());
        if (result.equals(TurnMessages.EMPTY_PLACE)) {
            return;
        } else if (result.equals(TurnMessages.BARRACK)) {
            BarracksMenu.run();
        } else if (result.equals(TurnMessages.MERCENARY_POST)) {
            MercenaryPostMenu.run();
        } else if (result.equals(TurnMessages.LORD_HOUSE)) {
            KingdomMenu.run();
        } else if (result.equals(TurnMessages.MARKET)) {
            MarketMenu.run();
        } else{
            System.out.println(result.getTxt());
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
