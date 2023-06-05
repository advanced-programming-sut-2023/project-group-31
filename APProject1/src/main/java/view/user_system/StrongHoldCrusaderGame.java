package view.user_system;


import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBase;
import view.enums.Menus;

import java.io.IOException;
import java.net.URL;

public class StrongHoldCrusaderGame extends Application {
    public static void main(String[] args) {
        DataBase.loadApp();
        launch(args);
    }
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loadFXML(Menus.LOGIN);
        StrongHoldCrusaderGame.stage = stage;
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(Menus menus) {
        try {
            URL address = new URL(StrongHoldCrusaderGame.class.getResource("/FXML/" + menus.getName() + ".fxml").toExternalForm());
            FXMLLoader loader = new FXMLLoader(address);
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changeMenu(Menus menuName) {


        Parent root = loadFXML(menuName);
        assert root != null;
        Scene scene = new Scene(root);


        stage.setScene(scene);
    }


}
