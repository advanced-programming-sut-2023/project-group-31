package controller.game_menu;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.Colors;
import model.game_stuff.Map;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.UserMessages;

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
}
