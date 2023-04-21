package controller.user_menu;

import controller.ControllerUtils;
import model.User;
import view.user_system.messages.UserMessages;

public class LoginController extends ControllerUtils {


    //username password
    public static UserMessages loginUser(){
        if(!User.doesUserExit(inputs.get("username"))){
            UserMessages.USER_NOT_EXITS.setTxt("user "+ inputs.get("username")+" does not exits!");
            return UserMessages.USER_NOT_EXITS;
        }

        if(!User.getUserByUsername(inputs.get("username")).isPasswordCurrent(inputs.get("password"))){
            return UserMessages.PASSWORD_IS_NOT_CORRECT;
        }

        if(inputs.get("stayLoggedIn")!=null){
            ;
        }

        return null;
    }

    public static UserMessages forgotMyPassword(String username){
        return null;
    }

    public static UserMessages checkAnswerCorrectness(String username, String answer){
        return null;
    }

}
