package view.game_system;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.game_stuff.Person;
import model.game_stuff.enums.Direction;
import model.game_stuff.enums.ImageItem;
import model.game_stuff.enums.ImageSubjects;

public class MoveAnimation extends Transition {
    private Person person;
    private Direction direction;
    private ImageItem imageItem;

    private static int maxTime = 20;
    private static int repeat = 3;
    private double limit;
    private double measure;

    private int counter = -1;
    private Image image;

    public MoveAnimation(Person person, Direction direction) {
        this.person = person;
        this.direction = direction;
        this.setCycleDuration(Duration.millis(((double) maxTime) / person.getSpeed()));

        switch (direction) {
            case RIGHT:
                imageItem = person.getImagePackage().getSubject(ImageSubjects.MOVE_RIGHT);
                break;
            case UP:
                imageItem = person.getImagePackage().getSubject(ImageSubjects.MOVE_UP);
                break;
            case LEFT:
                imageItem = person.getImagePackage().getSubject(ImageSubjects.MOVE_LEFT);
                break;
            default:
                imageItem = person.getImagePackage().getSubject(ImageSubjects.MOVE_DOWN);
                break;
        }
        measure = 1d / (imageItem.getCount() * repeat);
        limit = 0;
        try {
            image = imageItem.getImage(imageItem.getFrom());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TODO tahesh aksesh ro adi konim;
    @Override
    protected void interpolate(double v) {
        try {
            person.getNode().setX(person.getNode().getX() + direction.getX());
            person.getNode().setY(person.getNode().getY() + direction.getY());
            if (v > limit) {
                counter = (counter + 1) % imageItem.getCount();
                image = imageItem.getImage(imageItem.getFrom() + imageItem.getModule() * counter);
                person.getNode().setImage(image);
                limit += measure;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
