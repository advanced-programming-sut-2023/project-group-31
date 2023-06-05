package view.game_system.pages;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public void initialize() {
        checkScroll(playersVbox);
        setPlayersList();
    }

    private void checkScroll(VBox playersVbox) {
        playersVbox.setOnScroll((ScrollEvent event) -> {
            System.out.println(event.getDeltaY());
        });
    }

    private void setPlayersList() {
        createPlayerRow();
        for (int i = playersStart; i < playersStart + playersCount; i++) {

            //((ImageView)(hBox.getChildren().get(0))).setImage(new Image(DataBase.getDataBase().getUsers().get(i).getUrl()));
            //((Label)(hBox.getChildren().get(1))).setText(DataBase.getDataBase().getUsers().get(i).getUrl());
        }
    }

    private HBox createPlayerRow(){
        //User user = DataBase.getDataBase().getUsers().get(index);
        HBox hBox = new HBox();
        hBox.setStyle("-fx-start-margin: 20");
//        ImageView imageView = new ImageView("new Image(user.getUrl())");
//        imageView.setFitHeight(43.0);
//        imageView.setFitWidth(58.0);
        Label label1 = new Label();
        label1.setAlignment(Pos.CENTER);
        label1.setPrefHeight(57.0);
        label1.setPrefWidth(150.0);
        label1.setTextFill(Paint.valueOf("#c91515"));
        label1.setFont(Font.font("System Bold"));
        label1.setStyle("-fx-font-size: 24");
        label1.setText("user.getUsername()");
        Label label2 = new Label();
        label2.setAlignment(Pos.CENTER);
        label2.setPrefHeight(57.0);
        label2.setPrefWidth(150.0);
        label2.setTextFill(Paint.valueOf("#c91515"));
        label2.setFont(Font.font("System Bold"));
        label2.setText("user.getNickname()");
        label2.setStyle("-fx-font-size: 22");
//        hBox.getChildren().add(imageView);
        hBox.getChildren().add(label1);
        hBox.getChildren().add(label2);
        return hBox;
    }
//                        <HBox>
//                        <ImageView id="boxImage" fitHeight="43.0" fitWidth="58.0">
//                            <Image url="@../../Media/Avatars/avatar1.png"/>
//
//                        </ImageView>
//
//                        <Label alignment="CENTER" prefHeight="57.0" prefWidth="150.0" text="maamad" textFill="#c91515">
//                            <font>
//                                <Font name="System Bold" size="24.0"/>
//                            </font>
//                        </Label>
//                        <Label alignment="CENTER" prefHeight="54.0" prefWidth="206.0" text="mmirzadi"
//    textFill="#c91515">
//                            <font>
//                                <Font name="System Bold" size="20.0"/>
//                            </font>
//                        </Label>
//                        <Button mnemonicParsing="false" prefHeight="52.0" prefWidth="113.0" text="add"/>
//                    </HBox>
}
