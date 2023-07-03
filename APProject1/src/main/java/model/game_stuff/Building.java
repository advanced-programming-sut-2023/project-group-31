package model.game_stuff;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.game_stuff.types.Buildings;
import view.game_system.GameMainPage;

import java.net.URL;
import java.util.ArrayList;

public abstract class Building implements HasHp{
    protected Rectangle rectangle;
    protected int hp;
    protected Buildings baseBuildingType;
    protected Waiter fireWaiter;
    protected int maxHp;
    protected Government owner;
    protected String name;
    private boolean isUnderAttack;
    protected String imagePath;
    protected ArrayList<Rectangle> fireRectangles;
    protected ArrayList<Block> blocks;

    {
        blocks = new ArrayList<>();
        fireRectangles = new ArrayList<>();
    }
    public Building(Government government) {
        this.owner = government;
    }
    public void getRepaired() {
        hp = maxHp;
    }
    public int getHeight() {
        if(baseBuildingType == null) {
            return 2;
        }
        return baseBuildingType.getHeight();
    }

    public int getWidth() {
        if(baseBuildingType == null) {
            return 2;
        }
        return baseBuildingType.getWidth();
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setRectangle() {
        rectangle = makeRectangle(this);
    }
    public static Rectangle makeRectangle(Building building) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(building.getHeight());
        rectangle.setWidth(building.getWidth());
        rectangle.setX(building.getPosition().getX());
        rectangle.setY(building.getPosition().getY());
        System.out.println(Building.class.getResource("/" + building.getImagePath() + ".png"));
        rectangle.setFill(new ImagePattern(new Image(Building.class.getResource("/" + building.getImagePath() + ".png").toString())));
        GameMainPage.getMapPane().getChildren().add(rectangle);

        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(building.toString());
                alert.setHeaderText(building.name);
                //TODO ADD REPAIR BOTTOM
                //todo set special onMouseClick for menuBuildings
                alert.show();
            }
        });

        return rectangle;
    }

    public void getFired() {
        fireWaiter = new Waiter(3);
        if(fireRectangles.isEmpty()) {
            for (Block block : blocks) {
                Rectangle fireRectangle = new Rectangle(1, 1);
                fireRectangle.setFill(new ImagePattern(new Image("/Media/game/fire.png")));
                fireRectangle.setX(block.getX());
                fireRectangle.setY(block.getY());
                GameMainPage.getMapPane().getChildren().add(fireRectangle);
                fireRectangles.add(fireRectangle);
            }
        }
    }

    public void work() {
        if(fireWaiter != null) {
            if(fireWaiter.isTheTurn()) {
                fireWaiter = null;
                GameMainPage.getMapPane().getChildren().removeAll(fireRectangles);
                fireRectangles.clear();
            }else {
                getDamaged(500);
            }
        }
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
