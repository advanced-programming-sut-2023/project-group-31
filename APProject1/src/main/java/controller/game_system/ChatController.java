package controller.game_system;

import controller.ControllerUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import model.User;

import java.util.ArrayList;

public class ChatController extends ControllerUtils {


    @FXML
    private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private Label onlineCountLabel;
    @FXML private ListView userList;
    @FXML private ImageView userImageView;
    @FXML ListView chatPane;
    @FXML
    BorderPane borderPane;
    @FXML
    ComboBox statusComboBox;
    
    @FXML
    public void initialize(){
        setCurrentUserHBOX();
    }

    private void setCurrentUserHBOX() {
        if(currentUser == null){
            return;
        }
        userImageView.setImage(new Image(ChatController.class.getResource(currentUser.getUrl()).toExternalForm()));
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }

    public void sendButtonAction(ActionEvent actionEvent) {
    }

    private void groupList(){
        Platform.runLater(() -> {
            ArrayList<User> users =new  ArrayList<>();
            for(String username : User.getUsers().keySet()){
                users.add(User.getUsers().get(username));
            }
            ObservableList<User> usersList = FXCollections.observableList(users);
            userList.setItems(usersList);
        });
    }
}
