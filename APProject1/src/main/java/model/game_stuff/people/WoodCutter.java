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
        return bestTree;
    }
    private void setBestTree() {
        chosenTree = findBestTree(owner.getGame().getMap().getTrees());
        if(chosenTree == null) {
            destination = null;
            return;
        }
        destination = chosenTree.getPosition();
        transportingWaiter = new Waiter(chosenTree.getPosition().getDistanceTo(position) / type.getSpeed());
    }

    @Override
    public void work() {
        switch (state) {
            case HEADING_BACK:
                if(transportingWaiter.isTheTurn()) {
                    state = WorkerStates.SEARCHING_WOOD;
                    move(workHouse.getPosition());
                    setBestTree();
                }
                break;
            case HEADING_STORAGE:
                if(destination != null && transportingWaiter.isTheTurn()) {
                    if(destination == null) {
                        setAppropriateStorage();
                        return;
                    }
                    move(destination);
                    deliver();
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
                } else {
                    setBestTree();
                    return;
                }
                break;
            case HEADING_BACK_WITH_WOOD:
                if(transportingWaiter.isTheTurn()) {
                    move(destination);
                    state = WorkerStates.PRODUCING;
                }
        }
    }
}
