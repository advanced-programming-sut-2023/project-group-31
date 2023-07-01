package model.game_stuff;

import controller.game_system.UnitController;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.game_stuff.buildings.GateHouse;
import model.game_stuff.buildings.Tower;
import model.game_stuff.enums.Direction;
import model.game_stuff.types.PersonType;
import view.game_system.GameMainPage;
import view.game_system.MoveAnimation;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Person implements HasHp{
    protected Government owner;
    protected String name;
    protected PersonType personType;
    protected int hp;
    protected Rectangle rectangle;
    protected int speed;
    protected Block position;
    protected String imagePath;
    protected Block moveDestination;
    protected boolean isMoving;
    //TODO: set image packages for people
    protected LinkedList<Direction> moveOrder;

    {
        moveOrder = new LinkedList<>();
        isMoving = false;
    }

    public Person(Government owner) {
        this.owner = owner;
    }

    public Block getMoveDestination() {
        return moveDestination;
    }

    public void setMoveDestination(Block moveDestination) {
        this.moveDestination = moveDestination;
    }

    public void setMoveOrder(LinkedList<Direction> moveOrder) {
        this.moveOrder = new LinkedList<>(moveOrder);
        if(!moveOrder.isEmpty()) {
            isMoving = true;
        }
    }

    public void setRectangle() {
        rectangle = new Rectangle();
        rectangle.setHeight(1);
        rectangle.setWidth(1);
        rectangle.setX(this.getPosition().getX());
        rectangle.setY(this.getPosition().getY());;
        System.out.println(Person.class.getResource("/" + this.getImagePath() + ".png"));
        rectangle.setFill(new ImagePattern(new Image(Person.class.getResource("/" + this.getImagePath() + ".png").toString(),1,1, false,false )));
        System.out.println(this.getImagePath() + ".png");
        rectangle.setFill(new ImagePattern(new Image(this.getImagePath() + ".png")));
        GameMainPage.getMapPane().getChildren().add(rectangle);
        Tooltip tooltip = new Tooltip(toString());
        Tooltip.install(rectangle, tooltip);
    }

    public LinkedList<Direction> getMoveOrder() {
        return moveOrder;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean move() {
        for(int i = 0; i < speed; i++) {
            if (moveOrder.isEmpty()) {
                moveDestination = null;
                isMoving = false;
                return false;
            }
            Block target;
            if ((target = owner.getGame().getMap().getNeighbour(moveOrder.getFirst(), position)) == null) {
                moveOrder.clear();
                moveDestination = null;
                isMoving = false;
                return false;
            }
            if(target.getPerson() != null) {
                if ((target.getPerson().getOwner().getColor().equals(this.getOwner().getColor())) && !target.getPerson().isMoving) {
                    moveOrder.clear();
                    moveDestination = null;
                    isMoving = false;
                }
                return false;
            }
            if (!target.isPermeable()) {
                moveDestination = null;
                moveOrder.clear();
                isMoving = false;
                return false;
            }
            if (target.containsEnemyBuilding(owner.getColor()) || target.containsEnemyPerson(owner.getColor())) {
                return false;
            }
            if (target.getBuilding() != null) {
                if (target.getBuilding().getOwner().equals(owner) && target.getBuilding() != position.getBuilding()) {
                    if (target.getBuilding() instanceof Tower) {
                        Tower tower = (Tower) target.getBuilding();
                        if (!tower.canEnter(moveOrder.getFirst()) || !tower.isFull()) {
                            moveOrder.clear();
                            isMoving = false;
                            moveDestination = null;
                            return false;
                        }
                    }
                    if (target.getBuilding() instanceof GateHouse) {
                        if (!((GateHouse)target.getBuilding()).isOpen()) {
                            return false;
                        }
                    }
                    moveOrder.clear();
                    isMoving = false;
                    moveDestination = null;
                    return false;
                }
            }
            move(target);
            moveOrder.removeFirst();
            if(moveOrder.isEmpty()) {
                isMoving = false;
                moveDestination = null;
            }
            new MoveAnimation(this, moveOrder.getFirst()).play();
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    protected void changeRout() {
        ArrayList<Direction> moveOrderArrayList = new ArrayList<>();
        UnitController.routUnit(position.getX(), position.getY(), moveOrderArrayList, moveDestination.getX() - position.getX(),
            moveDestination.getY() - position.getY());
        moveOrder = new LinkedList<>(moveOrderArrayList);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void getDamaged(int damage) {
        this.hp -= damage;
        if(hp < 0) {
            die();
        }
    }

    public int getHp() {
        return hp;
    }

    public Government getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Block getPosition() {
        return position;
    }

    public void setPosition(Block position) {
        this.position = position;
    }
    public void move(Block destination) {
        position.setPerson(null);
        destination.setPerson(this);
        position = destination;
    }
    public abstract void work();
    abstract public String toString();

    public abstract void die();
}
