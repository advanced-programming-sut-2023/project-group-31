package view.game_system;

import controller.ControllerUtils;
import controller.game_system.KingdomController;
import view.ViewUtils;
import view.game_system.commands.KingdomCommands;
import view.game_system.messages.GameSwitcherMessages;
import view.game_system.messages.KingdomMessages;
import view.game_system.messages.MarketMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class KingdomMenu extends ViewUtils {
    public static GameSwitcherMessages run(){
        String command;
        Matcher matcher;
        while (true){
            command=scanner.nextLine().trim();
            if((matcher= KingdomCommands.getMatcher(command,KingdomCommands.SHOW_POPULARITY_FACTOR))!=null){
                System.out.println(KingdomController.showPopularityFactors());
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.SHOW_POPULARITY))!=null){
                System.out.println(KingdomController.showPopularity());
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.FOOD_RATE))!=null){
                foodRate(matcher);
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.FOOD_RATE_SHOW))!=null){
                System.out.println(KingdomController.showFoodRate());
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.SHOW_FOOD_LIST))!=null){
                System.out.println(KingdomController.showFoodList());
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.TAX_RATE))!=null){
                taxRate(matcher);
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.TAX_RATE_SHOW))!=null){
                System.out.println(KingdomController.showTaxRate());
            }
            else if((matcher=KingdomCommands.getMatcher(command,KingdomCommands.FEAR_RATE))!=null){
                FearRate(matcher);
            }
            else if(command.matches("exit")){
                return GameSwitcherMessages.GAME;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

    private static void FearRate(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher,KingdomCommands.FEAR_RATE.getRegex()));
        KingdomMessages message=KingdomController.fearRate();
        if(message==KingdomMessages.INVALID_COMMAND){
            System.out.println("Invalid command!");
            return;
        }

    }

    private static void taxRate(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher,KingdomCommands.FEAR_RATE.getRegex()));
        KingdomMessages message=KingdomController.taxRate();
        if(message==KingdomMessages.POSITIVE){
            System.out.println("Your popularity has risen");
            return;
        }
        else if(message==KingdomMessages.NEGATIVE){
            System.out.println("People are leaving your castle");
            return;
        }
        else if(message==KingdomMessages.ZERO){
            System.out.println("Your popularity is stable my lord!");
            return;
        }
        else {
            System.out.println("Invalid command!");
        }
    }

    private static void foodRate(Matcher matcher) {
        ControllerUtils.setInputs(putInHashmap(matcher,KingdomCommands.FEAR_RATE.getRegex()));
        KingdomMessages message=KingdomController.foodRate();
        if(message==KingdomMessages.POSITIVE){
            System.out.println("Your popularity has risen");
            return;
        }
        else if(message==KingdomMessages.NEGATIVE){
            System.out.println("People are leaving your castle");
            return;
        }
        else if(message==KingdomMessages.ZERO){
            System.out.println("Your popularity is stable my lord!");
            return;
        }
        else {
            System.out.println("Invalid command!");
        }
    }
}
