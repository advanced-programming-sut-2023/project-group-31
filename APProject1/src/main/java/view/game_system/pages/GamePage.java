package view.game_system.pages;

import controller.ControllerUtils;
import controller.game_system.GameViewUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.game_system.GameMainPage;

public class GamePage {

    private static Rectangle rectangle;

    @FXML
    private Pane minimap;
    @FXML
    private Pane wholeMapPane;
    @FXML
    public void initialize(){
        addMiniMap();
    }

    private void addMiniMap() {
        Pane pane = GameViewUtils.createMapPane(ControllerUtils.getCurrentMap());
        pane.setScaleX(1);
        pane.setScaleY(1);
        Rectangle rectangle = new Rectangle(20,20);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        wholeMapPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setLayoutX(-GameMainPage.getMapPane().getLayoutX()/GameMainPage.getMapPane().getScaleX());
                rectangle.setLayoutY(-GameMainPage.getMapPane().getLayoutY()/GameMainPage.getMapPane().getScaleY());
                //System.out.println(rectangle.getLayoutX());
                //rectangle.setLayoutX(GameMainPage.getMapPane().getLayoutX());
               // rectangle.setLayoutY(GameMainPage.getMapPane().getLayoutY());
            }
        });
        pane.getChildren().add(rectangle);
        minimap.getChildren().add(pane);

    }

    public static Rectangle getRectangle() {
        return rectangle;
    }
}
