package model.game_stuff;

import model.game_stuff.enums.Textures;
import model.game_stuff.enums.TreeTypes;

import java.util.ArrayList;

public class Tree {
    private Block position;
    private TreeTypes type;
    private boolean mark;
    private final static ArrayList<Textures> notPossibleTextures;
    static {
        notPossibleTextures = new ArrayList<>();
        notPossibleTextures.add(Textures.CLIFF);
        notPossibleTextures.add(Textures.WATER);
        notPossibleTextures.add(Textures.IRON);
    }


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

    public static ArrayList<Textures> getNotPossibleTextures() {
        return notPossibleTextures;
    }
}
