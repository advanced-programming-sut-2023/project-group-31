package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Government;
import model.game_stuff.Trade;
import model.game_stuff.enums.Items;
import view.game_system.messages.TradeMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeController extends ControllerUtils {
    private static final ArrayList<Government> currentAudiences = new ArrayList<>();
    private static final HashMap<Items, Integer> currentProvidedItems = new HashMap<>();
    private static final HashMap<Items, Integer> currentAskedItems = new HashMap<>();

    public static TradeMessages addRequest() {
        if (inputs.get("give") == null || inputs.get("get") == null) {
            return TradeMessages.INVALID_COMMAND;
        }
        if (inputs.get("player") == null) {
            return TradeMessages.CHOOSE_YOUR_AUDIENCE;
        }
        Government audience = currentGame.getPlayerByNickname(inputs.get("player"));
        if (audience == null) {
            return TradeMessages.NO_SUCH_PLAYER;
        }
        if (audience.equals(currentPlayer)) {
            return TradeMessages.YOU_CAN_NOT_TRADE_WITH_YOURSELF;
        }
        HashMap<String, Integer> stringResourcesToGet = takeItemsOutFromString(inputs.get("get"));
        HashMap<String, Integer> stringResourcesToGive = takeItemsOutFromString(inputs.get("give"));
        if (stringResourcesToGive.isEmpty() && stringResourcesToGet.isEmpty()) {
            return TradeMessages.INVALID_TRADE;
        }
        HashMap<Items, Integer> resourcesToGet = new HashMap<>();
        HashMap<Items, Integer> resourcesToGive = new HashMap<>();
        if (stringHashmapToItemHashmapFailed(stringResourcesToGet, resourcesToGet)) {
            return TradeMessages.INVALID_RESOURCE_TYPE;
        }
        if (stringHashmapToItemHashmapFailed(stringResourcesToGive, resourcesToGive)) {
            return TradeMessages.INVALID_RESOURCE_TYPE;
        }
        Trade trade = new Trade(currentPlayer, audience);
        trade.setProvidedItems(resourcesToGet);
        trade.setAskedItems(resourcesToGive);
        if (inputs.get("message") != null) {
            trade.setOwnersMessage(inputs.get("message"));
        }
        currentPlayer.addTrade(trade);
        audience.addTrade(trade);
        return TradeMessages.SUCCESS;
    }

    private static boolean stringHashmapToItemHashmapFailed(HashMap<String, Integer> stringResources, HashMap<Items, Integer> resources) {
        Items item;
        for (String string : stringResources.keySet()) {
            if ((item = Items.getItemByName(string)) == null) {
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
        while (matcher.find()) {
            resource = matcher.group("resourceType").trim();
            if (resource.matches("\"[^\"]*\"")) {
                resource = resource.replaceAll("\"", "");
            }
            amount = Integer.parseInt(matcher.group("resourceAmount"));
            output.put(resource, amount);
        }
        return output;
    }

    public static String showMyTradeHistory() {
        if (currentPlayer.getTradeHistory().isEmpty()) {
            return "You have not any trade yet!";
        }
        StringBuilder output = new StringBuilder("TRADE HISTORY:");
        for (Trade trade : currentPlayer.getTradeHistory()) {
            output.append("\n").append(trade);
        }
        return output.toString();
    }

    public static String showMyTradeList() {
        StringBuilder output = new StringBuilder("TRADES:");
        for (Trade liveTrade : Trade.getLiveTrades()) {
            if (liveTrade.getAskedPlayer().equals(currentPlayer)) {
                output.append("\n").append(liveTrade);
            }
        }
        return output.toString();
    }

    public static String showNewTrades() {
        StringBuilder output = new StringBuilder("NEW TRADES:");
        for (Trade liveTrade : Trade.getLiveTrades()) {
            if (liveTrade.getAskedPlayer().equals(currentPlayer) && !liveTrade.isWatched()) {
                output.append("\n").append(liveTrade);
            }
        }
        return output.toString();
    }

    public static TradeMessages acceptRequest() {
        if (inputs.get("id") == null) {
            return TradeMessages.INVALID_COMMAND;
        }
        Trade trade = Trade.getTradeById(Integer.parseInt(inputs.get("id")));
        if (trade == null) {
            return TradeMessages.NO_SUCH_TRADE_ID;
        }
        for (Items item : trade.getAskedItems().keySet()) {
            if (currentPlayer.getNumberOfAnItem(item) < trade.getAskedItems().get(item)) {
                TradeMessages.NOT_ENOUGH_RESOURCE.setInput(item.getName());
                return TradeMessages.NOT_ENOUGH_RESOURCE;
            }
        }
        for (Items item : trade.getProvidedItems().keySet()) {
            if (currentPlayer.getNumberOfAnItem(item) < trade.getProvidedItems().get(item)) {
                return TradeMessages.OTHER_ONE_RESOURCE_SHORTAGE;
            }
        }
        if (inputs.get("message") != null) {
            trade.setOthersMessage(inputs.get("message"));
        }
        for (Items item : trade.getProvidedItems().keySet()) {
            trade.getOwner().reduceItem(item, trade.getProvidedItems().get(item));
        }
        for (Items item : trade.getAskedItems().keySet()) {
            trade.getAskedPlayer().reduceItem(item, trade.getAskedItems().get(item));
        }
        for (Items item : trade.getProvidedItems().keySet()) {
            trade.getAskedPlayer().addItem(item, trade.getProvidedItems().get(item));
        }
        for (Items item : trade.getAskedItems().keySet()) {
            trade.getOwner().addItem(item, trade.getAskedItems().get(item));
        }
        trade.removeTrade();
        return TradeMessages.SUCCESS;
    }

    public static TradeMessages addAudience(String playerString) {
        Government player = currentGame.getPlayerByNickname(playerString);
        if (player == null) {
            return TradeMessages.NO_SUCH_PLAYER;
        }
        if (currentAudiences.contains(player)) {
            return TradeMessages.PLAYER_IS_ALREADY_ADDED;
        }
        if (player.equals(currentPlayer)) {
            return TradeMessages.YOU_CAN_NOT_TRADE_WITH_YOURSELF;
        }
        currentAudiences.add(player);
        return TradeMessages.SUCCESS;
    }

    public static TradeMessages removeAudience(String playerString) {
        Government player = currentGame.getPlayerByNickname(playerString);
        if (player == null) {
            return TradeMessages.NO_SUCH_PLAYER;
        }
        if (!currentAudiences.contains(player)) {
            return TradeMessages.NO_SUCH_PLAYER_ADDED_BEFORE;
        }
        currentAudiences.remove(player);
        return TradeMessages.SUCCESS;
    }

    public static TradeMessages addItem(String itemString, int amount, Boolean toGet) {
        Items item = Items.getItemByName(itemString);
        if (item == null) {
            return TradeMessages.NO_SUCH_ITEM;
        }
        if (toGet && currentPlayer.getNumberOfAnItem(item) < amount) {
            TradeMessages.NOT_ENOUGH_RESOURCE.setInput(item.getName());
            return TradeMessages.NOT_ENOUGH_RESOURCE;
        }
        HashMap<Items, Integer> items;
        if (toGet) {
            items = currentAskedItems;
        } else {
            items = currentProvidedItems;
        }
        if (items.containsKey(item)) {
            return TradeMessages.ITEM_IS_ALREADY_ADDED;
        }
        items.put(item, amount);
        return TradeMessages.SUCCESS;
    }

    public static TradeMessages removeItem(String itemString, boolean toGet) {
        Items item = Items.getItemByName(itemString);
        if (item == null) {
            return TradeMessages.NO_SUCH_ITEM;
        }
        HashMap<Items, Integer> items;
        if (toGet) {
            items = currentAskedItems;
        } else {
            items = currentProvidedItems;
        }
        if (!items.containsKey(item)) {
            return TradeMessages.NO_SUCH_ITEM_ADDED_BEFORE;
        }
        items.remove(item);
        return TradeMessages.SUCCESS;
    }

    public static String showCurrentTrade() {
        StringBuilder output = new StringBuilder();
        if (currentAudiences.isEmpty()) {
            output.append("no audience");
        } else {
            output.append("CURRENT AUDIENCES:");
            for (Government government : currentAudiences) {
                output.append("\t").append(government.getName());
            }
        }
        if (currentAskedItems.isEmpty()) {
            output.append("\nno asked item");
        } else {
            output.append("\nCURRENT ASKED ITEMS:");
            for (Items item : currentAskedItems.keySet()) {
                output.append("\t").append(item.getName()).append(" : ").append(currentAskedItems.get(item));
            }
        }
        if (currentProvidedItems.isEmpty()) {
            output.append("\nno provided item");
        } else {
            output.append("\nCURRENT PROVIDED ITEMS:");
            for (Items item : currentProvidedItems.keySet()) {
                output.append("\t").append(item.getName()).append(" : ").append(currentProvidedItems.get(item));
            }
        }
        return output.toString();
    }

    public static TradeMessages submit(String message) {
        if(currentAudiences.isEmpty()) {
            return TradeMessages.NO_AUDIENCE;
        }
        if(currentAskedItems.isEmpty() && currentProvidedItems.isEmpty()) {
            return TradeMessages.INVALID_TRADE;
        }
        Trade trade;
        for (Government audience : currentAudiences) {
            trade = new Trade(currentPlayer, audience);
            trade.setAskedItems(currentAskedItems);
            trade.setProvidedItems(currentProvidedItems);
            trade.setOwnersMessage(message);
            currentPlayer.addTrade(trade);
            audience.addTrade(trade);
        }
        currentProvidedItems.clear();
        currentAskedItems.clear();
        currentAudiences.clear();
        return TradeMessages.SUCCESS;
    }
}
