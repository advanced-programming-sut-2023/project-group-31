package view.user_system.pages;

import controller.ControllerUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import model.User;
import view.enums.Menus;
import view.user_system.StrongHoldCrusaderGame;

public class MainMenuPage {
    public void startGame(MouseEvent mouseEvent) {
    }

    public void profile(MouseEvent mouseEvent) {
           StrongHoldCrusaderGame.changeMenu(Menus.PROFILE);
    }

    public void scoreBoard(MouseEvent mouseEvent) {
        StrongHoldCrusaderGame.changeMenu(Menus.SCOREBOARD);
    }

    public void logout(MouseEvent mouseEvent) { Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            ControllerUtils.setCurrentUser(null);
            Alert alertFinish=new Alert(Alert.AlertType.INFORMATION, "Logout successfull\nProceeding to login menu");
            alertFinish.showAndWait();
            StrongHoldCrusaderGame.changeMenu(Menus.LOGIN);
        }
    }

    public void exit(MouseEvent mouseEvent) {
        //TODO
        //save users
        System.exit(0);
    }

}
