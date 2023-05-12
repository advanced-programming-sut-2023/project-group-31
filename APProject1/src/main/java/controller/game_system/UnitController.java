package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Person;
import model.game_stuff.Troop;

import java.util.ArrayList;

public class UnitController extends ControllerUtils {
    static private ArrayList<Troop> troops;

    public static UnitMessages selectUnit(int x,int y){
        troops=new ArrayList<Troop>();
        for(Person person:currentGame.getMap().getBlock(x,y).getPeople()){
            if(person instanceof Troop){
                troops.add((Troop)person);
            }
        }
        if(troops.size()==0){
            return U;
        }
    }
}
