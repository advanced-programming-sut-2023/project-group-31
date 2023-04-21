package controller.game_menu;

import model.game_stuff.Player;
import model.game_stuff.Trade;
import view.user_system.messages.UserMessages;

import java.util.regex.Matcher;

public class TradeController extends KingdomController{
    public static String trade(Player player, Matcher matcher){
        String type=matcher.group("resourceType");
        int amount=Integer.parseInt(matcher.group("resourceAmount"));
        int price=Integer.parseInt(matcher.group("price"));
        String message=matcher.group("message");
        if(amount<=0||price<=0){
            return "invalid amount or price";
        }
        //if()//invalid type error
            Trade target;
            player.getGame().addToTrades(target=new Trade(player));
            player.getToShowInHistoryOfTrades().add(target);
            return "done success!";
    }

    public static String tradeList(Player player){
        StringBuilder result=new StringBuilder();
        int i=1;
        result.append("list of trades:").append('\n');
        for (Trade trade : player.getGame().getTrades()) {
            result.append(i).append("name of owner : "+trade.getOwner().getOwner().getNickname()+" "+trade.getId()).append('\n');
        }
        return result.toString();
    }
    public static String tradeAccept(Player player,Matcher matcher){
          String id=matcher.group("id");
          Trade target;
          if((target=player.getGame().findTradeWithId(id))==null){
              return "No trade exist with this id";
          }
          player.getGame().getTrades().remove(target);
          target.setMessage(matcher.group("message"));
          target.getOwner().getAcceptedDonation().add(target);
          player.getAcceptedDonation().add(target);
          return "Request accepted successfully!";
    }

    public static String tradeHistory(Player player){
        StringBuilder result=new StringBuilder();
        result.append("donation that done before : ").append('\n');
        for (Trade trade : player.getAcceptedDonation()) {
            result.append("sender : "+trade.getOwner().getOwner().getNickname()).append("message : "+trade.getMessage()).append('\n');
        }
        result.append("request to show in this time : ").append('\n');
        for (Trade toShowInHistoryOfTrade : player.getToShowInHistoryOfTrades()) {
            result.append("sender : "+toShowInHistoryOfTrade.getOwner().getOwner().getNickname()).append("message : "+toShowInHistoryOfTrade.getMessage()).append('\n');
        }
        player.getToShowInHistoryOfTrades().clear();
        return result.toString();
    }
}
