package view.game_system;

import view.ViewUtils;
import view.game_system.commands.TurnCommands;

import java.util.regex.Matcher;

public class TurnMenu extends ViewUtils {
    public static void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = TurnCommands.getMatcher(input, TurnCommands.DROP_BUILDING)) != null) {
                dropBuilding();
            }
        }
    }

    private static void dropBuilding() {

    }
}
