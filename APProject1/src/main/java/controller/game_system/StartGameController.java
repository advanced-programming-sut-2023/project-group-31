package controller.game_system;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.*;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.buildings.Storage;
import model.game_stuff.buildings.enums.BuildingMenus;
import model.game_stuff.buildings.enums.StorageTypes;
import model.game_stuff.enums.Items;
import model.game_stuff.enums.Textures;
import model.game_stuff.enums.TreeTypes;
import model.game_stuff.people.Lord;
import view.game_system.messages.StartGameMessages;

import java.util.ArrayList;
import java.util.HashSet;

public class StartGameController extends ControllerUtils {
    private static final HashSet<Colors> usedColors;
    private static final HashSet<Integer> usedLordHouses;
    private static ArrayList<PrimitivePlayer> primitivePlayers;
    private static boolean mapIsChanged;

    private static Map baseMap;

    static {
        primitivePlayers = new ArrayList<>();
        usedColors = new HashSet<>();
        usedLordHouses = new HashSet<>();
        mapIsChanged = false;
        baseMap = Map.getDefaultMap();
        //TODO: dastoor hayi ke map ro taghyir midan bayad mapIsChanged ro true konan
    }

    public static String showMaps() {
        StringBuilder output = new StringBuilder("CHOSEN MAP:\n" + currentMap + "\nAVAILABLE MAPS:");
        for (Map map : Map.getMaps()) {
            output.append("\n").append(map);
        }
        return output.toString();
    }

    public static void setPrimitivePlayers(ArrayList<PrimitivePlayer> primitivePlayers) {
        StartGameController.primitivePlayers = primitivePlayers;
    }

    public static StartGameMessages dropTree(int x, int y, String tree) {
        if (x >= currentMap.getSize() || y >= currentMap.getSize()) {
            return StartGameMessages.OUT_OF_MAP;
        }
        TreeTypes treeType;
        if ((treeType = TreeTypes.getTreeType(tree)) == null) {
            return StartGameMessages.INVALID_COMMAND;
        }
        currentMap.getBlocks().get(x).get(y).addTree(new Tree(currentMap.getBlocks().get(x).get(y), treeType));
        return StartGameMessages.SUCCESS;
    }

    public static String showAllPlayers() {
        StringBuilder output = new StringBuilder("PLAYERS:");
        for (User user : User.getUsers().values()) {
            output.append("\n").append(user).append("\n-------------------");
        }
        return output.toString();
    }

