package controller.game_system;

import view.game_system.messages.MarketMessages;

public class MarketController extends controller.ControllerUtils {
    public static String showPriceList(){
        //TODO   KINGDOM CONTROLLER
        // StringBuilder result
        //getCurrentGovernment.getPossession().for
        //result.append(good.getName()).append(good.getPrice)
        //result.append('\n');
        /*ya mishe ye tabe static az ye possession tarif ke
        hame kala haro dashte bashe*/
        return null;
    }
    public static MarketMessages buy(){
        if(inputs.get("item")==null){
            return MarketMessages.INVALID_COMMAND;
        }
        if(Integer.parseInt(inputs.get("amount"))<=0){
            return MarketMessages.INVALID_COMMAND;
        }
        //getCurrentGovernment.getPossession().for
        //if(good.getName.equals(item))
        // return good
        //if not return MarketMessages.INVALID_GOOD_NAME
        //if(User.getGold<amount*good.getPrice() return NOT_ENOUGH_GOLD
        //if(government.isStockpilesFull) return YOUR_STOCKPILE_IS_FULL
        //else user.setgold(user.getgold-amount*goodPrice()) stockpile add good and return SUCCESS
        return null;
    }
    public static MarketMessages sell(){
        if(inputs.get("item")==null){
            return MarketMessages.INVALID_COMMAND;
        }
        if(Integer.parseInt(inputs.get("amount"))<=0){
            return MarketMessages.INVALID_COMMAND;
        }
        //getCurrentGovernment.getPossession().for
        //if(good.getName().equals(item))
        //return good
        // if not return  INVALID_GOOD_NAME
        //if(good.getCount()<amount)return NOT_ENOUGH_GOOD
        // currentUser.getPossession.get(item).setNumber(getNumber-amount)
        //currentUser.setgold(getgold+amount*good.getPrice())
        //return SUCCESS
        return null;
    }
}
