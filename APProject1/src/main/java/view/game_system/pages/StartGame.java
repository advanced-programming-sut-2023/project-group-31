package view.game_system.pages;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.DataBase;
import model.User;

import java.io.IOException;

public class StartGame {
    private int playersCount;
    private int playersStart;

    public StartGame() {
        playersStart = 0;
        playersCount = 8;
    }

    @FXML
    private VBox playersVbox;
    @FXML
    private ScrollPane playerScroll;

    @FXML
    public void initialize() throws IOException{
        setPlayersList();
    }




    private void setPlayersList() {
//        for(int i=0;i<playersVbox.getChildren().size();i++){
//            playersVbox.getChildren().remove(i);
//            i--;
//        }
        for (int i = playersStart; i < playersStart + playersCount; i++) {
            if (i >= DataBase.getDataBase().getUsers().size()) {
                return;
            }
            playersVbox.getChildren().add(createPlayerRow(i));
        }
    }

    private HBox createPlayerRow(int index) {
        User user = DataBase.getDataBase().getUsers().get(index);
        HBox hBox = new HBox();
        hBox.setStyle("-fx-start-margin: 20");
        ImageView imageView = new ImageView(new Image(user.getUrl()));
        imageView.setFitHeight(43.0);
        imageView.setFitWidth(58.0);
        Label label1 = new Label();
        label1.setAlignment(Pos.CENTER);
        label1.setPrefHeight(57.0);
        label1.setPrefWidth(150.0);
        label1.setTextFill(Paint.valueOf("#c91515"));
        label1.setFont(Font.font("System Bold"));
        label1.setStyle("-fx-font-size: 24");
        label1.setText(user.getUsername());
        Label label2 = new Label();
        label2.setAlignment(Pos.CENTER);
        label2.setPrefHeight(57.0);
        label2.setPrefWidth(150.0);
        label2.setTextFill(Paint.valueOf("#c91515"));
        label2.setFont(Font.font("System Bold"));
        label2.setText(user.getNickname());
        label2.setStyle("-fx-font-size: 22");
        Button button = new Button();
        button.setMnemonicParsing(false);
        button.setPrefHeight(52);
        button.setPrefWidth(113);
        button.setText("add");
        button.setId(user.getUsername());
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(label1);
        hBox.getChildren().add(label2);
        hBox.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        return hBox;
    }

    public void checkScroll(ScrollEvent event) {
            System.out.println(event.getDeltaY());

    }
}
