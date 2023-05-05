package controller.game_system;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.Colors;
import model.game_stuff.Map;
import model.game_stuff.enums.Textures;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.UserMessages;

import java.util.HashMap;

public class StartGameController extends ControllerUtils {

    private static Map chosenMap;
    private static HashMap<User, Colors> usersToPlay;
    private static int xToShow;
    private static int yToShow;
    public static String showMaps() {
        String output = "CHOSEN MAP:\n" + chosenMap + "\nAVAILABLE MAPS:";
        for (Map map : Map.getMaps()) {
            output += "\n" + map;
        }
        return output;
    }

    public static Map getChosenMap() {
        return chosenMap;
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

    //TODO: DO SOMETHING WITH THE DUPLICATIONS

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
            StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + x + "," + y + ")");
            return StartGameMessages.BLOCK_IS_NOT_EMPTY;
        }
        Textures texture;
        if((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        chosenMap.getBlock(x, y).setType(texture);
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages setARectanglesTexture() {
        if(!inputs.containsKey("x1") || !inputs.containsKey("y1") ||
                !inputs.containsKey("x2") || !inputs.containsKey("y2") || !inputs.containsKey("type")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x1 = Integer.parseInt(inputs.get("x1").trim());
        int y1 = Integer.parseInt(inputs.get("y1").trim());
        int x2 = Integer.parseInt(inputs.get("x2").trim());
        int y2 = Integer.parseInt(inputs.get("y2").trim());
        if(x1 < 1 || x1 > x2 || x2 > chosenMap.getLength() ||
                y1 < 1 || y1 > y2 || y2 > chosenMap.getLength()) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        Textures texture;
        if((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        for(int i = x1 - 1; i < x2; i++) {
            for(int j = y1 - 1; j < y2; j++) {
                if(!chosenMap.getBlock(i, j).isEmpty()) {
                    StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + (i+1) + "," + (j+1) + ")");
                    return StartGameMessages.BLOCK_IS_NOT_EMPTY;
                }
            }
        }
        for(int i = x1 - 1; i < x2; i++) {
            for(int j = y1 - 1; j < y2; j++) {
               chosenMap.getBlock(i, j).setType(texture);
            }
        }
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages showDetails() {
        if(!inputs.containsKey("x") || !inputs.containsKey("y")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim());
        int y = Integer.parseInt(inputs.get("y").trim());
        if(x > chosenMap.getLength() || x < 1 || y > chosenMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        StartGameMessages.SUCCESS.setTxt(chosenMap.getBlock(x, y).toString());
        return StartGameMessages.SUCCESS;
    }
}
