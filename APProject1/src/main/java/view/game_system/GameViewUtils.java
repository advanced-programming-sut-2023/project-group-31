package view.game_system;

import controller.game_system.TurnController;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.game_stuff.Block;
import model.game_stuff.Map;
import model.game_stuff.enums.Textures;
import model.game_stuff.types.Buildings;
import view.game_system.messages.TurnMessages;

import java.util.ArrayList;
import java.util.HashMap;

public class GameViewUtils {

    private static HashMap<Textures, Paint> texturesPaints;
    private static int blockSize;

    private static Pane mapPane;
    
    static  {
        blockSize = 40;
        
        texturesPaints = new HashMap<>();
        texturesPaints.put(Textures.GROUND,
            new ImagePattern(new Image(GameViewUtils.class.getResource("/Media/Textures/ground.jpg").toString(),blockSize,blockSize,false,false)));
        texturesPaints.put(Textures.SAND, Color.GRAY);
        texturesPaints.put(Textures.QUARRY, Color.SNOW);
        texturesPaints.put(Textures.CLIFF,
            new ImagePattern(new Image(GameViewUtils.class.getResource("/Media/Textures/cliff.jpg").toString(),blockSize,blockSize,false,false)));
        texturesPaints.put(Textures.IRON, Color.DARKRED);
        texturesPaints.put(Textures.GROSS,
            new ImagePattern(new Image(GameViewUtils.class.getResource("/Media/Textures/gross.jpg").toString(),blockSize,blockSize,false,false)));
        texturesPaints.put(Textures.MEADOW, Color.GREENYELLOW);
        texturesPaints.put(Textures.GROSS_LAND, Color.GREEN);
        texturesPaints.put(Textures.WATER,
            new ImagePattern(new Image(GameViewUtils.class.getResource("/Media/Textures/water.jpg").toString(),blockSize,blockSize,false,false)));
        
    }
    
    
    public static Pane createMapPane(Map map) {
        mapPane = new Pane();
        mapPane.setScaleX(blockSize);
        mapPane.setScaleY(blockSize);
        int size = map.getSize();
        mapPane.setPrefWidth( size);
        mapPane.setPrefHeight(size);
        Rectangle rectangle;
        for (ArrayList<Block> rows : map.getBlocks()) {
            for (Block block : rows) {
                rectangle = new Rectangle(1,1);
                rectangle.setFill(texturesPaints.get(block.getType()));
                rectangle.setX(block.getX());
                rectangle.setY(block.getY());
                mapPane.getChildren().add(rectangle);
                Tooltip tooltip = new Tooltip(block.toString());
                Tooltip.install(rectangle,tooltip);
                block.setRectangle(rectangle);
                rectangle.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        System.out.println("on drag over");
                        System.out.println(dragEvent.getDragboard().getString());
                        if (dragEvent.getDragboard().getString().split("\\s")[0].equals("building")) {
                            dragEvent.acceptTransferModes(TransferMode.ANY);
                        }

                        dragEvent.consume();
                    }
                });
                // gir e graphic i
                Rectangle finalRectangle = rectangle;
                rectangle.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        System.out.println("on drag entered");
                        Buildings buildingType = Buildings.getBuildingByName(dragEvent.getDragboard().getString().replace("building ", ""));
                        if (dragEvent.getDragboard().getString().split("\\s")[0].equals("building")) {
                            if(block.getType().equals(Textures.WATER) || block.getType().equals(Textures.CLIFF) ||
                                (buildingType.getPossibleTexture() != null && buildingType.getPossibleTexture().equals(block.getType()))) {
                                finalRectangle.setFill(Color.INDIANRED);
                            } else {
                                finalRectangle.setFill(Color.SPRINGGREEN);
                            }
                        }
                        dragEvent.consume();
                    }
                });

                rectangle.setOnDragExited(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        System.out.println("on drag exited");
                        if (dragEvent.getDragboard().getString().split("\\s")[0].equals("building")) {
                            finalRectangle.setFill(texturesPaints.get(block.getType()));
                        }
                    }
                });

                rectangle.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        System.out.println("on drag dropped");
                        if (dragEvent.getDragboard().getString().split("\\s")[0].equals("building")) {
                            HashMap<String, String> inputs = new HashMap<>();
                            inputs.put("x", block.getX().toString());
                            inputs.put("y", block.getY().toString());
                            String buildingType = dragEvent.getDragboard().getString().replace("building ", "");
                            inputs.put("type", buildingType);
                            TurnController.setInputs(inputs);
                            TurnController.dropBuilding();
                        }
                    }
                });
            }
        }

        mapPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Left"))
                    mapPane.setLayoutX(mapPane.getLayoutX()+25);
                else if (keyName.equals("Right"))
                    mapPane.setLayoutX(mapPane.getLayoutX()-25);
                else if (keyName.equals("Up"))
                    mapPane.setLayoutY(mapPane.getLayoutY()+25);
                else if (keyName.equals("Down"))
                    mapPane.setLayoutY(mapPane.getLayoutY()-25);
            }
        });

        mapPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                System.out.println(scrollEvent.getDeltaY());
                mapPane.setScaleX(mapPane.getScaleX()*(1+(scrollEvent.getDeltaY()/100)));
                mapPane.setScaleY(mapPane.getScaleY()*(1+(scrollEvent.getDeltaY()/100)));

            }
        });

        return mapPane;
    }

    public static Pane getMapPane() {
        return mapPane;
    }

    public static void setMapPane(Pane mapPane) {
        GameViewUtils.mapPane = mapPane;
    }
}
