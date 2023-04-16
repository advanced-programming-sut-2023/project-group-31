package controller;

import model.User;
import view.user_system.messages.UserMessages;

import java.util.HashMap;

public abstract class ControllerUtils {

    protected static HashMap<String,String> inputs;
    public static User currentUser;
    public static UserMessages captcha(){
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ControllerUtils.currentUser = currentUser;
    }



    public static void setInputs(HashMap<String, String> inputs) {
        inputs = inputs;
    }
}
