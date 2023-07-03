package view.game_system;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.game_stuff.Person;
import model.game_stuff.enums.Direction;

public class MoveAnimation extends Transition {
    private final Person person;
    private Direction direction;

    private static int maxTime = 120;

    public MoveAnimation(Person person, Direction direction) {
        synchronized (person) {
            this.person = person;
            this.direction = direction;
            this.setCycleDuration(Duration.millis(((double) maxTime) / person.getSpeed()));
        }
    }
    @Override
    protected void interpolate(double v) {
        try {
            person.getRectangle().setX(person.getRectangle().getX() + direction.getX());
            person.getRectangle().setY(person.getRectangle().getY() + direction.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}