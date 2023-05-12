package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Troop;

import java.util.ArrayList;

public class UnitController extends ControllerUtils {
    static private ArrayList<Troop> troops;

    public static void setTroops(ArrayList<Troop> troops) {
        UnitController.troops = troops;
    }


}
