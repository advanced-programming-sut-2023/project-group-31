package controller;

import model.User;
import model.game_stuff.Game;
import model.game_stuff.Government;
import model.game_stuff.Map;
import view.user_system.messages.UserMessages;

import java.util.HashMap;

public abstract class ControllerUtils {

    protected static HashMap<String, String> inputs;
    protected static User currentUser = null;
    protected static Government currentPlayer;
    protected static Game currentGame;
    protected static Map currentMap;

    static {
        currentMap = Map.getDefaultMap();
        inputs=new HashMap<>();
    }

    public static Map getCurrentMap() {
        return currentMap;
    }

    public static Government getCurrentPlayer() {
        return currentPlayer;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentPlayer(Government currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public static void setCurrentUser(User currentUser) {
        ControllerUtils.currentUser = currentUser;
    }

    public static void setInputs(HashMap<String, String> inputs) {
        ControllerUtils.inputs = inputs;
    }

    public static HashMap<String, String> getInputs() {
        return inputs;
    }

    public static void putInput(String key, String value) {
        inputs.put(key, value);
    }

    public static UserMessages getCaptchaCode() {
        String captchaCode = "";
        int[] random = new int[4];
        for (int i = 0; i < 4; i++) {
            random[i] = (int) Math.floor(Math.random() * 10);
            captchaCode += random[i];
        }
        inputs.put("captchaCode", captchaCode);
        UserMessages.MESSAGE.setTxt(captchaCode);
        return UserMessages.MESSAGE;
    }

    public static UserMessages checkCaptchaMatching() {
        if (inputs.get("inputCaptcha").equals(inputs.get("captchaCode"))) {
            return UserMessages.SUCCESS;
        }
        return UserMessages.FAIL;
    }

    private static boolean isFormatCorrect(String input, InputFormats inputFormat) {
        if (inputFormat.getFormat() == null) {
            return true;
        }
        return input.matches(inputFormat.getFormat());
    }


    protected static UserMessages checkEmptyError(HashMap<String, String> inputs) {
        for (String groupName : inputs.keySet()) {
            if (inputs.get(groupName) == null && (InputFormats.getInputFormat(groupName).isCompulsory())) {
                UserMessages.NOT_ENOUGH_MESSAGES.setTxt(groupName + "can't be null!");
                return UserMessages.NOT_ENOUGH_MESSAGES;
            }
            if (inputs.get(groupName) != null && inputs.get(groupName).equals("")) {
                return UserMessages.NOT_ENOUGH_MESSAGES;
            }
        }
        return null;
    }

    protected static UserMessages checkFormatErrors(HashMap<String, String> inputs) {
        UserMessages result;
        if ((result = checkEmptyError(inputs)) != null) {
            return result;
        }
        for (String groupName : inputs.keySet()) {
            if (inputs.get(groupName) != null && !isFormatCorrect(inputs.get(groupName), InputFormats.getInputFormat(groupName))) {

                UserMessages.INVALID_FORMAT.setTxt("Inavlid" + groupName + "format!");
                return UserMessages.INVALID_FORMAT;
            }
        }
        return null;
    }

    protected static UserMessages checkPasswordWeakness(String password) {
        if (password.length() < 6) {
            return UserMessages.PASSWORD_IS_WEAK.setAndReturn("password is short!");
        }
        if (!password.matches(".*[a-z].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndReturn("password should have small letter!");
        }
        if (!password.matches(".*[A-Z].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndReturn("password should have capital letter!");
        }
        if (!password.matches(".*[\\d].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndReturn("password should have at least one digit!");
        }
        if (!password.matches(".*([^\\da-zA-Z]).*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndReturn("password should have one not letter nad numeric character!");
        }
        return null;
    }


}