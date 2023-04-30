package view.game_system;

import view.game_system.messages.GameSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketMenu extends ViewUtils {
    public static GameSwitcherMessages run(Scanner scanner){
        String command ;
        Matcher matcher;
        while (true){
            command=scanner.nextLine().trim();
            if(command.matches("exit")){
                return GameSwitcherMessages.GAME;
            }
            else if( (matcher=TradeCommands.get))
        }
    }

    private static void showPriceList(Matcher matcher){

    }

    private static void buy(Matcher matcher){

    }

    private static void sell(Matcher matcher){

    }
}
