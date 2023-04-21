package view.user_system;

import view.ViewUtils;
import view.user_system.messages.menuSwitcherMessages;

import java.util.Scanner;

public class MenuSwitcher extends ViewUtils {
    public static void run() {
        scanner = new Scanner(System.in);
        menuSwitcherMessages command = menuSwitcherMessages.LOGIN;
        while (true) {
            switch (command) {
                case EXIT:
                    return;
                case LOGIN:
                    command = LoginMenu.run(scanner);
                    break;
                case REGISTER:
                    command = RegisterMenu.run(scanner);
                    break;
                case PROFILE:
                    command = ProfileMenu.run(scanner);
                    break;
                // TODO :
            }
        }
    }
}
