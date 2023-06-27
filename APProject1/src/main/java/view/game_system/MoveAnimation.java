package view.game_system;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.game_stuff.Block;
import model.game_stuff.enums.Direction;
import model.game_stuff.types.PersonType;

public class MoveAnimation extends Transition {
    private static int maxTime = 15000;
    private Node node;
    private Direction direction;
    public MoveAnimation(Block block, PersonType personType, Integer number, Direction direction) {
        this.direction = direction;
        ImageView imageView = new ImageView(new Image(Block.class.getResource("/" + block.getImagePathOfAPersonType(personType)).toString(), 1d/5, 1d/5,false,false));
        Text text = new Text(number.toString());
        node = new Group(imageView, text);
        node.setLayoutX(block.getX());
        node.setLayoutY(block.getY());
        GameMainPage.getMapPane().getChildren().add(node);
        setCycleDuration(Duration.millis(maxTime));
        //TODO EZAFE KARDAN FACTOR E SORAT
    }
    @Override
    protected void interpolate(double v) {
        node.setLayoutX(direction.getX());
        node.setLayoutY(direction.getY());
    }
}