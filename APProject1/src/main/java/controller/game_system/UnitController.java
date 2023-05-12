package controller.game_system;

import com.sun.net.httpserver.Authenticator;
import controller.ControllerUtils;
import model.game_stuff.Block;
import model.game_stuff.Person;
import model.game_stuff.Troop;
import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;
import view.game_system.messages.UnitMessages;

import java.util.ArrayList;

public class UnitController extends ControllerUtils {
    static private ArrayList<Troop> troops;

    public static UnitMessages selectUnit(int x, int y){
        troops=new ArrayList<Troop>();
        for(Person person:currentGame.getMap().getBlock(x,y).getPeople()){
            if(person instanceof Troop){
                troops.add((Troop)person);
            }
        }
        if(troops.size()==0){
            return UnitMessages.EMPTY_BLOCK;
        }
        return UnitMessages.SUCCESS;
    }

    private void routUnit(int x,int y, ArrayList<Direction> directions,int i,int j){
        int dirX,dirY;
        if(i>0){
            dirX=1;
        }else{
            dirX=-1;
        }
        if(j>0){
            dirY=1;
        }else{
            dirY=-1;
        }
        if(isPossibleToGo(currentGame.getMap().getBlock(x,y))){

        }

    }

    private boolean isPossibleToGo(Block block){
        if (!block.isPermeable() ||
                block.containsEnemyBuilding(currentPlayer.getColor()) ||
                block.containsEnemyPerson(currentPlayer.getColor())) {
                return false;
        }
        return true;
    }

    public static void setTroops(ArrayList<Troop> troops) {
        UnitController.troops = troops;
    }


}
