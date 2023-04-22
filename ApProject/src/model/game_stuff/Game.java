package model.game_stuff;

import view.game_system.TradeMenu;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player>players;
    private TradeMenu tradeMenu;
    private ArrayList <Trade>trades;
    public Game(){
        this.trades=new ArrayList<>();
        this.players=new ArrayList<>();
        new TradeMenu(this);
    }
    public void addToTrades(Trade trade){
        trade.setGame(this);
        trades.add(trade);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Trade findTradeWithId(String id){
        for (Trade trade : trades) {
            if(trade.getId().equals(id)){
                return trade;
            }
        }
        return null;
    }
}
