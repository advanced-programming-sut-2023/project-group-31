package controller.user_menu;


import controller.ControllerUtils;
import model.User;
import view.user_system.messages.UserMessages;

public class ProfileController extends ControllerUtils {



    private static User currentUser;
    //change user fields
    public static UserMessages profileChangeUsername(String newUsername){
        return null;
    }
    public static UserMessages profileChangeNickname(String newNickname){
        return null;
    }
    public static UserMessages profileChangePassword(String oldPassword, String newPassword){
        return null;
    }
    public static UserMessages profileChangeSlogan(String newSlogan){
        return null;
    }

    //display user fields

    public static UserMessages profileDisplayHighScore(){
        return null;
    }
    public static UserMessages profileDisplayRank(){
        return null;
    }
    public static UserMessages profileDisplaySlogan(){
        return null;
    }
    public static UserMessages profileDisplay(){
        return null;
    }

}
