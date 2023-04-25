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
    public static UserMessages profileDisplay(){
        switch (inputs.get("field")){
            case "highscore": UserMessages.MESSAGES.setTxt(Integer.toString(currentUser.getHighScore()));
                break;
            case "rank": UserMessages.MESSAGES.setTxt(Integer.toString(currentUser.getRank()));
                break;
            case "slogan": UserMessages.MESSAGES.setTxt(currentUser.getSlogan()!=null?currentUser.getSlogan():"You dont have slogan!");
                break;
            case "":   UserMessages.MESSAGES.setTxt(currentUser.toString()); break;
        }
        return UserMessages.MESSAGES;
    }

}
