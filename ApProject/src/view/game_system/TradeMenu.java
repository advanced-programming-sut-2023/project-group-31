package view.game_system;

import controller.game_menu.TradeController;
import model.game_stuff.Game;
import model.game_stuff.Trade;
import view.game_system.commands.TradeCommands;
import view.game_system.messages.GameSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private Game game;
    public TradeMenu(Game game){
        this.game=game;
    }
    public static GameSwitcherMessages run(Scanner scanner) {
        Matcher matcher;
        String command;
        System.out.println(TradeController.tradeHistory(this.getGame().getCurrentTurnUser()));
        while (true){
            command=scanner.nextLine();
            if( TradeCommands.getMatch(command,TradeCommands.TRADEHISTORY)!=null){
                System.out.println(TradeController.tradeHistory(this.getGame().getCurrentTrunUser()));
            }
            if((matcher=TradeCommands.getMatch(command,TradeCommands.TRADE))!=null){
                System.out.println(TradeController.trade(this.getGame().getCurrentTurnUser(),matcher));
            }
            if(TradeCommands.getMatch(command,TradeCommands.TRADELIST)!=null){
                System.out.println( TradeController.tradeList(this.getGame().getcurrentTurnUser()));
            }
            if((matcher=TradeCommands.getMatch(command,TradeCommands.ACCEPTTRADE))!=null){
                System.out.println(TradeController.tradeAccept(this.getGame().getCurrentTurnUser(),matcher));
            }
            if(TradeCommands.getMatch(command,TradeCommands.BACK)!=null){
                return GameSwitcherMessages.BACK;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

    public Game getGame() {
        return game;
    }
}
