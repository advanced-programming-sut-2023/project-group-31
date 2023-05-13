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

    public static UnitMessages selectUnit(int x, int y) {
        troops = new ArrayList<Troop>();
        for (Person person : currentGame.getMap().getBlock(x, y).getPeople()) {
            if (person instanceof Troop) {
                troops.add((Troop) person);
            }
        }
        if (troops.size() == 0) {
            return UnitMessages.EMPTY_BLOCK;
        }
        return UnitMessages.SUCCESS;
    }

    private static ArrayList<Direction> routUnit(int x, int y, ArrayList<Direction> directions, int i, int j) {
        if(i==0&&j==0){
            return directions;
        }
        int dirX = 0, dirY = 0;
        if (i > 0) {
            dirX = 1;
        } else if (i < 0) {
            dirX = -1;
        }
        if (j > 0) {
            dirY = 1;
        } else if (j < 0) {
            dirY = -1;
        }
        if (dirX != 0 && isPossibleToGo(currentGame.getMap().getBlock(x+dirX, y))) {
            directions.add(Direction.getDirectionByXY(dirX,0));
            return routUnit( x+dirX,  y, directions, i-dirX, j);
        }
        if (dirY != 0 && isPossibleToGo(currentGame.getMap().getBlock(x, y+dirY))) {
            directions.add(Direction.getDirectionByXY(0,dirY));
            return routUnit( x,  y+dirY, directions, i, j-dirY);
        }
        if(dirX==0){
            dirX=-1;
        }
        if(dirY==0){
            dirY=-1;
        }
        if ( isPossibleToGo(currentGame.getMap().getBlock(x-dirX, y))) {
            directions.add(Direction.getDirectionByXY(-dirX,0));
            return routUnit( x-dirX,  y, directions, i+dirX, j);
        }
        if ( isPossibleToGo(currentGame.getMap().getBlock(x, y-dirY))) {
            directions.add(Direction.getDirectionByXY(0,-dirY));
            return routUnit( x,  y+dirY, directions, i, j+dirY);
        }
        return null;

    }

    private static boolean isPossibleToGo(Block block) {
        if (!block.isPermeable() ||
                block.containsEnemyBuilding(currentPlayer.getColor()) ||
                block.containsEnemyPerson(currentPlayer.getColor())) {
            return false;
        }
        return true;
    }

    public static UnitMessages moveTroop(int x,int y){
        if(troops==null||troops.size()==0){
            return UnitMessages.NOTHING_SELECTED;
        }
        ArrayList<Direction> directions;
        int i=x-troops.get(0).getPosition().getX();
        int j=y-troops.get(0).getPosition().getY();
        routUnit(troops.get(0).getPosition().getX(),troops.get(0).getPosition().getY(),directions,i,j);

        return UnitMessages.SUCCESS;
    }

    public static UnitMessages patrolUnit(int x1,int y1,int x2,int y2){
        if(troops==null||troops.size()==0){
            return UnitMessages.NOTHING_SELECTED;
        }
        ArrayList<Direction> directions=new ArrayList<>();
        int i=x1-troops.get(0).getPosition().getX();
        int j=y-troops.get(0).getPosition().getY();
        routUnit(troops.get(0).getPosition().getX(),troops.get(0).getPosition().getY(),directions,i,j);

        return UnitMessages.SUCCESS;
    }

    public static void setTroops(ArrayList<Troop> troops) {
        UnitController.troops = troops;
    }


}
