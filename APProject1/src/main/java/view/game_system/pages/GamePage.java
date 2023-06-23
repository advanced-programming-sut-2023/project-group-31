package view.game_system.pages;

import controller.ControllerUtils;
import controller.game_system.GameViewUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_stuff.enums.BuildingCategories;
import model.game_stuff.types.Buildings;
import view.game_system.GameMainPage;

public class GamePage {

    private static Rectangle rectangle;

    @FXML
    private Pane minimap;
    @FXML
    private Pane wholeMapPane;

    @FXML
    private HBox buildingTypeList;

    @FXML
    private VBox buildingList;


    @FXML
    public void initialize(){
        addMiniMap();
        setBuildingList();
    }

    private void setBuildingList() {
        for(BuildingCategories buildingCategories:BuildingCategories.values()){
            Button button = new Button();
            button.setPrefWidth(150);
            button.setPrefHeight(35);
            button.setText(buildingCategories.getStr());
            buildingTypeList.getChildren().add((Node) button);
            for(Buildings buildings:Buildings.values()){
                if(buildings.getCategory().equals(buildingCategories)){
                    buildings.get
                }
            }
        }

    }

    private void addMiniMap() {
        Pane pane = GameViewUtils.createMapPane(ControllerUtils.getCurrentMap());
            pane.setScaleX((double) 150/ControllerUtils.getCurrentMap().getSize());
        pane.setScaleY((double) 150/ControllerUtils.getCurrentMap().getSize());
        Rectangle rectangle = new Rectangle(20,20);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        wholeMapPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setLayoutX((double) ControllerUtils.getCurrentMap().getSize()/2+
                        (-GameMainPage.getMapPane().getLayoutX()/GameMainPage.getMapPane().getScaleX()));
                rectangle.setLayoutY((double) ControllerUtils.getCurrentMap().getSize()/2+
                        (-GameMainPage.getMapPane().getLayoutY()/GameMainPage.getMapPane().getScaleY()));

            }
        });
        pane.getChildren().add(rectangle);
        minimap.getChildren().add(pane);

    }

    public static Rectangle getRectangle() {
        return rectangle;
    }
}
