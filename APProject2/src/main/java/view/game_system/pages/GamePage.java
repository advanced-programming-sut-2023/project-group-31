package view.game_system.pages;

import controller.ControllerUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.game_stuff.Colors;
import model.game_stuff.Government;
import model.game_stuff.enums.BuildingCategories;
import model.game_stuff.types.Buildings;
import view.game_system.GameMainPage;
import view.game_system.GameViewUtils;

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
    private Label popularity;
    @FXML
    private Label gold;
    @FXML
    private Label population;



    @FXML
    public void initialize(){
        addMiniMap();
        setBuildingList();
        setGovernmentFactors();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> setGovernmentFactors()));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void setGovernmentFactors(){
        Government currentGovernment = null;
        if(ControllerUtils.getCurrentGame()==null){
            return;
        }
        currentGovernment = ControllerUtils.getCurrentPlayer();
        popularity.setText(""+currentGovernment.getPopularity());
        gold.setText(""+currentGovernment.getPossession().getGold());
        popularity.setText(""+currentGovernment.getPopulation());
    }

    private void setBuildingList() {
        for(BuildingCategories buildingCategories:BuildingCategories.values()){
            Button button = new Button();
            button.setPrefWidth(150);
            button.setPrefHeight(35);
            button.setText(buildingCategories.getStr());
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(Node hBox:buildingList.getChildren()){
                        hBox = ((HBox)hBox) ;
                        if(hBox.getId().equals(buildingCategories.getStr())){
                            hBox.setManaged(true);
                            hBox.setVisible(true);
                        } else {
                            hBox.setManaged(false);
                            hBox.setVisible(false);
                        }
                    }
                }
            });
            buildingTypeList.getChildren().add((Node) button);
            HBox buildingsOfOneTypeHBox = new HBox();
            buildingsOfOneTypeHBox.setId(buildingCategories.getStr());
            buildingsOfOneTypeHBox.setPrefWidth(304);
            buildingsOfOneTypeHBox.setPrefHeight(105);
            buildingsOfOneTypeHBox.setVisible(false);
            buildingsOfOneTypeHBox.setManaged(false);
            for(Buildings buildings:Buildings.values()){
                if(buildings.getCategory().equals(buildingCategories)) {
                    Image image = new Image(buildings.getUrl());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    buildingsOfOneTypeHBox.getChildren().add(imageView);
                    imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println("on drag detected");
                            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                            ClipboardContent content = new ClipboardContent();
                            content.putString("building " + buildings.getName());
                            db.setContent(content);

                            mouseEvent.consume();
                        }
                    });
                }
            }
            buildingList.getChildren().add(buildingsOfOneTypeHBox);
        }
        buildingList.setPrefHeight(103);
        buildingList.getChildren().get(0).setVisible(true);
        buildingList.getChildren().get(0).setManaged(true);

    }

    private void addMiniMap() {
        Pane pane = GameViewUtils.createMapPane(ControllerUtils.getCurrentMap());
            pane.setScaleX((double) 150/ControllerUtils.getCurrentMap().getSize());
        pane.setScaleY((double) 150/ControllerUtils.getCurrentMap().getSize());
        Rectangle rectangle = new Rectangle(20,20);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        GameMainPage.setMinimapRectangle(rectangle);
        pane.getChildren().add(rectangle);
        minimap.getChildren().add(pane);

    }

    public static Rectangle getRectangle() {
        return rectangle;
    }
}
