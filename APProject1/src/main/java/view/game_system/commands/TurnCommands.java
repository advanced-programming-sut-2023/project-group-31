package view.game_system.commands;

import model.DataBase;
import model.game_stuff.enums.Textures;
import view.ViewUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TurnCommands {
    DROP_BUILDING("dropbuilding(( -x (?<x>[\\d]+))|( -y (?<y>[\\d]+))|( -type (?<type>IN)))+")
    ;
    String regex;
    TurnCommands(String regex){
        this.regex= ViewUtils.editRegex(regex);
    }

    public static Matcher getMatcher(String input, TurnCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
