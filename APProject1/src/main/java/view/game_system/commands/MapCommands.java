package view.game_system.commands;

import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapCommands {
    SHOW_MAP("show map(( -x (?<x>[\\d]+))|( -y (?<y>[\\d]+)))+"),
    NEXT_TURN("next turn"),
    MOVE("map up(?<directions>(( (left|right|up|down))|( (left|right|up|down)))+)"),
    SHOW_DETAILS("show details(( -x (?<x>[\\d]+))|( -y (?<y>[\\d]+)))+");
    String regex;

    private MapCommands(String regex) {
        this.regex = ViewUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, MapCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
