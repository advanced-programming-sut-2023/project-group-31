package controller.game_system;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.*;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.buildings.enums.BuildingMenus;
import model.game_stuff.enums.Textures;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.UserMessages;

import java.util.ArrayList;
import java.util.HashSet;

public class StartGameController extends ControllerUtils {
    private static ArrayList<PrimitivePlayer> primitivePlayers;
    private static HashSet<Colors> usedColors;
    private static HashSet<Integer> usedLordHouses;
    private static int xToShow;
    private static int yToShow;

    static {
        primitivePlayers = new ArrayList<>();
        usedColors = new HashSet<>();
        usedLordHouses = new HashSet<>();
    }
    public static String showMaps() {
        String output = "CHOSEN MAP:\n" + currentMap + "\nAVAILABLE MAPS:";
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
            output += "\n" + user + "\n-------------------";
        }
        return output;
    }
    public static String showPlayersInTheGame() {
        if(primitivePlayers.isEmpty()) {
            return "nothing to show!";
        }
        String output = "PLAYERS:";
        for (PrimitivePlayer  primitivePlayer: primitivePlayers) {
            output += "\n" + primitivePlayer.getUser().getUsername() + " " + primitivePlayer.getColor() + " in lord house " + (primitivePlayer.getLordHouseNumber() + 1);
        }
        return output;
    }

    public static StartGameMessages chooseMap(String name) {
        for (Map map : Map.getMaps()) {
            if(map.getName().equals(name)) {
                currentMap = map;
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
        int x = Integer.parseInt(inputs.get("x").trim()),y = Integer.parseInt(inputs.get("y").trim());

        if(x > currentMap.getLength() || x < 1 || y > currentMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        if(!currentMap.getBlock(x, y).isEmpty()) {
            StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + x + "," + y + ")");
            return StartGameMessages.BLOCK_IS_NOT_EMPTY;
        }
        Textures texture;
        if((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        currentMap.getBlock(x, y).setType(texture);
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
        if(x1 < 1 || x1 > x2 || x2 > currentMap.getLength() ||
                y1 < 1 || y1 > y2 || y2 > currentMap.getLength()) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        Textures texture;
        if((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        for(int i = x1 - 1; i < x2; i++) {
            for(int j = y1 - 1; j < y2; j++) {
                if(!currentMap.getBlock(i, j).isEmpty()) {
                    StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + (i+1) + "," + (j+1) + ")");
                    return StartGameMessages.BLOCK_IS_NOT_EMPTY;
                }
            }
        }
        for(int i = x1 - 1; i < x2; i++) {
            for(int j = y1 - 1; j < y2; j++) {
               currentMap.getBlock(i, j).setType(texture);
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
        if(x > currentMap.getLength() || x < 1 || y > currentMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        StartGameMessages.SUCCESS.setTxt(currentMap.getBlock(x, y).toString());
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages addPlayer(String username) {
        User user;
        if((user = User.getUserByUsername(username)) == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            if(primitivePlayer.getUser().equals(user))
                return StartGameMessages.USER_IS_ALREADY_ADDED;
        }
        if(primitivePlayers.size() >= 8) {
            return StartGameMessages.MAXIMUM_NUMBER_OF_USERS;
        }
        Colors color = null;
        for (Colors color1 : Colors.values()) {
            if(!usedColors.contains(color1)) {
                color = color1;
                usedColors.add(color);
                break;
            }
        }
        int lordHouse = -1;
        for(int i = 0; i < 8; i++) {
            if(!usedLordHouses.contains(i)) {
                lordHouse = i;
                usedLordHouses.add(i);
                break;
            }
        }
        primitivePlayers.add(new PrimitivePlayer(user, color, lordHouse));
        return StartGameMessages.SUCCESS;
    }
    private static PrimitivePlayer getPlayerByUsername(String username) {
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            if(primitivePlayer.getUser().getUsername().equals(username))
                return primitivePlayer;
        }
        return null;
    }
    public static StartGameMessages removePlayer(String username) {
        PrimitivePlayer playerToRemove = getPlayerByUsername(username);
        if(playerToRemove == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        Colors color = playerToRemove.getColor();
        usedLordHouses.remove(playerToRemove.getLordHouseNumber());
        primitivePlayers.remove(playerToRemove);
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            if(primitivePlayer.getColor().equals(color))
                return StartGameMessages.SUCCESS;
        }
        usedColors.remove(color);
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages setPlayersLordHouse(String username, int lordHouseNumber) {
        PrimitivePlayer primitivePlayer = getPlayerByUsername(username);
        if (primitivePlayer == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        lordHouseNumber --;
        if(lordHouseNumber < 0 || lordHouseNumber >= 8) {
            return StartGameMessages.INVALID_LORD_HOUSE_NUMBER;
        }
        if (usedLordHouses.contains(lordHouseNumber)) {
            return StartGameMessages.LORD_HOUSE_IS_SELECTED_BEFORE;
        }
        primitivePlayer.setLordHouseNumber(lordHouseNumber);
        usedLordHouses.add(lordHouseNumber);
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages setPlayersTeam(String username, String colorString) {
        PrimitivePlayer primitivePlayer = getPlayerByUsername(username);
        if (primitivePlayer == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        Colors color = Colors.getColorByName(colorString);
        if(color == null) {
            return StartGameMessages.INVALID_COLOR;
        }
        if(color.equals(primitivePlayer.getColor())) {
            return StartGameMessages.COLOR_IS_ALREADY_SET;
        }
        primitivePlayer.setColor(color);
        return StartGameMessages.SUCCESS;
    }

    public static String showLordHousesLeft() {
        String output = "lord house left:";
        for(int i = 0; i < 8; i++) {
            if(!usedLordHouses.contains(i)) {
                output += "\t" + (i + 1);
            }
        }
        return output;
    }

    public static StartGameMessages start() {
        if(primitivePlayers.size() < 2) {
            return StartGameMessages.TOO_FEW_PLAYERS;
        }
        currentGame = new Game(currentMap);
        Government player;
        Block block;
        ArrayList<Government> players = new ArrayList<>();
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            player = new Government(primitivePlayer.getUser(),primitivePlayer.getColor());
            players.add(player);
            block = currentMap.getLordHouses().get(primitivePlayer.getLordHouseNumber());
            MenuBuilding lordHouse = new MenuBuilding(player, BuildingMenus.LORD_HOUSE);
            for (int i = block.getX(); i < 2; i++) {
                for (int j = block.getY(); j < 2; j++) {
                    currentGame.getMap().getBlock(i, j).setBuilding(lordHouse);
                    lordHouse.addBlock(currentGame.getMap().getBlock(i, j));
                }
            }
        }
        currentGame.setPlayers(players);
        currentPlayer = players.get(0);
        return StartGameMessages.SUCCESS;
    }

    public static void saveMap() {
        if(!Map.getMaps().contains(currentMap)){
            Map.addMap(currentMap);
            currentMap.setSaved(true);
        }

    }
}
