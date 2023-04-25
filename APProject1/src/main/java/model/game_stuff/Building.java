package model.game_stuff;

import java.util.ArrayList;

public abstract class Building {
    protected int hp;
    protected Government owner;
    protected ArrayList<Block> blocks;

    {
        blocks = new ArrayList<>();
    }
    public Building(Government government) {
        this.owner = government;
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
}
