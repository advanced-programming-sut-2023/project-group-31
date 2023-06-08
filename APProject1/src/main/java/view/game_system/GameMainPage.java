package view.game_system;

import controller.ControllerUtils;
import controller.game_system.GameViewUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameMainPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setPrefHeight(700);
        root.setPrefWidth(1200);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Pane mapPane = GameViewUtils.createMapPane(ControllerUtils.getCurrentMap());
        root.getChildren().add(mapPane);
        mapPane.requestFocus();

        //TODO : fix problem whit bounds (mapPane position)
        //TODO : change mouse move to hover property
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getSceneX() > scene.getWidth() - 25 && mapPane.getLayoutX() > -1 * mapPane.getPrefWidth() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutX(mapPane.getLayoutX() - 25);
                }
                if(mouseEvent.getSceneX() < 25 && mapPane.getLayoutX() < mapPane.getPrefWidth() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutX(mapPane.getLayoutX() + 25);
                }
                if(mouseEvent.getSceneY() > scene.getHeight() - 25 && mapPane.getLayoutY() > -1 * mapPane.getPrefHeight() * mapPane.getScaleY() / 2) {
                    mapPane.setLayoutY(mapPane.getLayoutY() - 25);
                }
                if(mouseEvent.getSceneY() < 25 && mapPane.getLayoutY() < mapPane.getPrefHeight() * mapPane.getScaleX() / 2) {
                    mapPane.setLayoutY(mapPane.getLayoutY() + 25);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
