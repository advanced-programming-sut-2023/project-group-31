package controller.user_menu;


import controller.ControllerUtils;
import model.User;
import view.user_system.messages.Messages;

import java.util.HashMap;

public class ProfileController extends ControllerUtils {



    private static User currentUser;
    //change user fields
    public static Messages profileChangeUsername(String newUsername){
        return null;
    }
    public static Messages profileChangeNickname(String newNickname){
        return null;
    }
    public static Messages profileChangePassword(String oldPassword,String newPassword){
        return null;
    }
    public static Messages profileChangeSlogan(String newSlogan){
        return null;
    }

    //display user fields

    public static Messages profileDisplayHighScore(){
        return null;
    }
    public static Messages profileDisplayRank(){
        return null;
    }
    public static Messages profileDisplaySlogan(){
        return null;
    }
    public static Messages profileDisplay(){
        return null;
    }

}
