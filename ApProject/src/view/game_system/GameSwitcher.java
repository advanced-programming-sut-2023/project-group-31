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
                    command=MarketMenu.run(scanner);
                    break;
                case TRADE:
                    command=TradeMenu.run(scanner);
                // TODO :
            }
        }
    }
}
