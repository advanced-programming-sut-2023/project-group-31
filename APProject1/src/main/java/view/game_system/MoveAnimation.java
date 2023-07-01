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
            setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    person.notify();
                }
            });
        }
    }
    @Override
    protected void interpolate(double v) {
        synchronized (person) {
            try {
                person.getRectangle().setLayoutX(person.getRectangle().getLayoutX() + direction.getX());
                person.getRectangle().setLayoutY(person.getRectangle().getLayoutY() + direction.getY());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}