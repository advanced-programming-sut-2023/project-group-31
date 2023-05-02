package view.game_system;

import controller.ControllerUtils;
import controller.game_menu.MarketController;
import view.ViewUtils;
import view.game_system.commands.MarketCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.MarketMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketMenu extends ViewUtils {
    public static GameSwitcherMessages run(Scanner scanner){
        String command ;
        Matcher matcher=null;
        while (true){
            command=scanner.nextLine().trim();
            if(command.matches("exit")){
                return GameSwitcherMessages.GAME;
            }
            else if((matcher=MarketCommands.getMatcher(command,MarketCommands.SELL))!=null){
                sell(matcher);
            }
            else if((matcher=MarketCommands.getMatcher(command,MarketCommands.BUY))!=null){
                buy(matcher);
            }
            else if (MarketCommands.getMatcher(command,MarketCommands.SHOWPRICELIST)!=null){
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
       else if(message==MarketMessages.INVALID_COMMAND){
           System.out.println("Invalid commands!");
       }
       else {
           System.out.println("buy good failed "+message.getTxt());
       }
    }

    private static void sell(Matcher matcher){
        ControllerUtils.setInputs(putInHashmap(matcher,MarketCommands.BUY.getRegex()));
        MarketMessages message = MarketController.sell();
        if(message==MarketMessages.SUCCESS){
            System.out.println("you buy good successfully!");
        }
        else if(message==MarketMessages.INVALID_COMMAND){
            System.out.println("Invalid commands!");
        }
        else {
            System.out.println("sell good failed "+message.getTxt());
        }
    }
}
