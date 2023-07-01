package view.game_system.pages;

import controller.ControllerUtils;
import view.game_system.GameViewUtils;
import controller.game_system.StartGameController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.DataBase;
import model.User;
import model.game_stuff.Colors;
import model.game_stuff.Map;
import view.enums.Menus;
import view.game_system.GameMainPage;
import view.game_system.GameSwitcher;
import view.game_system.messages.StartGameMessages;
import view.user_system.StrongHoldCrusaderGame;

import java.io.IOException;
import java.util.ArrayList;

public class StartGame {
    private int usersCount;
    private int usersStart;

    private ArrayList<String> players;

    private int mapIndex = 0;

    public StartGame() {
        usersStart = 0;
        usersCount = 8;
        players = new ArrayList<>();
    }

    @FXML
    private VBox usersVbox;
    @FXML
    private ScrollPane usersScroll;

    @FXML
    private ScrollPane playersScroll;

    @FXML
    private VBox playersList;

    @FXML
    private Pane mapPane;

    @FXML
    public void initialize() throws IOException {
        if (ControllerUtils.getCurrentUser() != null) {
            setFirstPlayer();
        }
        setPlayersList();
        setMapPain(ControllerUtils.getCurrentMap());
    }

    private void setMapPain(Map map) {
        Pane pane = GameViewUtils.createMapPane(map);
        pane.setScaleX((double) 200 / map.getSize());
        pane.setScaleY((double) 200 / map.getSize());
        mapPane.getChildren().add(pane);
    }


    public void setFirstPlayer() {
        HBox hBox = (HBox) createPlayerRow(ControllerUtils.getCurrentUser());
        addAnyPlayer(hBox,ControllerUtils.getCurrentUser().getUsername());
    }

    private void setPlayersList() throws IOException {
        System.out.println(DataBase.getDataBase().getUsers().size());
        for (int i = usersStart; i < usersStart + usersCount; i++) {
            if (i >= DataBase.getDataBase().getUsers().size()) {
                return;
            }
            usersVbox.getChildren().add(createPlayerRow(DataBase.getDataBase().getUsers().get(i)));
        }
    }

    private HBox createPlayerRow(User user) {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-start-margin: 20");
        System.out.println(StartGame.class.getResource(user.getUrl()).toExternalForm());
        ImageView imageView = new ImageView(new Image(StartGame.class.getResource(user.getUrl()).toExternalForm()));
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
                addNewPlayer(mouseEvent);
            }
        });
        return hBox;
    }

    private void addNewPlayer(MouseEvent mouseEvent) {
        HBox hBox = (HBox) ((Button) (mouseEvent.getSource())).getParent();
        String buttonId=((Button) (mouseEvent.getSource())).getId();
        addAnyPlayer(hBox,buttonId);
    }
    private void addAnyPlayer(HBox hBox,String buttonId){
        players.add(buttonId);
        hBox.getChildren().remove(3);
        ChoiceBox choiceBox = new ChoiceBox();
        for (Colors color : Colors.values()) {
            choiceBox.getItems().add(color.getName());
        }
        choiceBox.setValue("choose color");
        choiceBox.setPrefWidth(100);
        choiceBox.setPrefHeight(50);
        hBox.getChildren().add(choiceBox);
        Button button = new Button();
        button.setStyle("-fx-background-color: red;");
        button.setText("remove");
        button.setId(buttonId);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removePlayer(mouseEvent);
            }
        });
        hBox.getChildren().add(button);
        playersList.getChildren().add(hBox);
    }

    private void removePlayer(MouseEvent mouseEvent) {
        HBox hBox = (HBox) ((Button) (mouseEvent.getSource())).getParent();
        players.remove(((Button) (mouseEvent.getSource())).getId());
        hBox.getChildren().remove(3);
        hBox.getChildren().remove(3);
        Button button = new Button();
        button.setMnemonicParsing(false);
        button.setPrefHeight(52);
        button.setPrefWidth(113);
        button.setText("add");
        button.setId(((Button) (mouseEvent.getSource())).getId());
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addNewPlayer(mouseEvent);
            }
        });
        hBox.getChildren().add(button);
        usersVbox.getChildren().add(hBox);
    }

    public void checkScroll(ScrollEvent event) {
        System.out.println(event.getDeltaY());

    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        ArrayList<String> colors = new ArrayList<>();
        for (Object hBox : playersList.getChildren()) {
            hBox = (HBox) hBox;
            if (((ChoiceBox) (((HBox) hBox).getChildren().get(3))).getValue().equals("choose color")) {
                StartGameMessages.showAlert(StartGameMessages.CHOOSE_COLOR);
                return;
            }
            colors.add((String) (((ChoiceBox) (((HBox) hBox).getChildren().get(3))).getValue()));
        }
        System.out.println(players);
        for (int i = 0; i < players.size(); i++) {
            StartGameController.addPlayer(players.get(i));
            StartGameController.setPlayersLordHouse(players.get(i), i);
            StartGameController.setPlayersTeam(players.get(i), colors.get(i));
        }
        StartGameMessages message;
        if (!(message = StartGameController.start()).equals(StartGameMessages.SUCCESS)) {
            StartGameMessages.showAlert(message);
        }
        new GameMainPage().start(StrongHoldCrusaderGame.stage);
    }

    public void showNextMap(MouseEvent mouseEvent) {
        System.out.println(Map.getMaps().size());
        if(Map.getMaps().size()<1){
            return;
        }
        mapPane.getChildren().remove(0);

        mapIndex =((mapIndex+1)%Map.getMaps().size());
        setMapPain(Map.getMaps().get(mapIndex));
        StartGameMessages message = StartGameController.chooseMap(Map.getMaps().get(mapIndex).getName());
    }

    public void showPreviousMap(MouseEvent mouseEvent) {
        if(Map.getMaps().size()<1){
            return;
        }
        mapPane.getChildren().remove(0);
        mapIndex = ((Map.getMaps().size()+mapIndex-1)%Map.getMaps().size());
        setMapPain(Map.getMaps().get(mapIndex));
        StartGameMessages message = StartGameController.chooseMap(Map.getMaps().get(mapIndex).getName());
    }

    public void editMap(MouseEvent mouseEvent) {
        GameSwitcher.startMenu(Menus.CHANGE_MAP);
    }
}
