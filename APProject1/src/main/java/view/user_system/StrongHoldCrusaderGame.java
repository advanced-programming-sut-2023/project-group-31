package view.user_system;


import client.Client;
import com.google.gson.Gson;
import controller.ControllerUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBase;
import model.Session;
import model.User;
import view.enums.Menus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class StrongHoldCrusaderGame extends Application {
    public static void main(String[] args) throws IOException {
        Client.setClient(new Client("localhost",8080));
        DataBase.loadApp();
        launch(args);
    }
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        if(Session.getSession().authentication()){
            root = loadFXML(Menus.MAIN);
        } else {
            root =loadFXML(Menus.LOGIN);
        }

        StrongHoldCrusaderGame.stage = stage;
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(Menus menus) {
        try {
            URL address = new URL(StrongHoldCrusaderGame.class.getResource("/FXML/user_system/" + menus.getName() + ".fxml").toExternalForm());
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
