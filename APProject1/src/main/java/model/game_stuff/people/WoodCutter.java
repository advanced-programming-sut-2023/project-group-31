package model.game_stuff.people;

import model.game_stuff.Tree;
import model.game_stuff.Waiter;
import model.game_stuff.buildings.Producer;
import model.game_stuff.people.enums.WorkerStates;
import model.game_stuff.people.enums.WorkerTypes;

import java.util.ArrayList;

public class WoodCutter extends Worker{
    private Tree chosenTree;
    public WoodCutter(Producer workHouse) {
        super(workHouse, WorkerTypes.WOOD_CUTTER);
    }
    // tree ha bayad yek mark dashte bashan ya bayad ye list az afradi ke mikhan bekananeshoon dashte bashan
    private Tree findBestTree(ArrayList<Tree> trees) {
        Tree bestTree = null;
        int minimumDistance = 500;
        int distance;
        for (Tree tree : trees) {
            if (!tree.isMarked() && (distance = tree.getPosition().getDistanceTo(position)) < minimumDistance) {
                minimumDistance = distance;
                bestTree = tree;
            }
        }
        if( bestTree == null) {
            return null;
        }
        bestTree.setMark(true);
        return bestTree;
    }
    private void setBestTree() {
        chosenTree = findBestTree(owner.getGame().getMap().getTrees());
        if(chosenTree == null) {
            destination = null;
            moveDestination = null;
            return;
        }
        destination = chosenTree.getPosition();
        moveDestination = destination;
        changeRout();
    }

    @Override
    public void work() {
        switch (state) {
            case HEADING_BACK:
                if(!move()) {
                    if(position.equals(workHouse.getPosition())) {
                        setBestTree();
                        state = WorkerStates.SEARCHING_WOOD;
                    } else {
                        changeRout();
                        move();
                    }
                }
                break;
            case HEADING_STORAGE:
                if(destination == null) {
                    setAppropriateStorage();
                    return;
                } else {
                    if(!move()) {
                        if(position.equals(moveDestination)) {
                            deliver();
                        } else {
                            changeRout();
                            move();
                        }
                    }
                }
                break;
            case PRODUCING:
                if(!workHouse.isFull() && producingWaiter.isTheTurn()) {
                    produce();
                }
                break;
            case SEARCHING_WOOD:
                if(chosenTree != null) {
                    if(transportingWaiter.isTheTurn()) {
                        move(destination);
                        chosenTree.terminate();
                        state = WorkerStates.HEADING_BACK_WITH_WOOD;
                        destination = workHouse.getPosition();
                        transportingWaiter = new Waiter(destination.getDistanceTo(position) / type.getSpeed());
                    }
                    if(!move()) {
                        if(position.equals(moveDestination)) {
                            chosenTree.terminate();
                            state = WorkerStates.HEADING_BACK_WITH_WOOD;
                            destination = workHouse.getPosition();
                            moveDestination = destination;
                            changeRout();
                        } else {
                            changeRout();
                            move();
                        }
                    }
                } else {
                    setBestTree();
                    return;
                }
                break;
            case HEADING_BACK_WITH_WOOD:
                if(!move()) {
                    if(position.equals(workHouse.getPosition())) {
                        setBestTree();
                        state = WorkerStates.PRODUCING;
                    } else {
                        changeRout();
                        move();
                    }
                }
        }
    }
}
