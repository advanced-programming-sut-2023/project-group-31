package model.game_stuff;

import java.util.ArrayList;

public abstract class Building implements HasHp{
    protected int hp;
    protected int maxHp;
    protected Government owner;
    protected String name;
    private boolean isUnderAttack;
    protected ArrayList<Block> blocks;
    protected String imagePath;

    {
        blocks = new ArrayList<>();
    }
    public Building(Government government) {
        this.owner = government;
    }
    public void getRepaired() {
        hp = maxHp;
    }
    public double getHpLost() {
        return (double)(maxHp - hp) / ((double) maxHp);
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public void getDamaged(int damage){
        this.hp -= damage;
        if(hp < 0) {
            terminate();
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public abstract String toString();

    public Government getOwner() {
        return owner;
    }

    public Block getPosition() {
        return blocks.get(0);
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void terminate() {
        for (Block block : blocks) {
            block.setBuilding(null);
        }
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

    public void setUnderAttack(boolean underAttack) {
        isUnderAttack = underAttack;
    }

    public boolean isUnderAttack() {
        return isUnderAttack;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}
