package model.game_stuff.enums;

import javafx.scene.image.Image;

public class ImageItem {
    private int from;
    private int module;
    private int count;

    public ImageItem(int from, int module, int count) {
        this.from = from;
        this.module = module;
        this.count = count;
    }

    public int getFrom() {
        return from;
    }

    public int getModule() {
        return module;
    }

    public int getCount() {
        return count;
    }
}
