package view.user_system.pages;

import controller.ControllerUtils;
import controller.user_system.RegisterController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.User;
import view.enums.Menus;
import view.user_system.StrongHoldCrusaderGame;
import view.user_system.messages.UserMessages;

import java.util.ArrayList;
import java.util.Random;

public class RegisterPage {
    @FXML
    private TextField email;
    @FXML
    private PasswordField enterAgain;
    @FXML
    private Label passwordState;
    @FXML
    private Label error;
    @FXML
    private TextField nickname;
    @FXML
    private Button randomPass;
    @FXML
    private TextField passwordOfPassword2;
   @FXML
    private PasswordField passwordOfPassword1;
    @FXML
    private CheckBox passwordProperty;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private CheckBox popularSlogans;
    @FXML
    private CheckBox randomSlogan;
    @FXML
    private TextField sloganField;
    @FXML
    private CheckBox slogan;
    private static ArrayList<String>slogans;
    static {
        slogans=new ArrayList<>();
        slogans.add("We are will do it");
        slogans.add("make America great again!");
        slogans.add("Independence freedom Islamic republic!");
        slogans.add("Woman life freedom!");
    }
    private static ArrayList<Integer>captchaImage=LoginPage.getCaptchaImage();
    private static User user;
    public void show(MouseEvent mouseEvent) {
        if (slogan.isSelected()){
          sloganField.setVisible(true);
          randomSlogan.setVisible(true);
          popularSlogans.setVisible(true);
        }
        if (!slogan.isSelected()){
            sloganField.setVisible(false);
            randomSlogan.setVisible(false);
            popularSlogans.setVisible(false);
        }
    }
    public void initialize(){
        if (!slogan.isSelected()){
            sloganField.setVisible(false);
            randomSlogan.setVisible(false);
            popularSlogans.setVisible(false);
        }
        usernameField.textProperty().addListener(observable -> {
            if ( usernameField.getText().matches(".*[!@#$%^&*(\\)].*")) {
                usernameLabel.setText("invalid format for username");
                usernameField.setStyle("-fx-background-color: #fa0202");
                usernameLabel.setStyle("-fx-text-fill: #fa0202");
            }
            else if (usernameField.getText().equals("")){
                usernameLabel.setText("Fill username field");
                usernameField.setStyle("-fx-background-color: #fa0202");
                usernameLabel.setStyle("-fx-text-fill: #fa0202");
            }
            else {
                usernameLabel.setText("");
                usernameField.setStyle("-fx-background-color:  #00FF00");
                usernameLabel.setStyle("-fx-text-fill: #00FF00");
            }
        });
        passwordOfPassword2.setVisible(false);
        passwordOfPassword2.setManaged(false);
        passwordOfPassword2.managedProperty().bind(passwordProperty.selectedProperty());
        passwordOfPassword2.visibleProperty().bind(passwordProperty.selectedProperty());

        passwordOfPassword1.managedProperty().bind(passwordProperty.selectedProperty().not());
        passwordOfPassword1.visibleProperty().bind(passwordProperty.selectedProperty().not());
        passwordOfPassword2.textProperty().bindBidirectional(passwordOfPassword1.textProperty());
        passwordOfPassword1.textProperty().addListener(observable -> {
         String state;
            if ((state=checkWeakness(passwordOfPassword1.getText())) !=null) {
                passwordState.setText(state);
                passwordState.setStyle("-fx-text-fill: #fa0202");
            }
            else {
                passwordState.setText("");
                passwordState.setStyle("-fx-text-fill: #00FF00");
            }
        });
    }

    private String checkWeakness(String password) {
        if (password.length() < 6) {
            return "password is short!";
        }
        if (!password.matches(".*[a-z].*")) {
            return "password should have small letter!";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "password should have capital letter!";
        }
        if (!password.matches(".*[\\d].*")) {
            return "password should have at least one digit!";
        }
        if (!password.matches(".*([^\\da-zA-Z]).*")) {
            return "password should have one not letter nad numeric character!";
        }
        return null;
    }

    public void randomSlogan(MouseEvent mouseEvent) {
        if (randomSlogan.isSelected()){
        String slogan=randomSlo();
        sloganField.setText(slogan);}
    }

    private String randomSlo() {
        Random random=new Random();
        int randomNum=random.nextInt();
        if (randomNum<0){
            randomNum=randomNum*-1;
        }
        String toReturn=slogans.get(randomNum%4);
        return toReturn;
    }


    public void showPopularSlogan(MouseEvent mouseEvent) {
        if (popularSlogans.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("We are will do it!\nmake America great again!\nIndependence freedom Islamic republic!\nWoman life freedom!");
            alert.setTitle("popular slogans");
            alert.setHeaderText("the most popular slogans");
            alert.showAndWait();
        }
    }

    public void randomPassword(MouseEvent mouseEvent) {
        String randomPassword=RegisterController.generateNewPassword();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("random password");
        alert.setHeaderText("you select random password option");
        alert.setContentText("are you sure that want "+ randomPassword+" for your password?");
        alert.showAndWait();
        if (alert.getResult()==ButtonType.YES){
            alert.close();
            passwordOfPassword2.setText(randomPassword);
            passwordOfPassword1.setText(randomPassword);
        }
    }

    public void register(MouseEvent mouseEvent) {
        if (nickname.getText().equals("")||passwordOfPassword1.getText().equals("")||usernameField.getText().equals("")){
           error.setText("Fill all fields");
           return;
        }
        if (slogan.isSelected()&&sloganField.getText().equals("")&&!randomSlogan.isSelected()){
            error.setText("Fill all fields");
            return;
        }
        UserMessages result;
        ControllerUtils.putInput("username",usernameField.getText());
        ControllerUtils.putInput("password",passwordOfPassword1.getText());
        ControllerUtils.putInput("passwordConfirmation",enterAgain.getText());
        ControllerUtils.putInput("email",email.getText());
        ControllerUtils.putInput("slogan",sloganField.getText());
        if ((result=RegisterController.userCreate())!=UserMessages.SUCCESS){
              error.setText(result.getTxt());
              return;
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Moving to pick question");
        alert.setContentText("Your information has been registered");
        alert.showAndWait();
        user=User.getUserByUsername(usernameField.getText());
        StrongHoldCrusaderGame.changeMenu(Menus.PICKQUESION);
    }

    public void back(MouseEvent mouseEvent) {
        StrongHoldCrusaderGame.changeMenu(Menus.LOGIN);
    }

    public static User getUser() {
        return user;
    }
}
