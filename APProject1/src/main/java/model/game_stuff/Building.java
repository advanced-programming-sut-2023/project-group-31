package model.game_stuff;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.game_system.GameMainPage;

import java.util.ArrayList;

public abstract class Building implements HasHp{
    protected Rectangle rectangle;
    protected int hp;
    protected int maxHp;
    protected Government owner;
    protected String name;
    private boolean isUnderAttack;
    protected String imagePath;
    protected ArrayList<Block> blocks;

    {
        blocks = new ArrayList<>();
    }
    public Building(Government government) {
        this.owner = government;
    }
    public void getRepaired() {
        hp = maxHp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setRectangle() {
        rectangle = new Rectangle();
        rectangle.setHeight(1);
        rectangle.setWidth(1);
        rectangle.setX(this.getPosition().getX());
        rectangle.setY(this.getPosition().getY());
        rectangle.setFill(new ImagePattern(new Image(this.getImagePath() + ".png")));
        GameMainPage.getRoot().getChildren().add(rectangle);

        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(toString());
                alert.setHeaderText(name);
                //TODO ADD REPAIR BOTTOM
                //todo set special onMouseClick for menuBuildings
                alert.show();
            }
        });
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public double getHpLost() {
        return (double)(maxHp - hp) / ((double) maxHp);
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public void getDamaged(int damage){
        this.hp -= damage;
        if(hp < 0) {
            terminate();
        }
    }

    public abstract String toString();

    public Government getOwner() {
        return owner;
    }

    public Block getPosition() {
        return blocks.get(0);
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void terminate() {
        for (Block block : blocks) {
            block.setBuilding(null);
        }
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

    public void setUnderAttack(boolean underAttack) {
        isUnderAttack = underAttack;
    }

    public boolean isUnderAttack() {
        return isUnderAttack;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}
