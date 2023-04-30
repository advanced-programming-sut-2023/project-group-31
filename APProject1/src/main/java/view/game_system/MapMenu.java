package view.game_system;

import model.game_stuff.Map;
import model.game_stuff.enums.Textures;
import view.ViewUtils;
import view.game_system.commands.MapCommands;
import view.viewStyle.Colors;

import java.util.regex.Matcher;

public class MapMenu extends ViewUtils {
    private Map map;
    private int length;
    private int width;

    private int x;
    private int y;

    public MapMenu(Map map) {
        this.map = map;
        length = 14;
        width = 4;
    }

    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = MapCommands.getMatcher(input, MapCommands.SHOW_MAP)) != null) {
                x=Integer.parseInt(matcher.group("x"));
                y=Integer.parseInt(matcher.group("y"));
                showMapByXY();
            } else if ((matcher = MapCommands.getMatcher(input, MapCommands.MOVE)) != null) {
                moveOnMap();
            }
        }
    }

    private void moveOnMap() {

    }

    private void showMapByXY() {
        showLine();
        for (int i = y; i < y + width; i++) {
            for (int j = 0; j < 3; j++) {
                showRow(x,i);
            }
            showLine();
        }
    }

    private void showLine() {
        System.out.print("-");
        for (int i = 0; i < 6 * length;i++) {
            System.out.println("-");
        }
        System.out.print("-");
        System.out.println();
    }

    private void showRow(int x, int y) {
        String output;
        for (int i = x-(length/2); i < x +(length/2); i++) {
            System.out.println("|");
            if (map.getBlocks().get(i).get(y).getType().equals(Textures.GROUND)) {
                output = "#";
            } else {
                output = map.getBlocks().get(i).get(y).getType().getColor().getBackgroundColorCode()
                        + map.getBlocks().get(i).get(y).getType().getName().substring(0, 1)
                        + Colors.RESET.getBackgroundColorCode();
            }
            for (int j = 0; j < 6; j++) {
                System.out.println(output);
            }
        }
        System.out.println("|");
    }
}

