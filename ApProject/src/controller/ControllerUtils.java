package controller;

import model.User;
import view.user_system.messages.Messages;

import java.util.HashMap;

public abstract class ControllerUtils {

    protected HashMap<String,String> inputs;
    public static User currentUser;
    public static Messages captcha(){
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ControllerUtils.currentUser = currentUser;
    }
}
