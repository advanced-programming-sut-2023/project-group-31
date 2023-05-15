package model.game_stuff;

import model.game_stuff.enums.TreeTypes;

public class Tree {
    private Block position;
    private TreeTypes type;
    private boolean mark;

    public Tree(Block position, TreeTypes type) {
        this.position = position;
        this.type = type;
    }

    public TreeTypes getType() {
        return type;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isMarked() {
        return mark;
    }

    public Block getPosition() {
        return position;
    }

    public void terminate() {
        position.removeTree(this);
    }
}
