package view.user_system.menu_controller;
import view.user_system.messages.UserMessages;
import controller.ControllerUtils;
import controller.user_system.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.ViewUtils;

public class LoginMenuController{
    private UserMessages result;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void submit(MouseEvent mouseEvent) {
        ControllerUtils.putInput("username",username.getText());
        ControllerUtils.putInput("password",password.getText());
        result = LoginController.loginUser();
        if (result.equals(UserMessages.SUCCESS)) {
            System.out.println("user login: " + UserMessages.SUCCESS.getTxt());
        } else if (result.equals(UserMessages.PASSWORD_IS_NOT_CORRECT)) {
            System.out.println("user login failed: " + result.getTxt());
            //wrongPasswordMenu();
        } else {
            System.out.println("user login failed: " + result.getTxt());
        }
    }
}
