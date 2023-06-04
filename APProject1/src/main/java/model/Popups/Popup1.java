package model.Popups;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;

public class Popup1 {
    public static Button cancel=new Button("Cancel");
    @FXML
    private Label Header;
    @FXML
    private Label label2;
    @FXML
    private Label label1;
    @FXML
    private PasswordField second;
    @FXML
    private PasswordField first;
    private static User tologgedIn;
    @FXML
    private TextField answer;
    static Stage popupStage;
    public static void display(User user) throws IOException {
        tologgedIn=user;
         popupStage= new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        URL url= Popup1.class.getResource("/FXML/popup.fxml");
        Pane pane= FXMLLoader.load(url);
        cancel.setOnAction(event -> popupStage.close());
        Scene scene=new Scene(pane);
        popupStage.setScene(scene);
        popupStage.show();
    }
    public void initialize(){
        Header.setText(tologgedIn.getPasswordRecoveryQuestion());
        first.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!first.getText().matches(".*[!@#$%^&(\\)].*")||!first.getText().matches(".*[0-9].*")||!first.getText().matches(".*[a-z].*")||!first.getText().matches(".*[A-Z].*")){
                 label1.setStyle("-fx-background-color: tomato");
                }
                else {
                    label1.setStyle("-fx-background-color: green");
                }
            }
        });
        second.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!second.getText().matches(".*[!@#$%^&(\\)].*")||!second.getText().matches(".*[0-9].*")||!second.getText().matches(".*[a-z].*")||!second.getText().matches(".*[A-Z].*")){
                    label2.setStyle("-fx-background-color: tomato");
                }
                else {
                    label2.setStyle("-fx-background-color: green");
                }
            }
        });
    }
    public void save(MouseEvent mouseEvent) {
        if (second.getText().equals(first.getText())&&answer.getText().equals(tologgedIn.getPasswordRecoveryAnswer())){
            tologgedIn.setPassword(first.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "are you sure to change your password?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.YES){
                Alert alertFinish=new Alert(Alert.AlertType.INFORMATION, "Your password change successfully!!!");
                alertFinish.showAndWait();
                popupStage.close();
            }

        }
    }
}
