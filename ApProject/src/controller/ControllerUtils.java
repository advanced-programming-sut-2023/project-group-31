package controller;

import model.User;
import view.user_system.commands.InputFormats;
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

    public static HashMap<String, String> getInputs(){
        return inputs;
    }

    private static boolean isFormatCurrent(String input, InputFormats inputFormat) {
        if (inputFormat.getFormat() == null) {
            return true;
        }
        return input.matches(inputFormat.getFormat());
    }


    protected static UserMessages checkEmptyError(HashMap<String,String> inputs){
        for (String groupName : inputs.keySet()) {
            if (inputs.get(groupName) == null && (InputFormats.getInputFormat(groupName).isCompulsory())) {
                UserMessages.NOT_ENOUGH_MESSAGES.setTxt(groupName+"can't be null!");
                return UserMessages.NOT_ENOUGH_MESSAGES;
            }if(inputs.get(groupName).equals("")){
                return UserMessages.NOT_ENOUGH_MESSAGES;
            }
        }
        return null;
    }

    protected static UserMessages checkFormatErrors(HashMap<String, String> inputs) {
        UserMessages result;
        if((result=checkEmptyError(inputs))!=null){
            return result;
        }
        for (String groupName : inputs.keySet()) {
            if (inputs.get(groupName) != null && !isFormatCurrent(inputs.get(groupName), InputFormats.getInputFormat(groupName))) {
                UserMessages.INVALID_FORMAT.setTxt("Inavlid"+groupName+"format!");
                return UserMessages.INVALID_FORMAT;
            }
        }
        return null;
    }

}
