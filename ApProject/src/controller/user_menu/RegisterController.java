package controller.user_menu;

import com.sun.net.httpserver.Authenticator;
import controller.ControllerUtils;
import model.Resource;
import model.User;
import view.ViewUtils;
import view.user_system.messages.UserMessages;

public class RegisterController extends ControllerUtils {

    public static UserMessages userCreate() {

        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if(inputs.get("password").equals("random")){
            return UserMessages.CONFIRM;
        }

        if(inputs.get("password").equals(inputs.get("password confirmation"))){
            return UserMessages.getMessage("Password not match!");
        }
        if(!inputs.containsKey("slogan")){
            inputs.put("slogan",null);
        }


        User user=new User(inputs.get("username"),inputs.get("password"),inputs.get("nickname"),inputs.get("email"),inputs.get("slogan"));

        User.addUser(user);

        return UserMessages.SUCCESS;
    }

    public static UserMessages pickQuestion() {
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if(inputs.get("answer").equals(inputs.get("answerConfirm"))){
            return UserMessages.ANSWER_NOT_MATCH;
        }

        currentUser.setPasswordRecoveryQuestion(Resource.getSlogans().get(Integer.parseInt(inputs.get("questionNumber"))));
        currentUser.setPasswordRecoveryAnswer(inputs.get("answer"));

        return UserMessages.SUCCESS;
    }

    public static UserMessages confirmRandomPassword(){
        return null;
    }
}
