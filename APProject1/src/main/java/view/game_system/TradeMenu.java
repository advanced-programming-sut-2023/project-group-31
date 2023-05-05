package view.game_system;

import controller.ControllerUtils;
import controller.game_system.TradeController;
import view.ViewUtils;
import view.game_system.commands.TradeCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.TradeMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu extends ViewUtils {
    public static GameSwitcherMessages run(Scanner scanner) {
        System.out.println(TradeController.showNewTrades());
        String command;
        Matcher matcher;
        while(true) {
            command = scanner.nextLine().trim();
            if(command.matches("exit")) {
                return GameSwitcherMessages.GAME;
            }else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ADD_REQUEST))!=null) {
                addRequest(matcher);
            }else if(TradeCommands.getMatcher(command,TradeCommands.SHOW_MY_TRADE_HISTORY)!=null) {
                System.out.println(TradeController.showMyTradeHistory());
            }else if(TradeCommands.getMatcher(command,TradeCommands.SHOW_TRADE_LIST)!=null) {
                System.out.println(TradeController.showMyTradeList());
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ACCEPT_TRADE))!=null) {
                acceptRequest(matcher);
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void acceptRequest(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher, TradeCommands.ACCEPT_TRADE.getRegex()));
        TradeMessages message = TradeController.acceptRequest();
        if(message == TradeMessages.SUCCESS) {
            System.out.println("accept request successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("accept request failed: " + message.getTxt());
    }

    private static void addRequest(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher, TradeCommands.ADD_REQUEST.getRegex()));
        TradeMessages message = TradeController.addRequest();
        if(message == TradeMessages.SUCCESS) {
            System.out.println("add trade successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("add trade failed: " + message.getTxt());
    }
}
