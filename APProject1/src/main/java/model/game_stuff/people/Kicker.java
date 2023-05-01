package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.Person;
import model.game_stuff.enums.Direction;
import model.game_stuff.people.enums.KickerTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Kicker extends Person implements Fighter{
    private KickerTypes type;

    public Kicker(Government owner) {
        super(owner);
    }
    public void hit(Person person) {
        person.getDamaged(type.getDamage());
    }
    public void attack() {
        Block target = null;
        Random random = new Random();
        ArrayList<Block> blocks = findEnemyTroopsPosition();
        if(!blocks.isEmpty()) {
            target = blocks.get(random.nextInt(blocks.size()));
        } else if(!(blocks = findEnemyBuildingsPosition()).isEmpty()) {
            target = blocks.get(random.nextInt(blocks.size()));
        }

    }
    private ArrayList<Block> findEnemyTroopsPosition(){
        ArrayList<Block> enemyBlocks = new ArrayList<>();
        HashMap<Direction, Block> neighbours = owner.getGame().getMap().getNeighbours(position);
        for (Direction direction : neighbours.keySet()) {
            if(neighbours.get(direction).containsEnemyTroop(owner.getColor())) {
                enemyBlocks.add(neighbours.get(direction));
            }
        }
        return enemyBlocks;
    }
    private ArrayList<Block> findEnemyBuildingsPosition(){
        ArrayList<Block> enemyBlocks = new ArrayList<>();
        HashMap<Direction, Block> neighbours = owner.getGame().getMap().getNeighbours(position);
        for (Direction direction : neighbours.keySet()) {
            if(neighbours.get(direction).containsEnemyBuilding(owner.getColor())) {
                enemyBlocks.add(neighbours.get(direction));
            }
        }
        return enemyBlocks;
    }
}
