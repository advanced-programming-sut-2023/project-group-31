
import controller.game_system.StartGameController;
import model.DataBase;
import view.game_system.StartGameMenu;
import view.user_system.MenuSwitcher;

public class Main {
    public static void main(String[] args) {
        DataBase.loadApp();
        MenuSwitcher.run();
    }

}


