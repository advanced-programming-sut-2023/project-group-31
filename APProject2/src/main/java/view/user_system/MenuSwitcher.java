package view.user_system;

import view.ViewUtils;
import view.game_system.StartGameMenu;
import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;

public class MenuSwitcher extends ViewUtils {
    public static void run() {
        scanner = new Scanner(System.in);
        MenuSwitcherMessages command = MenuSwitcherMessages.LOGIN;
        while (true) {
            switch (command) {
                case EXIT:
                    return;
                case LOGIN:
                    command = LoginMenu.run(scanner);
                    break;
                case REGISTER:
                    command = RegisterMenu.run();
                    break;
                case PROFILE:
                    command = ProfileMenu.run(scanner);
                    break;
                case MAIN:
                    command = MainMenu.run();
                    break;
                case START_GAME:
                    command = StartGameMenu.run();
                    break;
            }
        }
    }
}
