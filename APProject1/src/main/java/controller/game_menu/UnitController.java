package controller.game_menu;

import controller.ControllerUtils;
import model.game_stuff.Troop;
import model.game_stuff.enums.Direction;
import view.user_system.messages.UserMessages;

public class UnitController extends ControllerUtils {

    public static UserMessages createUnit(int x, int y, String type){
        return null;
    }

    public static UserMessages dropUnit(int x, int y, String type, int count){
        return null;
    }
    public static UserMessages selectUnit(int x, int y){
        return null;
    }

    public static UserMessages moveUnitTo(int x, int y){
        return null;
    }

    public static UserMessages patrolUnit(int x, int y){
        return null;
    }

    public static UserMessages SetUnitSituation(int x, int y, int situation){
        return null;
    }

    public static UserMessages pourOil(String username, Direction direction){
        return null;
    }

    public static UserMessages attackEnemy(String enemyUsername){
        return null;
    }

    public static UserMessages attackUnit(Troop currentTroop, int x, int y){
        return null;
    }

    public static UserMessages attack(Troop currentTroop, int x, int y){
        return null;
    }

    public static UserMessages clear(int x, int y){
        return null;
    }
}
