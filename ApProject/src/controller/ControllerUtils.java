package controller;

import model.User;
import view.user_system.messages.Messages;

public abstract class ControllerUtils {
    public static User currentUser;
    public static Messages captcha(){
        return null;
    }
}
