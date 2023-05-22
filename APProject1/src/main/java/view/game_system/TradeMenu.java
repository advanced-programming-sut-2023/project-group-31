package view.game_system;

import controller.ControllerUtils;
import controller.game_system.TradeController;
import view.ViewUtils;
import view.game_system.commands.TradeCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.TradeMessages;

import java.util.regex.Matcher;

public class TradeMenu extends ViewUtils {
    public static GameSwitcherMessages run() {
        System.out.println(TradeController.showNewTrades());
        String command;
        Matcher matcher;
        while(true) {
            command = scanner.nextLine().trim();
            if(command.matches("exit")) {
                return GameSwitcherMessages.GAME;
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ADD_REQUEST))!=null) {
                addRequest(matcher);
            } else if(TradeCommands.getMatcher(command,TradeCommands.SHOW_MY_TRADE_HISTORY)!=null) {
                System.out.println(TradeController.showMyTradeHistory());
            } else if(TradeCommands.getMatcher(command,TradeCommands.SHOW_TRADE_LIST)!=null) {
                System.out.println(TradeController.showMyTradeList());
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ADD_AUDIENCE))!=null) {
                addAudience(matcher);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.REMOVE_AUDIENCE))!=null) {
                removeAudience(matcher);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ADD_ITEM_TO_GET))!=null) {
                addItem(matcher, true);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.REMOVE_ITEM_FROM_GET))!=null) {
                removeItem(matcher, true);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.ADD_ITEM_TO_GIVE))!=null) {
                addItem(matcher, false);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.REMOVE_ITEM_FROM_GIVE))!=null) {
                removeItem(matcher, false);
            } else if ((matcher = TradeCommands.getMatcher(command,TradeCommands.SHOW_CURRENT_TRADE))!=null) {
                showCurrentTrade(matcher);
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void showCurrentTrade(Matcher matcher) {

    }

    private static void removeItem(Matcher matcher, boolean toGet) {
        String itemString = fixDoubleQuotes(matcher.group("item"));
        int amount = Integer.parseInt(matcher.group("amount"));
        TradeMessages message = TradeController.removeItem(itemString, amount, toGet);
        if(message == TradeMessages.SUCCESS) {
            System.out.println("remove item successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("remove item failed: " + message.getTxt());
    }

    private static void addItem(Matcher matcher, boolean toGet) {
        String itemString = fixDoubleQuotes(matcher.group("item"));
        int amount = Integer.parseInt(matcher.group("amount"));
        TradeMessages message = TradeController.addItem(itemString, amount, toGet);
        if(message == TradeMessages.SUCCESS) {
            System.out.println("add item successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("add item failed: " + message.getTxt());
    }

    private static void removeAudience(Matcher matcher) {
        String playerString = fixDoubleQuotes(matcher.group("player"));
        TradeMessages message = TradeController.removeAudience(playerString);
        if(message == TradeMessages.SUCCESS) {
            System.out.println("remove audience successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("remove audience failed: " + message.getTxt());
    }

    private static void addAudience(Matcher matcher) {
        String playerString = fixDoubleQuotes(matcher.group("player"));
        TradeMessages message = TradeController.addAudience(playerString);
        if(message == TradeMessages.SUCCESS) {
            System.out.println("add audience successful!");
            return;
        }
        if(message == TradeMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("add audience failed: " + message.getTxt());
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
