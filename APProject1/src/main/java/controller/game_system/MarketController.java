package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.Storage;
import model.game_stuff.enums.ItemTypes;
import model.game_stuff.enums.Items;
import view.game_system.messages.MarketMessages;

import java.util.Map;

public class MarketController extends ControllerUtils {
    public static String showPriceList(){
        StringBuilder result=new StringBuilder();
        result.append("product in stockpile : ").append('\n');
        for (Items value : Items.values()) {
            if(value.getType()== ItemTypes.RAW_MATERIAL){
                result.append("item name : ").append(value.getName()).append("price : ").append(value.getCost()).append('\n');
            }
        }
        result.append("product of granary : ").append('\n');
        for (Items value : Items.values()) {
            if(value.getType()==ItemTypes.FOOD){
                result.append("item name : ").append(value.getName()).append("price : ").append(value.getCost()).append('\n');
            }
        }
        result.append("product of weaponry : ").append('\n');
        for (Items value : Items.values()) {
            if(value.getType()==ItemTypes.WEAPON){
                result.append("item name : ").append(value.getName()).append("price : ").append(value.getCost()).append('\n');
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
        if (currentPlayer.getPossession().getGold()<target.getCost()*Integer.parseInt(inputs.get("amount"))){
            return MarketMessages.NOT_ENOUGH_GOLD;
        }
        int number=Integer.parseInt(inputs.get("amount"));
        int copyOfNumber=number;
        boolean done=false;
        switch (target.getType()){
            case WEAPON:
                while (number>0){
                    for (Storage weaponry : currentPlayer.getWeaponries()) {
                        if(weaponry.getCapacityLeft()>=number){
                            weaponry.addProduct(target,Integer.parseInt(inputs.get("amount")));
                            number=0;
                            done=true;
                            break;
                        }
                        if(weaponry.getCapacityLeft()<number){
                            weaponry.addProduct(target,weaponry.getCapacityLeft());
                            number=number-weaponry.getCapacityLeft();
                            continue;
                        }
                        if(number==0){
                            done=true;
                            break;
                        }
                    }
                }
                break;
            case RAW_MATERIAL:
                while (number>0){
                    for (Storage weaponry : currentPlayer.getStockpiles()) {
                        if(weaponry.getCapacityLeft()>=number){
                            weaponry.addProduct(target,Integer.parseInt(inputs.get("amount")));
                            number=0;
                            done=true;
                            break;
                        }
                        if(weaponry.getCapacityLeft()<number){
                            weaponry.addProduct(target,weaponry.getCapacityLeft());
                            number=number-weaponry.getCapacityLeft();
                            continue;
                        }
                        if(number==0){
                            done=true;
                            break;
                        }
                    }
                }
                break;
            case FOOD:
                while (number>0){
                    for (Storage weaponry : currentPlayer.getGranaries()) {
                        if(weaponry.getCapacityLeft()>=number){
                            weaponry.addProduct(target,Integer.parseInt(inputs.get("amount")));
                            number=0;
                            done=true;
                            break;
                        }
                        if(weaponry.getCapacityLeft()<number){
                            weaponry.addProduct(target,weaponry.getCapacityLeft());
                            number=number-weaponry.getCapacityLeft();
                            continue;
                        }
                        if (number==0){
                            done=true;
                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }
        if(done==false){
            int toIncrease=copyOfNumber-number;
            currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-toIncrease*target.getCost());
            return MarketMessages.YOUR_STOCKPILE_IS_FULL;
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
        int number=Integer.parseInt(inputs.get("amount"));
        if(target==null){
            return MarketMessages.INVALID_GOOD_NAME;
        }
        if(currentPlayer.getPossession().getItem(target)<number){
            return MarketMessages.NOT_ENOUGH_GOOD;
        }
        boolean done=false;
        switch (target.getType()){
            case WEAPON:
                for (Storage weaponry : currentPlayer.getWeaponries()) {
                    if(weaponry.getProperties().containsKey(target)){
                        if(weaponry.getProperties().get(target)>=number) {
                            int newNum = weaponry.getProperties().get(target) - number;
                            weaponry.removeProduct(target, weaponry.getProperties().get(target));
                            if(newNum==0){
                                break;
                            }
                            weaponry.addProduct(target, newNum);
                            done = true;
                            break;
                        }
                        if(weaponry.getProperties().get(target)<number){
                            weaponry.removeProduct(target,weaponry.getProperties().get(target));
                            number=number-weaponry.getProperties().get(target);
                            continue;
                        }
                    }
                }
                break;
            case FOOD:
                for (Storage granary : currentPlayer.getGranaries()) {
                    if(granary.getProperties().containsKey(target)){
                        if(granary.getProperties().get(target)>=number) {
                            int newNum = granary.getProperties().get(target) - number;
                            granary.removeProduct(target, granary.getProperties().get(target));
                            granary.addProduct(target, newNum);
                            done = true;
                            break;
                        }
                        if(granary.getProperties().get(target)<number){
                            granary.removeProduct(target,granary.getProperties().get(target));
                            number=number-granary.getProperties().get(target);
                            continue;
                        }
                    }
                }
                break;
            case RAW_MATERIAL:
                for (Storage stockpile : currentPlayer.getStockpiles()) {
                    if(stockpile.getProperties().containsKey(target)){
                        if(stockpile.getProperties().get(target)>=number) {
                            int newNum = stockpile.getProperties().get(target) - number;
                            stockpile.removeProduct(target, stockpile.getProperties().get(target));
                            stockpile.addProduct(target, newNum);
                            done = true;
                            break;
                        }
                        if(stockpile.getProperties().get(target)<number){
                            stockpile.removeProduct(target, stockpile.getProperties().get(target));
                            number=number-stockpile.getProperties().get(target);
                            continue;
                        }
                    }
                }
                break;
            default:
                break;
        }
        if(done==false){
            return MarketMessages.NOT_ENOUGH_GOOD;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()+target.getCost()*Integer.parseInt(inputs.get("amount")));
        return MarketMessages.SUCCESS;
    }
}
