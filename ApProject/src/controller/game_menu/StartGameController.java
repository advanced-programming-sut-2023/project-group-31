package controller.game_menu;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.Colors;
import model.game_stuff.Map;
import model.game_stuff.enums.Textures;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.MenuSwitcherMessages;
import view.user_system.messages.UserMessages;

import java.util.Collections;
import java.util.HashMap;

public class StartGameController extends ControllerUtils {

    private static Map chosenMap;
    private static HashMap<User, Colors> usersToPlay;
    public static String showMaps() {
        String output = "CHOSEN MAP:\n" + chosenMap + "\nAVAILABLE MAPS:";
        for (Map map : Map.getMaps()) {
            output += "\n" + map;
        }
        return output;
    }
    public static UserMessages setTexture(int x, int y, String type){
        return null;
    }

    public static UserMessages dropRock(int x, int y){
        return null;
    }

    public static UserMessages dropTree(int x, int y, String type){
        return null;
    }

    public static UserMessages startGame(){
        return null;
    }

    public static String showAllPlayers() {
        String output = "PLAYERS:";
        for (User user : User.getUsers().values()) {
            output += "\n" + user;
        }
        return output;
    }
    public static String showPlayersInTheGame() {
        String output = "PLAYERS:";
        for (User user : usersToPlay.keySet()) {
            output += user.getNickname() + " " + usersToPlay.get(user);
        }
        return output;
    }

    public static StartGameMessages chooseMap(String name) {
        for (Map map : Map.getMaps()) {
            if(map.getName().equals(name)) {
                chosenMap = map;
                return StartGameMessages.SUCCESS;
            }
        }
        return StartGameMessages.MAP_NOT_FOUND;
    }

    public static StartGameMessages setABlockTexture() {
        if(!inputs.containsKey("x") || !inputs.containsKey("y") || !inputs.containsKey("type")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim());
        int y = Integer.parseInt(inputs.get("y").trim());
        if(x > chosenMap.getLength() || x < 1 || y > chosenMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        if(!chosenMap.getBlock(x, y).isEmpty()) {
            return StartGameMessages.BLOCK_IS_NOT_EMPTY;
        }
        Textures texture;
        if((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        chosenMap.getBlock(x, y).setType(texture);
        return StartGameMessages.SUCCESS;
    }
}
