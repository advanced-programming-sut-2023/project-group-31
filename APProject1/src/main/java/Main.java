
import controller.game_system.StartGameController;
import model.DataBase;
import view.game_system.StartGameMenu;
import view.user_system.MenuSwitcher;

public class Main {
    public static void main(String[] args) {
        try {
            DataBase.loadApp();
            MenuSwitcher.run();
        } catch (Exception ex) {
            System.out.println("bug");
        }
    }

}


