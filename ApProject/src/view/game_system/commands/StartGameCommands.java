package view.game_system.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartGameCommands {
    CHOOSE_MAP("select map -name (?<name>I)"),
    SHOW_MAP("show map (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    SHOW_DETAILS("show details (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    MOVE_MAP("move map ((?<up>( up (?<upNumber>\\d+) ))|(?<down>( down (?<downNumber>\\d+) ))|(?<right>( right (?<rightNumber>\\d+) ))|(?<left>( left (?<leftNumber>\\d+) )))+"),
    SET_A_BLOCK_TEXTURE("set texture (( -x (?<x>\\d+) )|( -y (?<y>\\d+) )|( -type (?<type>I))+"),
    SET_TEXTURE("set texture (( -x1 (?<x>\\d+) )|( -y1 (?<y>\\d+) )|( -x2 (?<x>\\d+) )|( -y2 (?<y>\\d+) )|( -type (?<type>I))+"),
    CLEAR_BLOCK("clear block (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    DROP_TREE("drop tree (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    DROP_LORD_HOUSE("drop lord house (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+")
    ;
    private String regex;
    StartGameCommands(String regex) {
        this.regex = editRegex(regex);
    }
    public static Matcher getMatcher(String input, StartGameCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    private String editRegex(String regex) {
        regex = regex.replaceAll("[\\s]+", "[\\s]+");
        regex = regex.replaceAll("I", "([\\S]*)|(\"[^*]*\")");
        return regex;
    }

    public String getRegex() {
        return regex;
    }
}
