package model.game_stuff;

import javafx.scene.image.Image;
import model.game_stuff.enums.ImageItem;
import model.game_stuff.enums.ImageSubjects;

import java.util.HashMap;

public class ImagePackage {
    private String imagePath;
    private int mainImageNumber;
    private HashMap<ImageSubjects, ImageItem> subjects;

    {
        subjects = new HashMap<>();
        mainImageNumber = -1;
    }

    public ImagePackage(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImagePackage(String imagePath, int mainImageNumber) {
        this.imagePath = imagePath;
        this.mainImageNumber = mainImageNumber;
    }

    public String getMainImagePath() {
        if(mainImageNumber == -1) {
            return imagePath + ".png";
        }
        return imagePath + mainImageNumber + ".png";
    }

    public void addSubject(ImageSubjects subject, ImageItem imageItem) {
        imageItem.setImagePath(imagePath);
        subjects.put(subject, imageItem);
    }

    public ImageItem getSubject(ImageSubjects subject) {
        return subjects.get(subject);
    }

    public Image getMainImage() throws Exception{
        return new Image(getMainImagePath());
    }

    public String getImagePath() {
        return imagePath;
    }
}
