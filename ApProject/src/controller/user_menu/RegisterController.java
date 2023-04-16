package controller.user_menu;

import controller.ControllerUtils;
import model.User;
import view.ViewUtils;
import view.user_system.messages.UserMessages;

public class RegisterController extends ControllerUtils {

    public static UserMessages userCreate() {

        if (ViewUtils.checkFormatErrors(inputs) != null) {
            return UserMessages.getMessage(ViewUtils.checkFormatErrors(inputs));
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

    public static UserMessages getForgotPasswordQuestion(){

        return null;
    }

}
