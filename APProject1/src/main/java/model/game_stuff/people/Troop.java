package model.game_stuff.people;

import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.HasHp;
import model.game_stuff.Person;
import model.game_stuff.people.enums.TroopState;
import model.game_stuff.people.enums.TroopTypes;

import java.util.ArrayList;
import java.util.Random;

public class Troop extends Person {
    protected int damage;
    protected TroopTypes type;
    protected TroopState state;
    protected Block attackTarget;
    protected Block attackPurpose;

    {
        attackTarget = null;
        attackPurpose = null;
        state = TroopState.DEFENCIVE;
    }
    public Troop(Government owner, TroopTypes type) {
        super(owner);
        this.type = type;
        damage = type.getDamage();
        personType = type.getPersonType();
        name = type.getPersonType().getName();
        owner.getTroops().add(this);
        speed = type.getSpeed();
        imagePath = type.getImagePath();

    }

    public void setAttackPurpose(Block attackPurpose) {
        this.attackPurpose = attackPurpose;
    }

    public TroopState getState() {
        return state;
    }

    public void setState(TroopState state) {
        this.state = state;
    }

    public void setAttackTarget(Block attackTarget) {
        this.attackTarget = attackTarget;
    }

    public void hit(HasHp livingBeing) {
        livingBeing.getDamaged(damage);
    }
    public void work() {
        switch (state) {
            case AGGRESSIVE:
                if(!attack()) {
                    move();
                }
                break;
            case DEFENCIVE:
                if(!move()) {
                    attack();
                }
        }
    }
    public boolean attack() {
        Random random = new Random();
        if(attackPurpose != null && attackPurpose.getDistanceTo(position) <= type.getFightingRange()) {
            attackTarget = attackPurpose;
            attackPurpose = null;
        }
        if(attackTarget == null || (!attackTarget.containsEnemyPerson(owner.getColor()) && !attackTarget.containsEnemyBuilding(owner.getColor()))) {
            if(!findEnemyBlockToAttack()) {
                return false;
            }
        }
        if(attackTarget.containsEnemyTroop(owner.getColor())) {
            ArrayList<Person> enemyTroops = new ArrayList<>();
            for (Person person : attackTarget.getPeople()) {
                if(person instanceof Troop) {
                    enemyTroops.add(person);
                }
            }
            hit(enemyTroops.get(random.nextInt(enemyTroops.size())));
        } else if(attackTarget.containsEnemyPerson(owner.getColor())) {
            hit(attackTarget.getPeople().get(random.nextInt(attackTarget.getPeople().size())));
        } else if(attackTarget.containsEnemyBuilding(owner.getColor())){
            hit(attackTarget.getBuilding());
        }
        return true;
    }

    private boolean findEnemyBlockToAttack() {
        Random random = new Random();
        for(int i = 1; i <= type.getFightingRange(); i++) {
            ArrayList<Block> blocks = findEnemyTroopsPosition(i);
            if (!blocks.isEmpty()) {
                attackTarget = blocks.get(random.nextInt(blocks.size()));
                return true;
            } else if (!(blocks = findEnemyPeoplePositions(i)).isEmpty()) {
                attackTarget = blocks.get(random.nextInt(blocks.size()));
                return true;
            } else if (!(blocks = findEnemyBuildingsPosition(i)).isEmpty()) {
                attackTarget = blocks.get(random.nextInt(blocks.size()));
                return true;
            }
        }
        return false;
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
        return personType.getName() + ":" + (100 * hp / type.getHp());
    }

    public void die() {
        position.removePerson(this);
        owner.getTroops().remove(this);
    }
}
