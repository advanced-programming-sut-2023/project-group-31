package controller.game_menu;

import controller.ControllerUtils;
import model.game_stuff.Government;
import model.game_stuff.Trade;
import model.game_stuff.enums.Items;
import view.game_system.messages.TradeMessages;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeController extends ControllerUtils {
    public static TradeMessages addRequest() {
        if(inputs.get("give") == null || inputs.get("get") == null) {
            return TradeMessages.INVALID_COMMAND;
        }
        if(inputs.get("player") == null) {
            return TradeMessages.CHOOSE_YOUR_AUDIENCE;
        }
        Government audience = currentGame.getPlayerByNickname(inputs.get("player"));
        if(audience == null) {
            return TradeMessages.NO_SUCH_PLAYER;
        }
        if(audience.equals(currentPlayer)) {
            return TradeMessages.YOU_CAN_NOT_TRADE_WITH_YOURSELF;
        }
        HashMap<String, Integer> stringResourcesToGet = takeItemsOutFromString(inputs.get("get"));
        HashMap<String, Integer> stringResourcesToGive = takeItemsOutFromString(inputs.get("give"));
        if(stringResourcesToGive.isEmpty() && stringResourcesToGet.isEmpty()) {
            return TradeMessages.INVALID_TRADE;
        }
        HashMap<Items, Integer> resourcesToGet = new HashMap<>();
        HashMap<Items, Integer> resourcesToGive = new HashMap<>();
        if(stringHashmapToItemHashmapFailed(stringResourcesToGet, resourcesToGet)) {
            return TradeMessages.INVALID_RESOURCE_TYPE;
        }
        if(stringHashmapToItemHashmapFailed(stringResourcesToGive, resourcesToGive)) {
            return TradeMessages.INVALID_RESOURCE_TYPE;
        }
        Trade trade = new Trade(currentPlayer,audience);
        trade.setProvidedItems(resourcesToGet);
        trade.setAskedItems(resourcesToGive);
        if(inputs.get("message") != null) {
            trade.setOwnersMessage(inputs.get("message"));
        }
        currentPlayer.addTrade(trade);
        audience.addTrade(trade);
        return TradeMessages.SUCCESS;
    }
    private static boolean stringHashmapToItemHashmapFailed(HashMap<String, Integer> stringResources, HashMap<Items, Integer> resources) {
        Items item;
        for (String string : stringResources.keySet()) {
            if((item = Items.getItemByName(string)) == null) {
                TradeMessages.INVALID_RESOURCE_TYPE.setInput(string);
                return false;
            }
            resources.put(item, stringResources.get(string));
        }
        return true;
    }
    public static HashMap<String, Integer> takeItemsOutFromString(String input) {
        HashMap<String, Integer> output = new HashMap<>();
        Matcher matcher = Pattern.compile(input.trim()).matcher("-t\\s+(?<resourceType>I)\\s+-a\\s+(?<resourceAmount>\\d+)");
        String resource;
        int amount;
        while(matcher.find()) {
            resource = matcher.group("resourceType").trim();
            if(resource.matches("\"[^\"]*\"")) {
                resource = resource.replaceAll("\"","");
            }
            amount = Integer.parseInt(matcher.group("resourceAmount"));
            output.put(resource, amount);
        }
        return output;
    }

    public static String showMyTradeHistory() {
        /*if(currentPlayer.getTradeHistory().isEmpty()) {
            return "You have not any trade yet!";
        }*/
        String output = "TRADE HISTORY:";
        for (Trade trade : currentPlayer.getTradeHistory()) {
            output += "\n" + trade;
        }
        return output;
    }

    public static String showMyTradeList() {
        String output = "TRADES:";
        for (Trade liveTrade : Trade.getLiveTrades()) {
            if(liveTrade.getAskedPlayer().equals(currentPlayer)) {
                output += "\n" + liveTrade;
            }
        }
        return output;
    }

    public static String showNewTrades() {
        String output = "NEW TRADES:";
        for (Trade liveTrade : Trade.getLiveTrades()) {
            if(liveTrade.getAskedPlayer().equals(currentPlayer) && !liveTrade.isWatched()) {
                output += "\n" + liveTrade;
            }
        }
        return output;
    }

    public static TradeMessages acceptRequest() {
        if(inputs.get("id") == null) {
            return TradeMessages.INVALID_COMMAND;
        }
        Trade trade = Trade.getTradeById(Integer.parseInt(inputs.get("id")));
        if(trade == null) {
            return TradeMessages.NO_SUCH_TRADE_ID;
        }
        for (Items item : trade.getAskedItems().keySet()) {
            if(currentPlayer.getNumberOfAnItem(item) < trade.getAskedItems().get(item)) {
                TradeMessages.NOT_ENOUGH_RESOURCE.setInput(item.getName());
                return TradeMessages.NOT_ENOUGH_RESOURCE;
            }
        }
        for (Items item : trade.getProvidedItems().keySet()) {
            if(currentPlayer.getNumberOfAnItem(item) < trade.getProvidedItems().get(item)) {
                return TradeMessages.OTHER_ONE_RESOURCE_SHORTAGE;
            }
        }
        if(inputs.get("message") != null) {
            trade.setOthersMessage(inputs.get("message"));
        }
        //TODO: hazf kardan item ha az storage ha !!!!!
        return TradeMessages.SUCCESS;
    }
}
