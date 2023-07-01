package controller.user_system;

import client.Client;
import com.google.gson.Gson;
import controller.ControllerUtils;
import model.DataBase;

import model.User;
import view.user_system.messages.UserMessages;

import java.io.IOException;

public class RegisterController extends ControllerUtils {

    public static UserMessages userCreate() throws IOException {
        UserMessages message;

        if(checkEmptyError(inputs)!=null){
            return checkEmptyError(inputs);
        }
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if (inputs.get("randomPassword")!=null) {
            String newPassword=generateNewPassword();
            inputs.put("password",newPassword);
            inputs.put("passwordConfirmation",newPassword);
            inputs.put("randomPassword",null);
            return UserMessages.RANDOM_PASSWORD;
        }

        if (!inputs.get("password").equals(inputs.get("passwordConfirmation"))) {
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
        if (User.doesUserExit(inputs.get("username"))) {
            newUsername = generateNewUsername(inputs.get("username"));
            inputs.put("username",newUsername);
            return UserMessages.USER_EXITS_BEFORE.setAndReturn(newUsername);
        }


        User user = new User(inputs.get("username"), inputs.get("password"), inputs.get("nickname"), inputs.get("email"), inputs.get("slogan"));

        User.addUser(user);
        DataBase.saveDataBase();
        Client.getClient().updateDataBase("addUser",new Gson().toJson(user));
        return UserMessages.SUCCESS;
    }


    public static String generateNewUsername(String previousUsername) {
        String newUsername="";
        int number = 0;
        do {
            newUsername = (previousUsername + number);
            number++;

        } while (User.doesUserExit(newUsername));
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



    public static UserMessages pickQuestion(String username) throws IOException {
        if (checkFormatErrors(inputs) != null) {
            return checkFormatErrors(inputs);
        }

        if(Integer.parseInt(inputs.get("questionNumber"))-1>DataBase.getDataBase().getRecoveryQuestions().size()){
            return UserMessages.INVALID_FORMAT.setAndReturn("Question number is invalid");
        }

        if (!inputs.get("answer").equals(inputs.get("answerConfirm"))) {
            return UserMessages.ANSWER_NOT_MATCH;
        }

        User.getUserByUsername(username).setPasswordRecoveryQuestion(DataBase.getDataBase().getRecoveryQuestions().get(Integer.parseInt(inputs.get("questionNumber"))-1));
        User.getUserByUsername(username).setPasswordRecoveryAnswer(inputs.get("answer"));
        DataBase.saveDataBase();
        return UserMessages.SUCCESS;
    }


    public static UserMessages getForgotPasswordQuestions() {
        int index=0;
        String output="";
        for(String question: DataBase.getRecoveryQuestions()){
            index++;
            output+=(index+". "+question+"\n");
        }
        return UserMessages.MESSAGES.setAndReturn(output);

    }
}
