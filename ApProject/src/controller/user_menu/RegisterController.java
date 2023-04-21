package controller.user_menu;

import controller.ControllerUtils;
import model.Resource;
import model.User;
import view.user_system.messages.UserMessages;

public class RegisterController extends ControllerUtils {

    public static UserMessages userCreate() {
        UserMessages message;
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if (inputs.get("password").equals("random")) {
            return UserMessages.RANDOM_PASSWORD;
        }

        if (inputs.get("password").equals(inputs.get("password confirmation"))) {
            return UserMessages.getMessage("Password not match!");
        }
        if (!inputs.containsKey("slogan")) {
            inputs.put("slogan", null);
        }

        if ((message = checkPasswordWeakness(inputs.get("password"))) != null) {
            return message;
        }
        String newUsername;
        if (User.DoesUserExit(inputs.get("username"))) {
            newUsername = createNewUsername(inputs.get("username"));
            inputs.put("username",newUsername);
            return UserMessages.USER_EXITS_BEFORE.setAndPrintMessage(newUsername);
        }


        User user = new User(inputs.get("username"), inputs.get("password"), inputs.get("nickname"), inputs.get("email"), inputs.get("slogan"));

        User.addUser(user);

        return UserMessages.SUCCESS;
    }


    public static String createNewUsername(String previousUsername) {
        String newUsername;
        int number = 0;
        do {
            newUsername = (previousUsername + number);
            number++;

        } while (User.DoesUserExit(newUsername));
        return newUsername;
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

        currentUser.setPasswordRecoveryQuestion(Resource.getSlogans().get(Integer.parseInt(inputs.get("questionNumber"))));
        currentUser.setPasswordRecoveryAnswer(inputs.get("answer"));

        return UserMessages.SUCCESS;
    }

    public static UserMessages confirmRandomPassword() {
        return null;
    }
}
