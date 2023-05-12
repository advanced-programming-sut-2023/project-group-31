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
                case BARRACKS:
                    command=BarracksMenu.run(scanner);
                    break;
                case MERCENARY_POST:
                    command=MercenaryPostMenu.run(scanner);
                    break;
                case KINGDOM:
                    command=KingdomMenu.run(scanner);
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
