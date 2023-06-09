package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Block;
import model.game_stuff.Person;
import model.game_stuff.people.Troop;
import model.game_stuff.enums.Direction;
import model.game_stuff.people.Tunneler;
import model.game_stuff.people.enums.TroopState;
import view.game_system.messages.UnitMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class UnitController extends ControllerUtils {
    static private ArrayList<Troop> troops;
    private static HashMap<String, Integer> numberOfEachTroop;

    static {
        troops = new ArrayList<>();
        numberOfEachTroop = new HashMap<>();
    }

    public static UnitMessages setState(String state) {
        //System.out.println(troops.size());
        switch (state) {
            case "aggressive":
                for (Troop troop : troops) {
                    troop.setState(TroopState.AGGRESSIVE);
                }
                break;
            case "defensive":
                for (Troop troop : troops) {
                    troop.setState(TroopState.DEFENCIVE);
                }
                break;
        }
        return UnitMessages.SUCCESS;
    }

    public static UnitMessages setMoveOrder(String moveOrderString) {
        LinkedList<Direction> moveOrder = stringToMoveOrder(moveOrderString);
        for (Troop troop : troops) {
            troop.setMoveOrder(moveOrder);
        }
        return UnitMessages.SUCCESS;
    }

    private static LinkedList<Direction> stringToMoveOrder(String moveOrderString) {
        LinkedList<Direction> moveOrder = new LinkedList<>();
        char singleMove;
        for(int i = 0; i < moveOrderString.length(); i++) {
            singleMove = moveOrderString.charAt(i);
            switch (singleMove) {
                case 'U' :
                    moveOrder.add(Direction.UP);
                    break;
                case 'R' :
                    moveOrder.add(Direction.RIGHT);
                    break;
                case 'D' :
                    moveOrder.add(Direction.DOWN);
                    break;
                case 'L' :
                    moveOrder.add(Direction.LEFT);
                    break;
            }
        }
        return moveOrder;
    }

    public static UnitMessages selectSpecialTroops(String troopTypeString) {
        //System.out.println(troops.size());
        boolean hasSpecial=false;
        for (Troop troop : troops) {
          //  System.out.println(troop.getName());
            troop.getName().equals(troopTypeString);
            hasSpecial=true;
        }
        if (hasSpecial==false) {
            return UnitMessages.NO_SUCH_TROOP;
        }
        for (int i = 0; i < troops.size(); i++) {
            if (!troops.get(i).getName().equals(troopTypeString)) {
                troops.remove(troops.get(i));
                i--;
            }
        }
        HashMap<String, Integer> newNumber = new HashMap<>();
        newNumber.put(troopTypeString, numberOfEachTroop.get(troopTypeString));
        numberOfEachTroop = newNumber;
        return UnitMessages.SUCCESS;
    }

    public static UnitMessages setAttackTarget() {
        if(!inputs.containsKey("x") || !inputs.containsKey("y")) {
            return UnitMessages.INVALID_COMMAND;
        }
        int x = Integer.parseInt(inputs.get("x").trim());
        int y = Integer.parseInt(inputs.get("y").trim());
        if(!currentMap.isInMap(x, y)) {
            return UnitMessages.COORDINATE_OUT_OF_BOUND;
        }
        Block target = currentMap.getBlock(x, y);
        if (!target.containsEnemyBuilding(currentPlayer.getColor()) && !target.containsEnemyPerson(currentPlayer.getColor())) {
            return UnitMessages.NOTHING_TO_ATTACK_TO;
        }
        move(x, y);
        for (Troop troop : troops) {
            troop.setAttackPurpose(target);
        }
        return UnitMessages.SUCCESS;
    }

    public static UnitMessages digTunnel(String directionString) {
        Direction direction = Direction.getDirectionByName(directionString);
        if(direction == null) {
            return UnitMessages.INVALID_DIRECTION;
        }
        for (Troop troop : troops) {
            if(troop instanceof Tunneler) {
                ((Tunneler) troop).digTunnel(direction);
            }
        }
        return UnitMessages.SUCCESS;
    }

    public static UnitMessages fillTunnel(String directionString) {
        Direction direction = Direction.getDirectionByName(directionString);
        if(direction == null) {
            return UnitMessages.INVALID_DIRECTION;
        }
        for (Troop troop : troops) {
            if(troop instanceof Tunneler) {
                ((Tunneler) troop).fillTunnel(direction);
            }
        }
        return UnitMessages.SUCCESS;
    }

    public static UnitMessages setDestination(int destinationX, int destinationY) {
        if(!currentMap.isInMap(destinationX, destinationY)) {
            return UnitMessages.COORDINATE_OUT_OF_BOUND;
        }
        move(destinationX, destinationY);
        return UnitMessages.SUCCESS;
    }

    private static void move(int destinationX, int destinationY) {
        ArrayList<Direction> moveOrderArrayList = new ArrayList<>();
        ArrayList<Block> blocksContainingUnit = new ArrayList<>();
        for (Troop troop : troops) {
            if(!blocksContainingUnit.contains(troop.getPosition())) {
                blocksContainingUnit.add(troop.getPosition());
                if(moveOrderArrayList != null) {
                    moveOrderArrayList.clear();
                }
                moveOrderArrayList = routUnit(troop.getPosition().getX(), troop.getPosition().getY(),moveOrderArrayList,
                    destinationX - troop.getPosition().getX(),
                    destinationY - troop.getPosition().getY());
                if(moveOrderArrayList == null) {
                    continue;
                }
                LinkedList<Direction> moveOrder = new LinkedList<>(moveOrderArrayList);
                for (Person person : troop.getPosition().getPeople()) {
                    if((person instanceof Troop) ) {
                        ((Troop) person).setMoveOrder(moveOrder);
                    }
                }
            }
        }
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
        if(numberOfEachTroop.containsKey(troop.getName())) {
            numberOfEachTroop.replace(troop.getName() , numberOfEachTroop.get(troop.getName()) + 1);
            return;
        }
        numberOfEachTroop.put(troop.getName(), 1);
    }

    public void removeTroop(Troop troop) {
        troops.remove(troop);
        numberOfEachTroop.replace(troop.getName() , numberOfEachTroop.get(troop.getName()) -1);
        if(numberOfEachTroop.get(troop.getName()) == 0) {
            numberOfEachTroop.remove(troop.getName());
        }
    }

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

    private static ArrayList<Direction> routUnit(int thisX, int thisY, ArrayList<Direction> directions, int xDistanceTo, int yDistance) {
        if(xDistanceTo==0&&yDistance==0){
            return directions;
        }
        int dirX = 0, dirY = 0;
        if (xDistanceTo > 0) {
            dirX = 1;
        } else if (xDistanceTo < 0) {
            dirX = -1;
        }
        if (yDistance > 0) {
            dirY = 1;
        } else if (yDistance < 0) {
            dirY = -1;
        }
        if (dirX != 0 && isPossibleToGo(currentGame.getMap().getBlock(thisX+dirX, thisY))) {
            directions.add(Direction.getDirectionByXY(dirX,0));
            return routUnit( thisX+dirX,  thisY, directions, xDistanceTo-dirX, yDistance);
        }
        if (dirY != 0 && isPossibleToGo(currentGame.getMap().getBlock(thisX, thisY+dirY))) {
            directions.add(Direction.getDirectionByXY(0,dirY));
            return routUnit( thisX,  thisY+dirY, directions, xDistanceTo, yDistance-dirY);
        }
        if(dirX==0){
            dirX=-1;
        }
        if(dirY==0){
            dirY=-1;
        }
        if ( isPossibleToGo(currentGame.getMap().getBlock(thisX-dirX, thisY))) {
            directions.add(Direction.getDirectionByXY(-dirX,0));
            return routUnit( thisX-dirX,  thisY, directions, xDistanceTo+dirX, yDistance);
        }
        if ( isPossibleToGo(currentGame.getMap().getBlock(thisX, thisY-dirY))) {
            directions.add(Direction.getDirectionByXY(0,-dirY));
            return routUnit( thisX,  thisY+dirY, directions, xDistanceTo, yDistance+dirY);
        }
        return null;

    }

    private static boolean isPossibleToGo(Block block) {
        return block.isPermeable() &&
            block.getBuilding() == null &&
            !block.containsEnemyPerson(currentPlayer.getColor());
    }

    public static UnitMessages moveTroop(int x,int y){
        if(troops==null||troops.size()==0){
            return UnitMessages.NOTHING_SELECTED;
        }
        ArrayList<Direction> directions=new ArrayList<>();
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
        int j=y1-troops.get(0).getPosition().getY();
        routUnit(troops.get(0).getPosition().getX(),troops.get(0).getPosition().getY(),directions,i,j);

        return UnitMessages.SUCCESS;
    }

    public static void setTroops(ArrayList<Troop> troops) {
        UnitController.troops = troops;
    }


}
