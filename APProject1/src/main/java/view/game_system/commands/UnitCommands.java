package view.game_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UnitCommands {
    RIGHT_CLICK("right click"),
    SET_MOVE_ORDER("set move order to (?<move_order>[URDL]+)"),
    MOVE("move to(( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    PATROL("patrol in order (?<move_order>IN)"),
    SET_STATE("set state to (?<state>IN)"),
    ATTACK("attack (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    DIG_TUNNEL("dig tunnel (?<direction>IN)"),
    FILL_TUNNEL("fill tunnel (?<direction>IN)"),
    SELECT_SPECIAL_TROOPS("select (?<troop_type>IN)"),
    ;
    private String regex;

    UnitCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, UnitCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
