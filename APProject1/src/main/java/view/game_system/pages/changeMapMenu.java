package view.game_system.pages;

import controller.ControllerUtils;
import controller.game_system.StartGameController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.game_stuff.enums.Textures;
import model.game_stuff.enums.TreeTypes;
import view.game_system.GameSwitcher;
import view.game_system.messages.StartGameMessages;

import java.util.HashMap;

public class changeMapMenu {
    private ChoiceBox texture;
    private ChoiceBox treeType;
    @FXML
    private HBox textureChoiceBox;
    @FXML
    private HBox treeChoiceBox;

    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private CheckBox severalBlocks;
    @FXML
    private TextField x1;
    @FXML
    private TextField y1;
    @FXML
    private TextField x2;
    @FXML
    private TextField y2;

    @FXML
    private CheckBox dropTree;
    @FXML
    private CheckBox clearBlock;

    @FXML
    public void initialize() {
        setTextureChoiceBox();
        setTreeChoiceBox();
    }

    private void setTextureChoiceBox() {
        texture = new ChoiceBox();
        for (Textures textures : Textures.values()) {
            this.texture.getItems().add(textures.getName());
        }
        texture.setValue("choose new texture");
        textureChoiceBox.getChildren().add(texture);
    }

    private void setTreeChoiceBox() {
        treeType = new ChoiceBox();
        for (TreeTypes treeTypes : TreeTypes.values()) {
            this.treeType.getItems().add(treeTypes.getName());
        }
        treeType.setValue("olive");
        treeType.setDisable(true);
        textureChoiceBox.getChildren().add(treeType);
    }

    public void back(MouseEvent mouseEvent) {
        GameSwitcher.stage2.close();
    }

    public void getSeveralBlocks(MouseEvent mouseEvent) {
        if (severalBlocks.isSelected()) {
            x.setDisable(true);
            y.setDisable(true);
            x1.setDisable(false);
            y1.setDisable(false);
            x2.setDisable(false);
            y2.setDisable(false);
        } else {
            x.setDisable(false);
            y.setDisable(false);
            x1.setDisable(true);
            y1.setDisable(true);
            x2.setDisable(true);
            y2.setDisable(true);
        }
    }

    public void save(MouseEvent mouseEvent) {
        int x1, y1, x2, y2;
        if (severalBlocks.isSelected()) {
            if ((!this.x1.getText().matches("[\\d]+")
                    || !this.y1.getText().matches("[\\d]+")
                    || !this.x2.getText().matches("[\\d]+")
                    || !this.y2.getText().matches("[\\d]+"))) {
                StartGameMessages.showAlert(StartGameMessages.INVALID_COMMAND);
                return;
            }
            x1 = Integer.parseInt(this.x1.getText());
            y1 = Integer.parseInt(this.y1.getText());
            x2 = Integer.parseInt(this.x2.getText());
            y2 = Integer.parseInt(this.y1.getText());
        } else {
            if ((!this.x.getText().matches("[\\d]+")
                    || !this.y.getText().matches("[\\d]+"))) {
                StartGameMessages.showAlert(StartGameMessages.INVALID_COMMAND);
                return;
            }
            x1 = Integer.parseInt(this.x.getText());
            y1 = Integer.parseInt(this.y.getText());
            x2 = Integer.parseInt(this.x.getText());
            y2 = Integer.parseInt(this.y.getText());
        }
        StartGameMessages message;
        if (dropTree.isSelected()) {
            if (!(message = StartGameController.dropTrees(x1, y1, x2, y2, treeType.getValue().toString())).equals(StartGameMessages.SUCCESS)) {
                StartGameMessages.showAlert(message);
                return;
            }
        }
        if(!texture.getValue().equals("choose new texture")){
            ControllerUtils.setInputs(new HashMap<>());
            ControllerUtils.putInput("x1",String.valueOf(x1));
            ControllerUtils.putInput("y1",String.valueOf(y1));
            ControllerUtils.putInput("x2",String.valueOf(x2));
            ControllerUtils.putInput("y2",String.valueOf(y2));
            ControllerUtils.putInput("type",texture.getValue().toString());
            if (!(message = StartGameController.setARectanglesTexture()).equals(StartGameMessages.SUCCESS)) {
                StartGameMessages.showAlert(message);
                return;
            }
        }
        if(clearBlock.isSelected()){
            if(!(message=StartGameController.clearBlocks(x1,y1,x2,y2)).equals(StartGameMessages.SUCCESS)){
                StartGameMessages.showAlert(message);
                return;
            }
        }
        GameSwitcher.stage2.close();

    }

    public void dropTreeClicked(MouseEvent mouseEvent) {
        if(dropTree.isSelected()){
            treeType.setDisable(false);
        } else {
            dropTree.setDisable(true);
        }
    }
}
