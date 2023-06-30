package view.user_system.pages;

import controller.ControllerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.User;
import view.enums.Menus;
import view.user_system.StrongHoldCrusaderGame;

import java.io.File;
import java.net.MalformedURLException;

public class ProfileMenuPage {
    @FXML
    private ImageView avatarPic;
    private int avatarPicCount = 4;
    private int currentPic = 1;
    @FXML
    private TextField nickname;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private TextField email;
    public void initialize(){
        username.textProperty().addListener(observable ->
        {
            if (username.getText().matches(".*[!@#$%^&*(\\)].*")) {
                if (username.getText().matches(".*[!@#$%^&*(\\)].*")) {
                    username.setStyle("-fx-background-color: #fa0202");
                } else if (username.getText().equals("")) {
                    username.setStyle("-fx-background-color: #fa0202");

                } else {
                    username.setStyle("-fx-background-color:  #00FF00");
                }
            }
        });
        System.out.println(ControllerUtils.getCurrentUser().getUrl().substring(21,22));
        avatarPic.setImage(new Image(ProfileMenuPage.class.getResource(ControllerUtils.getCurrentUser().getUrl()).toString()));
        currentPic=Integer.parseInt(ControllerUtils.getCurrentUser().getUrl().substring(21,22));
    }
    public void backToMain(MouseEvent mouseEvent) {
        StrongHoldCrusaderGame.changeMenu(Menus.MAIN);
    }

    public void selectImage(MouseEvent mouseEvent) {
        UploadImageActionPerformed();
    }
    @FXML
    public static void UploadImageActionPerformed(){


        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            Image image=new Image(file.toURI().toString());
            System.out.println(image.getUrl());
            ControllerUtils.getCurrentUser().setUrl(image.getUrl());
        }
    }

    public void changeAvatarPic(MouseEvent mouseEvent) throws MalformedURLException {
        if (currentPic == avatarPicCount) {
            currentPic = 1;

        } else {
            currentPic++;
        }
        Image nextImage = new Image(ProfileMenuPage.class.getResource("/Media/Avatars/avatar"+ currentPic + ".png").toExternalForm());
        avatarPic.setImage(nextImage);
        ControllerUtils.getCurrentUser().setUrl(avatarPic.getImage().getUrl().substring(78));
        System.out.println(ControllerUtils.getCurrentUser().getUrl());
    }

    public void changeNickname(MouseEvent mouseEvent) {
        {
            if (email.getText().equals("")) {
                error.setText("Fill email field");
                error.setStyle("-fx-text-fill: #ff0066;");
                return;
            }
            ControllerUtils.getCurrentUser().setNickname(nickname.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "nickname changed successfully");
            nickname.setText("");
            alert.showAndWait();
            return;



        }
    }



    public void changeUsername(MouseEvent mouseEvent) {
        {
            if (username.getText().equals("")) {
                error.setText("Fill username field");
                error.setStyle("-fx-text-fill: #ff0066;");
                return;
            }
            if (User.getUserByUsername(username.getText())==null) {
                ControllerUtils.getCurrentUser().setUsername(username.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "username changed successfully");
                username.setText("");
                alert.showAndWait();
                return;
            }

            error.setText("This username is taken");
            error.setStyle("-fx-text-fill: #ff0066;");

        }
    }

    public void changeEmail(MouseEvent mouseEvent) {
        {
            if (email.getText().equals("")) {
                error.setText("Fill email field");
                error.setStyle("-fx-text-fill: #ff0066;");
                return;
            }
            if (User.getUserByEmail(email.getText())==null) {
                ControllerUtils.getCurrentUser().setEmail(email.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "email changed successfully");
                email.setText("");
                alert.showAndWait();
                return;
            }

            error.setText("This email is taken");
            error.setStyle("-fx-text-fill: #ff0066;");

        }
    }

    public void show(MouseEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("my Profile");
        alert.setHeaderText("Your registered information");
        alert.setContentText("username: "+ControllerUtils.getCurrentUser().getUsername()+"\npassword: "+ControllerUtils.getCurrentUser().getPassword()
                +"\nslogan: "+ControllerUtils.getCurrentUser().getSlogan()+"\nemail: "+ControllerUtils.getCurrentUser().getEmail());
        alert.showAndWait();
    }


}
