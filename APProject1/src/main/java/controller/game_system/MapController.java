package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.Block;
import model.game_stuff.Government;
import model.game_stuff.enums.Textures;
import view.viewStyle.Colors;
import model.game_stuff.Map;
import java.util.ArrayList;


public class MapController extends ControllerUtils {
    private Map map;
    private int length;
    private int width;

    private int x;
    private int y;

    public MapController() {
        this.map = StartGameController.getChosenMap();
        length = 14;
        width = 4;
    }

    public void setXy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String showMapByXY() {
        String output="";
        if(map.getSize()<=(x+length/2)||map.getSize()<=(y+width/2)){
            return "show map failed: invalid position.";
        }
        output += showLine();
        for (int i = y; i < y + width; i++) {
            for (int j = 0; j < 3; j++) {
                output += showRow(x, i);
            }
            output += showLine();
        }
        return output;

    }

    private String showLine() {
        String output = "";
        output += "-";
        for (int i = 0; i < 6 * length; i++) {
            output += "-";

        }
        output += "-\n";
        return output;
    }

    private String showRow(int x, int y) {
        String type, output = "";
        for (int i = x - (length / 2); i < x + (length / 2); i++) {
            output += ("|");
            if (map.getBlocks().get(i).get(y).getType().equals(Textures.GROUND)) {
                type = "#";
            } else {
                type = map.getBlocks().get(i).get(y).getType().getColor().getBackgroundColorCode()
                        + map.getBlocks().get(i).get(y).getType().getName().substring(0, 1)
                        + Colors.RESET.getBackgroundColorCode();
            }
            for (int j = 0; j < 6; j++) {
                output += (type);
            }
        }
        output += ("|\n");
        return output;
    }


    public String showDetails(int x, int y,boolean superDetailed) {
        if (x >= map.getSize() || y >= map.getSize()) {
            return "Invalid x and y!";
        }
        Block block = map.getBlocks().get(x).get(y);
        if(superDetailed)
            return block.getDetails();
        else
            return block.toString();
    }


    public void moveOnMap(int rights, int ups) {
        if (map.getSize() <= (x + rights + length / 2) || map.getSize() <= (y + ups + width / 2)) {
            System.out.println("show map failed: invalid position.");
        }
        x = x + rights;
        y = y + ups;
    }

    public String showWholeTheMap() {
        String output = "", type;
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (Textures.GROUND.equals(map.getBlocks().get(i).get(j).getType())) {
                    type = "#";
                } else {
                    type = map.getBlocks().get(i).get(y).getType().getColor().getBackgroundColorCode()
                            + map.getBlocks().get(i).get(y).getType().getName().substring(0, 1)
                            + Colors.RESET.getBackgroundColorCode();
                }
                output += type;
            }
            output+="\n";
        }
        return output;
    }

    public String detailedMap(int x, int y){
        String result="";
        for(int i=x-2;i<=x+2;i++){
            for(int j=y-2;j<=y+2;j++){
                result+=map.getBlock(i,j).getMoreDetails();
            }
        }
        return result;
    }

    public String nextTurn() {
        doNextTurn();
        for (Government player : currentGame.getPlayers()) {
            KingdomController.nextTurnForGovernment(player);
        }
        return currentPlayer.getName() + "is now playing!";
    }

    private void doNextTurn() {
        //TODO
        //bayad to data base hame darbazar begiri inaro mirza
        realNextTurn();
        Government current = getCurrentPlayer();
        //current.getPopulation().nextTurn();
        //current.getTroops.nextTurn();
        //current.getAllProducers.nextTurn();
    }

    private void realNextTurn() {
        removeLoser();
        getCurrentGame().setTurn(getCurrentGame().getTurn() + 1);
        int toTurn = getCurrentGame().getTurn() % getCurrentGame().getPlayers().size();
        //TODO
        //to faze shabake fek konam bayad check beshe ke aya aslan online hast ya na
        setCurrentPlayer(getCurrentGame().getPlayers().get(toTurn));
    }

    private void removeLoser() {
        ArrayList<Government> playerToRemove = new ArrayList<>();
        for (Government player : getCurrentGame().getPlayers()) {
            if (player.getLordsHouse().getBuilding().getHp() == 0) {
                //System.out.println("player " + player.getName() + " is losing!!!");
                playerToRemove.add(player);
                // player.getOwner().setScores();
            }
        }
        getCurrentGame().getPlayers().removeAll(playerToRemove);
    }
}
