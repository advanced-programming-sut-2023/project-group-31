package view.game_system.commands;

import model.DataBase;
import model.game_stuff.enums.Textures;
import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TurnCommands {
    EXIT("exit"),
    DROP_BUILDING("drop building(( -x (?<x>\\d+))|( -y (?<y>\\d+))|( -type (?<type>IN)))+"),
    REPAIR("repair building at(( -x (?<x>\\d+))|( -y (?<y>\\d+)))+"),
    SELECT_BUILDING("select building(( -x (?<x>\\d+))|( -y (?<y>\\d+)))+"),
    SELECT_UNIT("user login -u username -p 123456Aa$ --stay-login-menu"),
    TRADE_MENU("trade menu"),
    GOVERNMENT_MENU("go to government menu"),
    NEXT_TURN("next turn"),
    SHOW_MAP("show map(( -x (?<x>[\\d]+))|( -y (?<y>[\\d]+)))+"),
    MOVE_MAP("map up(?<directions>(( (left|right|up|down))|( (left|right|up|down)))+)"),
    SHOW_DETAILS("show details(( -x (?<x>[\\d]+))|( -y (?<y>[\\d]+)))+"),
    SELECT_MULTIPLE_UNITS("select unit(( -x1 (?<x1>\\d+))|( -y1 (?<y1>\\d+))|( -x2 (?<x2>\\d+))|( -y2 (?<y2>\\d+)))+"),
    ;
    String regex;
    TurnCommands(String regex){
        this.regex= ViewUtils.editRegex(regex);
    }

    public String getRegex() {
        return regex;
    }

    public static Matcher getMatcher(String input, TurnCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
