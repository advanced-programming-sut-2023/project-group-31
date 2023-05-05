package controller.user_menu;


import controller.ControllerUtils;
import model.User;
import view.user_system.messages.UserMessages;

public class ProfileController extends ControllerUtils {




    //change user fields

    public static UserMessages profileChangeUsername(){
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }
        if(User.doesUserExit(inputs.get("username"))){
            return UserMessages.USER_EXITS_BEFORE;
        }
        currentUser.setUsername(inputs.get("username"));
        return UserMessages.SUCCESS;
    }
    public static UserMessages profileChangeNickname() {
        if(inputs.get("nickname").equals(currentUser.getNickname())){
            return UserMessages.NO_CHANGE;
        }
        currentUser.setNickname(inputs.get("nickname"));
        return UserMessages.SUCCESS;
    }

    public static UserMessages profileChangeEmail(){
        if(inputs.get("email").equals(currentUser.getEmail())){
            return UserMessages.NO_CHANGE;
        }
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }
        if(User.doesEmailExits(inputs.get("email"))){
            return UserMessages.EMAIL_EXITS;
        }
        currentUser.setEmail(inputs.get("email"));
        return UserMessages.SUCCESS;
    }

    public static UserMessages profileChangePassword(){
        UserMessages message;

        if(!currentUser.isPasswordCurrent(inputs.get("oldPassword"))){
            return UserMessages.OLD_PASSWORD_IS_WRONG;
        }

        if ((message = checkPasswordWeakness(inputs.get("newPassword"))) != null) {
            return message;
        }

        if(inputs.get("oldPassword").equals(inputs.get("newPassword"))){
            return UserMessages.OLD_PASSWORD_MATCHES_NEW_PASSWORD;
        }

        return UserMessages.SUCCESS;
    }
    public static UserMessages profileChangeSlogan(){
        if(inputs.get("slogan").equals(currentUser.getSlogan())){
            return UserMessages.NO_CHANGE;
        }
        return UserMessages.SUCCESS;
    }





    //display user fields
    public static UserMessages profileDisplay(){
        if(inputs.get("field")==null){
            inputs.put("field","");
        }
        switch (inputs.get("field")){
            case "highscore": UserMessages.MESSAGE.setTxt(Integer.toString(currentUser.getHighScore()));
                break;
            case "rank": UserMessages.MESSAGE.setTxt(Integer.toString(currentUser.getRank()));
                break;
            case "slogan": UserMessages.MESSAGE.setTxt(currentUser.getSlogan()!=null?currentUser.getSlogan():"You dont have slogan!");
                break;
            case "":   UserMessages.MESSAGE.setTxt(currentUser.toString()); break;

            default: UserMessages.MESSAGE.setTxt("Invalid field");
        }
        return UserMessages.MESSAGE;
    }


}
