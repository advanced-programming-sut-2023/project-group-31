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
            else if( (matcher==MarketCommads.getMatch(command,MarketCommands.SELL))!=null){
                sell(matcher);
            }
            else if((matcher==MarketCommands.getMatch(command,MarketCommands.BUY))!=null){
                buy(matcher);
            }
            else if (MarketCommands.getMatch(command,MarketCommands.SHOWPRICELIST)!=null){
                System.out.println(MarketController.showPriceList());
            }
            else {
                System.out.println("Invalid commands!");
            }
        }
    }



    private static void buy(Matcher matcher){
       ControllerUtils.setInputs(putInHashmap(matcher,MarketCommands.BUY.getRegex()));
       MarketMessages message = MarketController.buy();
       if(message==MarketMessages.SUCCESS){
           System.out.println("you buy good successfully!");
       }
       else if(message=MarketMessages.INVALIDCOMMAND){
           System.out.println("Invalid commands!");
       }
       else {
           System.out.println("buy good failed "+message.getTxt());
       }
    }

    private static void sell(Matcher matcher){
        ControllerUtils.setInputs(putInHashmap(matcher,MarketCommands.BUY.getRegex()));
        MarketMessages message = MarketController.buy();
        if(message==MarketMessages.SUCCESS){
            System.out.println("you buy good successfully!");
        }
        else if(message=MarketMessages.INVALIDCOMMAND){
            System.out.println("Invalid commands!");
        }
        else {
            System.out.println("sell good failed "+message.getTxt());
        }
    }
}
