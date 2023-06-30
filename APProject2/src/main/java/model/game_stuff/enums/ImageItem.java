package model.game_stuff.enums;

import javafx.scene.image.Image;

public class ImageItem {
    private int from;
    private int module;
    private int count;
    private String imagePath;

    public ImageItem(int from, int module, int count) {
        this.from = from;
        this.module = module;
        this.count = count;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public Image getImage(int number) throws Exception {
        return new Image(imagePath + number + ".pnj");
    }
}
