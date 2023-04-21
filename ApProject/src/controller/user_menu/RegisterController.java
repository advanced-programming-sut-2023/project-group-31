package controller.user_menu;

import controller.ControllerUtils;
import model.DataBase;

import model.User;
import view.user_system.messages.UserMessages;

import java.util.regex.Matcher;

public class RegisterController extends ControllerUtils {

    public static UserMessages userCreate() {
        UserMessages message;
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if (inputs.get("password").equals("random")) {
            inputs.put("password",generateNewPassword());
            return UserMessages.RANDOM_PASSWORD;
        }

        if (inputs.get("password").equals(inputs.get("password confirmation"))) {
            return UserMessages.getMessage("Password not match!");
        }

        if(User.doesEmailExits(inputs.get("email"))){
            return UserMessages.EMAIL_EXITS;
        }



        if (!inputs.containsKey("slogan")) {
            inputs.put("slogan", null);
        }

        if ((message = checkPasswordWeakness(inputs.get("password"))) != null) {
            return message;
        }
        String newUsername;
        if (User.DoesUserExit(inputs.get("username"))) {
            newUsername = generateNewUsername(inputs.get("username"));
            inputs.put("username",newUsername);
            return UserMessages.USER_EXITS_BEFORE.setAndPrintMessage(newUsername);
        }


        User user = new User(inputs.get("username"), inputs.get("password"), inputs.get("nickname"), inputs.get("email"), inputs.get("slogan"));

        User.addUser(user);

        return UserMessages.SUCCESS;
    }


    public static String generateNewUsername(String previousUsername) {
        String newUsername;
        int number = 0;
        do {
            newUsername = (previousUsername + number);
            number++;

        } while (User.DoesUserExit(newUsername));
        return newUsername;
    }

    public static String generateNewPassword() {
        String newPassword;

        String alphaNumeric = "0123456789003433" + "abcdefghijklmnopqrstuvxyz" + "ABCDEFGHIJKLMNOPQRSTUVXYZ" +"**&&##@@!$^^*^^%%)_&*^";
        int length=alphaNumeric.length(),passwordLength= (int)Math.floor(Math.random()*10)+6;
        do {
            newPassword="";
            for(int i=0;i<passwordLength;i++){
                newPassword+=alphaNumeric.charAt((int)Math.floor(Math.random()*length));
            }

        } while (checkPasswordWeakness(newPassword)!=null);

        return newPassword;
    }

    private static UserMessages checkPasswordWeakness(String password) {
        if (password.length() < 6) {
            return UserMessages.PASSWORD_IS_WEAK.setAndPrintMessage("password is short!");
        }
        if (!password.matches(".*[a-z].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndPrintMessage("password should have small letter!");
        }
        if (!password.matches(".*[A-Z].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndPrintMessage("password should have capital letter!");
        }
        if (!password.matches(".*[\\d].*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndPrintMessage("password should have at least one digit!");
        }
        if (!password.matches(".*^([\\d]|[a-zA-Z]).*")) {
            return UserMessages.PASSWORD_IS_WEAK.setAndPrintMessage("password should have one not letter nad numeric character!");
        }
        return null;
    }

    public static UserMessages pickQuestion() {
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if (inputs.get("answer").equals(inputs.get("answerConfirm"))) {
            return UserMessages.ANSWER_NOT_MATCH;
        }

        currentUser.setPasswordRecoveryQuestion(DataBase.getSlogans().get(Integer.parseInt(inputs.get("questionNumber"))));
        currentUser.setPasswordRecoveryAnswer(inputs.get("answer"));

        return UserMessages.SUCCESS;
    }


}
