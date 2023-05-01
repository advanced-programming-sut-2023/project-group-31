package view.game_system;


import view.game_system.messages.GameSwitcherMessages;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;

public class GameSwitcher {
    public static MenuSwitcherMessages run() {
        Scanner scanner = new Scanner(System.in);
        GameSwitcherMessages command = GameSwitcherMessages.GAME;
        while (true) {
            switch (command) {
                case GAME:

                    break;
                case BACK:
                    return MenuSwitcherMessages.MAIN;
                case MARKET:
                    command= view.game_system.MarketMenu.run(scanner);
                    break;
                case TRADE:
                    command= view.game_system.TradeMenu.run(scanner);
                // TODO :
            }
        }
    }
}
