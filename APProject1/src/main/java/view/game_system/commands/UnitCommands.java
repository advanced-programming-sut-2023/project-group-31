package view.game_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UnitCommands {
    MOVE("move to (?<move_order>IN)"),
    PATROL("patrol in order (?<move_order>IN)"),
    SET_STATE("set state to (?<state>IN)"),
    ATTACK("attack (( -x (?<x>\\d+) )|( -y (?<y>\\d+) ))+"),
    DIG_TUNNEL("dig tunnel"),
    ;
    private String regex;

    UnitCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, TurnCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
