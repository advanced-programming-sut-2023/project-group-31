package controller.user_system;

import client.Client;
import controller.ControllerUtils;
import model.DataBase;
import model.Session;
import model.User;
import view.user_system.messages.UserMessages;

import java.io.IOException;

public class LoginController extends ControllerUtils {


    //username password
    public static UserMessages loginUser() throws IOException {
        UserMessages message;
        if ((message = checkEmptyError(inputs)) != null) {
            return UserMessages.NOT_ENOUGH_MESSAGES;
        }

        if (!User.doesUserExit(inputs.get("username"))) {
            UserMessages.USER_NOT_EXITS.setTxt("user " + inputs.get("username") + " does not exits!");
            return UserMessages.USER_NOT_EXITS;
        }

        if (!User.getUserByUsername(inputs.get("username")).isPasswordCurrent(inputs.get("password"))) {
            return UserMessages.PASSWORD_IS_NOT_CORRECT;
        }

        if (inputs.get("stayLoggedIn") != null) {
            Session.getSession().setLoggedInUserUsername(inputs.get("username"));
            Session.getSession().setLoggedInUserPassword(User.getUserByUsername(inputs.get("username")).getPassword());
        }


        setCurrentUser(User.getUserByUsername(inputs.get("username")));

        Client.getClient().loginInServer(inputs.get("username"));
        return UserMessages.SUCCESS;
    }

    public static UserMessages forgotMyPassword(String username) {
        return null;
    }

    public static UserMessages checkAnswerCorrectness(String username, String answer) {
        return null;
    }

    public static UserMessages getForgotPasswordQuestion() {
        UserMessages.MESSAGE.setTxt(User.getUserByUsername(inputs.get("username")).getPasswordRecoveryQuestion());
        return UserMessages.MESSAGE;
    }

    public static UserMessages checkForgotQuestionAnswerCorrectness(String input) {
        if (input.equals(User.getUserByUsername(inputs.get("username")).getPasswordRecoveryAnswer())) {
            return UserMessages.CORRECT_ANSWER;
        } else {
            return UserMessages.WRONG_ANSWER;
        }
    }

    public static UserMessages changePassword() throws IOException {
        if (!inputs.get("password").equals(inputs.get("passwordConfirmation"))) {
            return UserMessages.PASSWORD_NOT_MATCH;
        }
        if(inputs.get("password").equals(User.getUserByUsername(inputs.get("username")).getPassword())){
            return UserMessages.OLD_PASSWORD_MATCHES_NEW_PASSWORD;
        }
        if (checkPasswordWeakness(inputs.get("password")) != null) {
            return UserMessages.PASSWORD_IS_WEAK;
        }
        User.getUserByUsername(inputs.get("username")).setPassword(inputs.get("password"));
        currentUser = User.getUserByUsername(inputs.get("username"));
        setCurrentUser(User.getUserByUsername(inputs.get("username")));
        Client.getClient().loginInServer(inputs.get("username"));
        return UserMessages.SUCCESS;
    }

    public static boolean isUserLoggedIN() {
        return currentUser != null;
    }

    public static void userLogout() {
        currentUser = null;
        Session.getSession().setLoggedInUserUsername(null);
        Session.getSession().setLoggedInUserPassword(null);
        DataBase.saveDataBase();
    }

}
