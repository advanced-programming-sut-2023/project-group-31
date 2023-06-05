package view;

import view.user_system.messages.UserMessages;
import controller.ControllerUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataBase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomeMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewUtils.stage = stage;
        BorderPane registerPane = FXMLLoader.load(
                new URL(WelcomeMenu.class.getResource("/FXML/WelcomeMenu.fxml").toExternalForm()));
        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }

    public void submit(MouseEvent mouseEvent) {
        
    }

    public void reset(MouseEvent mouseEvent) {
    }
}
