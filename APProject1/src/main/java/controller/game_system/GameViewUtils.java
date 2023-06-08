package controller.game_system;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.game_stuff.Block;
import model.game_stuff.Colors;
import model.game_stuff.Map;
import model.game_stuff.enums.Textures;

import java.util.ArrayList;
import java.util.HashMap;

public class GameViewUtils {

    private static HashMap<Textures, Paint> texturesPaints;
    private static int blockSize;
    
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
        Pane mapPane = new Pane();
        mapPane.setScaleX(blockSize);
        mapPane.setScaleY(blockSize);
        int size = map.getSize();
        mapPane.setPrefWidth(size);
        mapPane.setPrefHeight(size);
        Rectangle rectangle;
        for (ArrayList<Block> rows : map.getBlocks()) {
            for (Block block : rows) {
                rectangle = new Rectangle(1,1);
                rectangle.setFill(texturesPaints.get(block.getType()));
                rectangle.setX(block.getX());
                rectangle.setY(block.getY());
                mapPane.getChildren().add(rectangle);
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

        return mapPane;
    }
}
