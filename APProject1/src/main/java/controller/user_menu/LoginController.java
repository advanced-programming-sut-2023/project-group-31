package controller.user_menu;

import controller.ControllerUtils;
import model.DataBase;
import model.User;
import view.user_system.messages.UserMessages;

public class LoginController extends ControllerUtils {


    //username password
    public static UserMessages loginUser(){
        UserMessages message;
        if((message=checkEmptyError(inputs))!=null){
            return UserMessages.NOT_ENOUGH_MESSAGES;
        }

        if(!User.doesUserExit(inputs.get("username"))){
            UserMessages.USER_NOT_EXITS.setTxt("user "+ inputs.get("username")+" does not exits!");
            return UserMessages.USER_NOT_EXITS;
        }

        if(!User.getUserByUsername(inputs.get("username")).isPasswordCurrent(inputs.get("password"))){
            return UserMessages.PASSWORD_IS_NOT_CORRECT;
        }

        if(inputs.get("stayLoggedIn")!=null){
            DataBase.
        }

        currentUser=User.getUserByUsername(inputs.get("username"));


        return UserMessages.SUCCESS;
    }

    public static UserMessages forgotMyPassword(String username){
        return null;
    }

    public static UserMessages checkAnswerCorrectness(String username, String answer){
        return null;
    }

    public static UserMessages getForgotPasswordQuestion() {
        UserMessages.MenuMessage.setTxt(currentUser.getPasswordRecoveryQuestion());
        return UserMessages.MenuMessage;
    }

    public static UserMessages checkForgotQuestionAnswerCorrectness(String input) {
        if(input.equals(currentUser.getPasswordRecoveryAnswer())){
            return UserMessages.CORRECT_ANSWER;
        }else{
            return UserMessages.WRONG_ANSWER;
        }
    }

    public static UserMessages changePassword() {
        if(!inputs.get("password").equals(inputs.get("passwordConfirmation"))){
            return UserMessages.PASSWORD_NOT_MATCH;
        }
        currentUser.setPassword(inputs.get("password"));
        return UserMessages.SUCCESS;
    }

    public static boolean isUserLoggedIN(){
        return currentUser!=null;
    }

    public static void userLogout(){
        currentUser=null;
    }

}
