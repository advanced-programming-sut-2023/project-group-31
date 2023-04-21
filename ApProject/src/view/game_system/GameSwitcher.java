package view.game_system;


import view.game_system.messages.GameSwitcherMessages;

import java.util.Scanner;

public class GameSwitcher {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        GameSwitcherMessages command = GameSwitcherMessages.GAME;
        while (true) {
            switch (command) {
                case GAME:
                    command = StartGameMenu.run(scanner);
                    break;
                case BACK:
                    command=StartGameMenu.run(scanner);
                    break;
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
