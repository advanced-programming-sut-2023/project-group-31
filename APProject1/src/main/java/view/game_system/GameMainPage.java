package view.game_system;

import controller.ControllerUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;

public class GameMainPage extends Application {
    private static Pane root;
    private static Rectangle minimapRectangle;

    private static Pane mapPane;
    @Override
    public void start(Stage stage) throws Exception {
        DataBase.loadApp();
        URL address = new URL(GameMainPage.class.getResource("/FXML/game_system/gameMain" +
                "Page.fxml").toExternalForm());
        FXMLLoader loader = new FXMLLoader(address);
        root = loader.load();
        root.setPrefHeight(900);
        root.setPrefWidth(1500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //mapPane =GameViewUtils.createMapPane(ControllerUtils.getCurrentMap());
        root.getChildren().add(0,mapPane);
        mapPane.requestFocus();

        //TODO : fix problem whit bounds (mapPane position)
        //TODO : change mouse move to hover property
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getSceneX() > scene.getWidth() - 25 && mapPane.getLayoutX() > -1 * mapPane.getPrefWidth() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutX(mapPane.getLayoutX() - 25);
                    if(minimapRectangle != null) {
                        minimapRectangle.setLayoutX((double) ControllerUtils.getCurrentMap().getSize()/2+
                                (-GameMainPage.getMapPane().getLayoutX()/GameMainPage.getMapPane().getScaleX()));
                    }
                }
                if(mouseEvent.getSceneX() < 25 && mapPane.getLayoutX() < mapPane.getPrefWidth() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutX(mapPane.getLayoutX() + 25);
                    if(minimapRectangle != null) {
                        minimapRectangle.setLayoutX((double) ControllerUtils.getCurrentMap().getSize()/2+
                                (-GameMainPage.getMapPane().getLayoutX()/GameMainPage.getMapPane().getScaleX()));
                    }
                }
                if(mouseEvent.getSceneY() > scene.getHeight() - 25 - 130 && mouseEvent.getSceneY() < scene.getHeight() - 130
                        && mapPane.getLayoutY() > -1 * mapPane.getPrefHeight() * mapPane.getScaleY() / 2) {
                    mapPane.setLayoutY(mapPane.getLayoutY() - 25);
                    if(minimapRectangle != null) {
                        minimapRectangle.setLayoutY((double) ControllerUtils.getCurrentMap().getSize()/2+
                                (-GameMainPage.getMapPane().getLayoutY()/GameMainPage.getMapPane().getScaleY()));
                    }
                }
                if(mouseEvent.getSceneY() < 25 && mapPane.getLayoutY() < mapPane.getPrefHeight() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutY(mapPane.getLayoutY() + 25);
                    if(minimapRectangle != null) {
                        minimapRectangle.setLayoutY((double) ControllerUtils.getCurrentMap().getSize()/2+
                                (-GameMainPage.getMapPane().getLayoutY()/GameMainPage.getMapPane().getScaleY()));
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    public static Pane getRoot() {
        return root;
    }

    public static Pane getMapPane() {
        return mapPane;
    }

    public static void setMapPane(Pane mapPane) {
        GameMainPage.mapPane = mapPane;
    }

    public static void setMinimapRectangle(Rectangle minimapRectangle) {
        GameMainPage.minimapRectangle = minimapRectangle;
    }
}
