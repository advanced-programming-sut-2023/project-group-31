
import model.DataBase;
import model.game_stuff.people.Assassins;
import view.user_system.MenuSwitcher;

public class Main {
    public static void main(String[] args) {
        DataBase.loadApp();
        MenuSwitcher.run();

    }

}


