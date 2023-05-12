package view.game_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartGameCommands {
    EXIT("exit"),
    SHOW_MAPS("show maps"),
    SHOW_PLAYERS("show players"),
    CHOOSE_MAP("select map -name (?<name>IN)"),
    SHOW_MAP("show map (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    SHOW_DETAILS("show details (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    MOVE_MAP("move map ((?<up>( up (?<upNumber>\\d+) ))|(?<down>( down (?<downNumber>\\d+) ))|(?<right>( right (?<rightNumber>\\d+) ))|(?<left>( left (?<leftNumber>\\d+) )))+"),
    SET_A_BLOCK_TEXTURE("set texture (( -x (?<x>\\d+) )|( -y (?<y>\\d+) )|( -type (?<type>IN))+"),
    SET_TEXTURE("set texture (( -x1 (?<x>\\d+) )|( -y1 (?<y>\\d+) )|( -x2 (?<x>\\d+) )|( -y2 (?<y>\\d+) )|( -type (?<type>IN))+"),
    CLEAR_BLOCK("clear block (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    DROP_TREE("drop tree (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    //DROP_LORD_HOUSE("drop lord house (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    SHOW_CHOSEN_PLAYERS("show chosen players"),
    ADD_PLAYER("add player (?<username>IN)"),
    REMOVE_PLAYER("remove player (?<username>IN)"),
    SET_PLAYERS_LORD_HOUSE("set lord house (?<lord_house>\\d) to (?<username>IN)"),
    SHOW_LORD_HOUSES_LEFT("show lord houses left"),
    SET_PLAYERS_TEAM("set team (?<team>IN) to (?<username>IN)"),
    START("start"),
    GOTO_MAP_MENU("goto map menu!");
    private String regex;
    StartGameCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
    }
    public static Matcher getMatcher(String input, StartGameCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}
