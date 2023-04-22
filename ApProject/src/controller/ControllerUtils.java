package controller;

import model.User;
import model.game_stuff.Game;
import model.game_stuff.Player;
import view.user_system.messages.UserMessages;

import java.util.HashMap;

public abstract class ControllerUtils {

    protected static HashMap<String,String> inputs;
    public static User currentUser;
    public static Game currentGame;
    public static Player currentPlayer;
    public static UserMessages captcha(){
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ControllerUtils.currentUser = currentUser;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        ControllerUtils.currentGame = currentGame;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        ControllerUtils.currentPlayer = currentPlayer;
    }

    public static void setInputs(HashMap<String, String> inputs) {
        inputs = inputs;
    }
}
