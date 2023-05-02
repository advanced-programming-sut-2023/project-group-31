package controller.game_menu;

import model.game_stuff.Block;
import model.game_stuff.Map;
import model.game_stuff.enums.Textures;
import view.game_system.MapMenu;
import view.user_system.messages.UserMessages;
import view.viewStyle.Colors;

public class MapController {
    Map map;
    private int length;
    private int width;

    private int x;
    private int y;
    public MapController() {
        this.map = StartGameController.getChosenMap();
        length = 14;
        width = 4;
    }

    public void setXy(int x,int y){
        this.x=x;
        this.y=y;
    }

    public String showMapByXY() {
        String output="";
        if(map.getSize()<=(x+length/2)||map.getSize()<=(y+width/2)){
            System.out.println("show map failed: invalid position.");
        }
        output+=showLine();
        for (int i = y; i < y + width; i++) {
            for (int j = 0; j < 3; j++) {
                output+=showRow(x, i);
            }
            output+=showLine();
        }
        return output;

    }

    private String showLine() {
        String output="";
        output+="-";
        for (int i = 0; i < 6 * length; i++) {
            output+="-";

        }
        output+="-\n";
        return output;
    }

    private String showRow(int x, int y) {
        String type,output="";
        for (int i = x - (length / 2); i < x + (length / 2); i++) {
            output+=("|");
            if (map.getBlocks().get(i).get(y).getType().equals(Textures.GROUND)) {
                type = "#";
            } else {
                type = map.getBlocks().get(i).get(y).getType().getColor().getBackgroundColorCode()
                        + map.getBlocks().get(i).get(y).getType().getName().substring(0, 1)
                        + Colors.RESET.getBackgroundColorCode();
            }
            for (int j = 0; j < 6; j++) {
                output+=(type);
            }
        }
        output+=("|\n");
        return output;
    }


    public String showDetails(int x,int y) {
        if(x>=map.getSize()||y>=map.getSize()){
            return "Invalid x and y!";
        }
        Block block=map.getBlocks().get(x).get(y);
        return block.toString();
    }


    public void moveOnMap(int rights,int ups) {
        if(map.getSize()<=(x+rights+length/2)||map.getSize()<=(y+ups+width/2)){
            System.out.println("show map failed: invalid position.");
        }
        x=x+rights;
        y=y+ups;
    }
}
