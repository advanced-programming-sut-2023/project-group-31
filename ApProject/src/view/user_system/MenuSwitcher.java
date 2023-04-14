package view.user_system;

import view.user_system.messages.MenuSwitcherMessages;

import java.util.Scanner;

public class MenuSwitcher {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        MenuSwitcherMessages command = MenuSwitcherMessages.LOGIN;
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
                    command = ProfieMenu.run(scanner);
                    break;
                // TODO :
            }
        }
    }
}
