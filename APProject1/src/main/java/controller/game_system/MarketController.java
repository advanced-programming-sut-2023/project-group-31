package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.Storage;
import model.game_stuff.enums.Items;
import view.game_system.messages.MarketMessages;

import java.util.Map;

public class MarketController extends ControllerUtils {
    public static String showPriceList(){
        StringBuilder result=new StringBuilder();
        for (Storage stockpile : currentPlayer.getStockpiles()) {
            for (Map.Entry<Items, Integer> itemsIntegerEntry : stockpile.getProperties().entrySet()) {
                result.append("item name "+itemsIntegerEntry.getKey().getName()+"item type "+itemsIntegerEntry.getKey().getType()+"number "+itemsIntegerEntry.getValue()).append('\n');
            }
        }
        return result.toString();
    }
    public static MarketMessages buy(){
        if(inputs.get("item")==null){
            return MarketMessages.INVALID_COMMAND;
        }
        if(Integer.parseInt(inputs.get("amount"))<=0){
            return MarketMessages.INVALID_COMMAND;
        }
        Items target= Items.getItemByName(inputs.get("type"));
        if(target==null){
            return MarketMessages.INVALID_GOOD_NAME;
        }
        boolean isFull=false;
        for (Storage stockpile : currentPlayer.getStockpiles()) {
            if (stockpile.isFull()){
                isFull=true;
            }
        }
        if(isFull==false){
            return MarketMessages.YOUR_STOCKPILE_IS_FULL;
        }
        if (currentPlayer.getPossession().getGold()<target.getCost()*Integer.parseInt(inputs.get("amount"))){
            return MarketMessages.NOT_ENOUGH_GOLD;
        }

         currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-target.getCost()*Integer.parseInt(inputs.get("amount")));
         return MarketMessages.SUCCESS;
    }
    public static MarketMessages sell(){
        if(inputs.get("item")==null){
            return MarketMessages.INVALID_COMMAND;
        }
        if(Integer.parseInt(inputs.get("amount"))<=0){
            return MarketMessages.INVALID_COMMAND;
        }
        Items target=Items.getItemByName(inputs.get("item"));
        if(target==null){
            return MarketMessages.INVALID_GOOD_NAME;
        }
        if(currentPlayer.getPossession().getItem(target)==0){
            return MarketMessages.NOT_ENOUGH_GOOD;
        }

        // type bayad moshakhas beshe ke bayad az koja gerefte beshe
            //currentPlayer.getPossession().getItem();

        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()+target.getCost()*Integer.parseInt(inputs.get("amount")));
        return MarketMessages.SUCCESS;
    }
}
