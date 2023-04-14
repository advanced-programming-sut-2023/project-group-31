package controller.game_menu;

import controller.ControllerUtils;
import model.game_stuff.Troop;
import model.game_stuff.types.Direction;
import view.user_system.messages.Messages;

public class UnitController extends KingdomController {

    public static Messages createUnit(int x,int y,String type){
        return null;
    }

    public static Messages dropUnit(int x,int y,String type,int count){
        return null;
    }
    public static Messages selectUnit(int x,int y){
        return null;
    }

    public static Messages moveUnitTo(int x,int y){
        return null;
    }

    public static Messages patrolUnit(int x,int y){
        return null;
    }

    public static Messages SetUnitSituation(int x,int y,int situation){
        return null;
    }

    public static Messages pourOil(String username, Direction direction){
        return null;
    }

    public static Messages attackEnemy(String enemyUsername){
        return null;
    }

    public static Messages attackUnit(Troop currentTroop, int x, int y){
        return null;
    }

    public static Messages attack(Troop currentTroop,int x,int y){
        return null;
    }

    public static Messages clear(int x,int y){
        return null;
    }
}
