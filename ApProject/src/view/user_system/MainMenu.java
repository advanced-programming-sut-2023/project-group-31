package view.user_system;

import view.user_system.messages.menuSwitcherMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private static Scanner scanner;
    public static menuSwitcherMessages run(Scanner newscanner) {
        scanner=newscanner;
        //TODO:
        return null;
    }
    private static boolean startNewGame(Matcher matcher) {
        //TODO:
        return false;
    }

    private static boolean logout(Matcher matcher) {
        //TODO:
        return false;
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
