package model.game_stuff;

import model.game_stuff.people.enums.TroopTypes;

import java.util.ArrayList;
import java.util.Random;

public class Troop extends Person{
    protected int damage;
    protected TroopTypes type;
    public Troop(Government owner, TroopTypes type) {
        super(owner);
        this.type = type;
        damage = type.getDamage();
    }
    public void hit(HasHp livingBeing) {
        livingBeing.getDamaged(damage);
    }
    public boolean attack() {
        Block target = null;
        Random random = new Random();
        for(int i = 1; i <= type.getFightingRange(); i++) {
            ArrayList<Block> blocks = findEnemyTroopsPosition(i);
            if (!blocks.isEmpty()) {
                target = blocks.get(random.nextInt(blocks.size()));
                ArrayList<Person> enemies = new ArrayList<>();
                for (Person person : target.getPeople()) {
                    if(person instanceof Troop)
                        enemies.add(person);
                }
                hit(enemies.get(random.nextInt(enemies.size())));
                break;
            } else if (!(blocks = findEnemyPeoplePositions(i)).isEmpty()) {
                target = blocks.get(random.nextInt(blocks.size()));
                hit(target.getPeople().get(random.nextInt(target.getPeople().size())));
                break;
            } else if (!(blocks = findEnemyBuildingsPosition(i)).isEmpty()) {
                target = blocks.get(random.nextInt(blocks.size()));
                hit(target.getBuilding());
                break;
            }
        }
        if(target == null) {
            return false;
        }
        return true;
    }
    protected ArrayList<Block> findEnemyPeoplePositions(int distance) {
        ArrayList<Block> enemyBlocks = new ArrayList<>();
        ArrayList<Block> neighbours = owner.getGame().getMap().getNeighboursWithDistance(position, distance);
        for (Block block : neighbours) {
            if(block.containsEnemyPerson(owner.getColor())) {
                enemyBlocks.add(block);
            }
        }
        return enemyBlocks;
    }
    protected ArrayList<Block> findEnemyTroopsPosition(int distance){
        ArrayList<Block> enemyBlocks = new ArrayList<>();
        ArrayList<Block> neighbours = owner.getGame().getMap().getNeighboursWithDistance(position, distance);
        for (Block block : neighbours) {
            if(block.containsEnemyTroop(owner.getColor())) {
                enemyBlocks.add(block);
            }
        }
        return enemyBlocks;
    }
    protected ArrayList<Block> findEnemyBuildingsPosition(int distance) {
        ArrayList<Block> enemyBlocks = new ArrayList<>();
        ArrayList<Block> neighbours = owner.getGame().getMap().getNeighboursWithDistance(position, distance);
        for (Block block : neighbours) {
            if(block.containsEnemyBuilding(owner.getColor())) {
                enemyBlocks.add(block);
            }
        }
        return enemyBlocks;
    }
}
