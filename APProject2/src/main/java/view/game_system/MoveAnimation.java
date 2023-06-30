package view.game_system;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.game_stuff.Person;
import model.game_stuff.enums.Direction;

public class MoveAnimation extends Transition {
    private Person person;
    private Direction direction;

    private static int maxTime = 20;

    public MoveAnimation(Person person, Direction direction) {
        this.person = person;
        this.direction = direction;
        this.setCycleDuration(Duration.millis(((double) maxTime) / person.getSpeed()));
    }
    @Override
    protected void interpolate(double v) {
        try {
            person.getNode().setLayoutX(person.getNode().getLayoutX() + direction.getX());
            person.getNode().setLayoutY(person.getNode().getLayoutY() + direction.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}