package model.game_stuff;

import model.game_stuff.people.enums.TroopTypes;

import java.util.ArrayList;
import java.util.Random;

public class Troop extends Person{
    protected int damage;
    protected TroopTypes type;
    protected Block attackTarget;
    public Troop(Government owner, TroopTypes type) {
        super(owner);
        this.type = type;
        damage = type.getDamage();
        attackTarget = null;
    }

    public void setAttackTarget(Block attackTarget) {
        this.attackTarget = attackTarget;
    }

    public void hit(HasHp livingBeing) {
        livingBeing.getDamaged(damage);
    }
    public boolean attack() {
        Random random = new Random();
        for(int i = 1; i <= type.getFightingRange(); i++) {
            ArrayList<Block> blocks = findEnemyTroopsPosition(i);
            if (!blocks.isEmpty()) {
                if(attackTarget == null || (!attackTarget.containsEnemyPerson(owner.getColor()) && !attackTarget.containsEnemyBuilding(owner.getColor()))) {
                    attackTarget = blocks.get(random.nextInt(blocks.size()));
                }
                ArrayList<Person> enemies = new ArrayList<>();
                for (Person person : attackTarget.getPeople()) {
                    if(person instanceof Troop)
                        enemies.add(person);
                }
                hit(enemies.get(random.nextInt(enemies.size())));
                break;
            } else if (!(blocks = findEnemyPeoplePositions(i)).isEmpty()) {
                attackTarget = blocks.get(random.nextInt(blocks.size()));
                hit(attackTarget.getPeople().get(random.nextInt(attackTarget.getPeople().size())));
                break;
            } else if (!(blocks = findEnemyBuildingsPosition(i)).isEmpty()) {
                attackTarget = blocks.get(random.nextInt(blocks.size()));
                hit(attackTarget.getBuilding());
                break;
            }
        }
        if(attackTarget == null) {
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

    @Override
    public String toString() {
        return type.getName() + ":" + (100 * hp / type.getHp());
    }
}
