package view.game_system;

import controller.ControllerUtils;
import controller.game_menu.TradeController;
import view.ViewUtils;
import view.game_system.commands.TradeCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.TradeMessages;
import view.user_system.commands.RegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu extends ViewUtils {
    public static GameSwitcherMessages run(Scanner scanner) {
        String command;
        Matcher matcher;
        while(true) {
            command = scanner.nextLine().trim();
            if ((matcher= TradeCommands.getMatcher(command,TradeCommands.ADD_REQUEST))!=null) {
                addRequest(matcher);
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void addRequest(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher, TradeCommands.ADD_REQUEST.getRegex()));
        TradeMessages message = TradeController.addRequest();
    }
}