    public static String showPlayersInTheGame() {
        if (primitivePlayers.isEmpty()) {
            return "nothing to show!";
        }
        StringBuilder output = new StringBuilder("PLAYERS:");
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            output.append("\n").append(primitivePlayer.getUser().getUsername()).append(" ").append(primitivePlayer.getColor()).append(" in lord house ").append(primitivePlayer.getLordHouseNumber() + 1);
        }
        return output.toString();
    }

    public static StartGameMessages chooseMap(String name) {
        for (Map map : Map.getMaps()) {
            if (map.getName().equals(name)) {
                currentMap = map.clone();
                baseMap = map;
                return StartGameMessages.SUCCESS;
            }
        }
        return StartGameMessages.MAP_NOT_FOUND;
    }

    //TODO: DO SOMETHING WITH THE DUPLICATIONS

    public static StartGameMessages setABlockTexture() {
        if (!inputs.containsKey("x") || !inputs.containsKey("y") || !inputs.containsKey("type")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim()), y = Integer.parseInt(inputs.get("y").trim());

        if (x > currentMap.getLength() || x < 1 || y > currentMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        if (!currentMap.getBlock(x, y).isEmpty()) {
            StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + x + "," + y + ")");
            return StartGameMessages.BLOCK_IS_NOT_EMPTY;
        }
        Textures texture;
        if ((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        currentMap.getBlock(x, y).setType(texture);
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages setARectanglesTexture() {
        if (!inputs.containsKey("x1") || !inputs.containsKey("y1") || !inputs.containsKey("x2") || !inputs.containsKey("y2") || !inputs.containsKey("type")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x1 = Integer.parseInt(inputs.get("x1").trim());
        int y1 = Integer.parseInt(inputs.get("y1").trim());
        int x2 = Integer.parseInt(inputs.get("x2").trim());
        int y2 = Integer.parseInt(inputs.get("y2").trim());
        if (x1 < 1 || x1 > x2 || x2 > currentMap.getLength() || y1 < 1 || y1 > y2 || y2 > currentMap.getLength()) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        Textures texture;
        if ((texture = Textures.getTextureByName(inputs.get("type").trim().replaceAll("\"", ""))) == null) {
            return StartGameMessages.INVALID_TEXTURE_TYPE;
        }
        for (int i = x1 - 1; i < x2; i++) {
            for (int j = y1 - 1; j < y2; j++) {
                if (!currentMap.getBlock(i, j).isEmpty()) {
                    StartGameMessages.BLOCK_IS_NOT_EMPTY.setInput("(" + (i + 1) + "," + (j + 1) + ")");
                    return StartGameMessages.BLOCK_IS_NOT_EMPTY;
                }
            }
        }
        for (int i = x1 - 1; i < x2; i++) {
            for (int j = y1 - 1; j < y2; j++) {
                currentMap.getBlock(i, j).setType(texture);
            }
        }
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages showDetails() {
        if (!inputs.containsKey("x") || !inputs.containsKey("y")) {
            return StartGameMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim());
        int y = Integer.parseInt(inputs.get("y").trim());
        if (x > currentMap.getLength() || x < 1 || y > currentMap.getWidth() || y < 1) {
            return StartGameMessages.COORDINATE_OUT_OF_BOUND;
        }
        StartGameMessages.SUCCESS.setTxt(currentMap.getBlock(x, y).toString());
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages addPlayer(String username) {
        User user;
        if ((user = User.getUserByUsername(username)) == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            if (primitivePlayer.getUser().equals(user)) return StartGameMessages.USER_IS_ALREADY_ADDED;
        }
        if (primitivePlayers.size() >= 8) {
            return StartGameMessages.MAXIMUM_NUMBER_OF_USERS;
        }
        Colors color = null;
        for (Colors color1 : Colors.values()) {
            if (!usedColors.contains(color1)) {
                color = color1;
                usedColors.add(color);
                break;
            }
        }
        int lordHouse = -1;
        for (int i = 0; i < 8; i++) {
            if (!usedLordHouses.contains(i)) {
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
            if (primitivePlayer.getUser().getUsername().equals(username)) return primitivePlayer;
        }
        return null;
    }

    public static StartGameMessages removePlayer(String username) {
        PrimitivePlayer playerToRemove = getPlayerByUsername(username);
        if (playerToRemove == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        Colors color = playerToRemove.getColor();
        usedLordHouses.remove(playerToRemove.getLordHouseNumber());
        primitivePlayers.remove(playerToRemove);
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            if (primitivePlayer.getColor().equals(color)) return StartGameMessages.SUCCESS;
        }
        usedColors.remove(color);
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages setPlayersLordHouse(String username, int lordHouseNumber) {
        PrimitivePlayer primitivePlayer = getPlayerByUsername(username);
        if (primitivePlayer == null) {
            return StartGameMessages.NO_SUCH_USER;
        }
        lordHouseNumber--;
        if (lordHouseNumber < 0 || lordHouseNumber >= 8) {
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
        if (color == null) {
            return StartGameMessages.INVALID_COLOR;
        }
        if (color.equals(primitivePlayer.getColor())) {
            return StartGameMessages.COLOR_IS_ALREADY_SET;
        }
        primitivePlayer.setColor(color);
        return StartGameMessages.SUCCESS;
    }

    public static String showLordHousesLeft() {
        StringBuilder output = new StringBuilder("lord house left:");
        for (int i = 0; i < 8; i++) {
            if (!usedLordHouses.contains(i)) {
                output.append("\t").append(i + 1);
            }
        }
        return output.toString();
    }

    public static StartGameMessages start() {
        if (primitivePlayers.size() < 2) {
            return StartGameMessages.TOO_FEW_PLAYERS;
        }
        currentGame = new Game(currentMap);
        Government player;
        Block block;
        ArrayList<Government> players = new ArrayList<>();
        for (PrimitivePlayer primitivePlayer : primitivePlayers) {
            player = new Government(primitivePlayer.getUser(), primitivePlayer.getColor());
            players.add(player);
            player.setGame(currentGame);
            block = currentMap.getLordHouses().get(primitivePlayer.getLordHouseNumber());
            MenuBuilding lordHouse = new MenuBuilding(player, BuildingMenus.LORD_HOUSE);
            Lord lord = new Lord(player);
            lord.setPosition(block);
            block.addPerson(lord);
            player.setLord(lord);
            setBlockForBuilding(block, lordHouse, 2, 2);
            Storage storage = new Storage(player, StorageTypes.STOCKPILE);
            setBlockForBuilding(currentMap.getBlock(block.getX() + 2, block.getY()), storage, 2, 2);
            player.addStockpile(storage);
            storage.addProduct(Items.WOOD, 100);
            storage.addProduct(Items.STONE, 100);
            storage = new Storage(player, StorageTypes.Granary);
            setBlockForBuilding(currentMap.getBlock(block.getX(), block.getY() + 2), storage, 2, 2);
            player.addGranary(storage);
            storage.addProduct(Items.BREAD, 100);
        }
        currentGame.setPlayers(players);
        currentPlayer = players.get(0);
        return StartGameMessages.SUCCESS;
    }

    private static void setBlockForBuilding(Block block, Building building, int length, int width) {
        int x = block.getX();
        int y = block.getY();
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
                currentGame.getMap().getBlock(i, j).setBuilding(building);
                building.addBlock(currentGame.getMap().getBlock(i, j));
            }
        }
    }

    public static StartGameMessages saveMap() {
        if (!Map.getMaps().contains(currentMap)) {
            Map.addMap(currentMap);
            currentMap.setSaved(true);
            return StartGameMessages.SUCCESS;
        } else if (!mapIsChanged) {
            return StartGameMessages.THE_MAP_IS_ALREADY_SAVED;
        }
        return StartGameMessages.SUCCESS;
    }

    public static StartGameMessages clearBlock(int x, int y) {
        if (!currentMap.isInMap(x, y)) {
            return StartGameMessages.OUT_OF_MAP;
        }
        currentMap.setBlock(x, y, baseMap.getBlock(x, y).clone());
        return StartGameMessages.SUCCESS;

    }


}
