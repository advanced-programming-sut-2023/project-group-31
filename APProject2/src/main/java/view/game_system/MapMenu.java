package view.game_system;

import controller.game_system.MapController;
import view.ViewUtils;
import view.game_system.commands.MapCommands;
import view.viewStyle.Colors;

import java.util.HashMap;
import java.util.regex.Matcher;

public class MapMenu extends ViewUtils {

    private final static MapController mapController=new MapController();


    public static void run() {
        String input;
        Matcher matcher;
        System.out.println("\n"+Colors.RED.getBackgroundColorCode()+"----map menu---"+Colors.RESET.getBackgroundColorCode()+"\n");
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = MapCommands.getMatcher(input, MapCommands.SHOW_MAP)) != null) {
                showMapByXY(matcher);
            } else if ((matcher = MapCommands.getMatcher(input, MapCommands.MOVE)) != null) {
                moveOnMap(matcher.group("directions"));
            }else if(input.equals("show whole map")){
                showHoleMap();
            }
            else if(MapCommands.getMatcher(input,MapCommands.NEXT_TURN)!=null){
                System.out.println(mapController.nextTurn());
            }else if ((matcher = MapCommands.getMatcher(input, MapCommands.SHOW_DETAILS)) != null) {
                showDetails(Integer.parseInt(matcher.group("x")),(Integer.parseInt(matcher.group("y"))));
            }else if ((matcher = MapCommands.getMatcher(input, MapCommands.SHOW_MORE_DETAILS)) != null) {
                showMoreDetails(Integer.parseInt(matcher.group("x")),(Integer.parseInt(matcher.group("y"))));
            } else if(input.equals("exit")){
                return;
            }else {
                System.out.println("Invalid command!");
            }
        }
    }

    public static void showMoreDetails(int x, int y) {
        System.out.println(mapController.detailedMap(x,y));
    }

    public static void showMapByXY(Matcher matcher){
        mapController.setXy(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        System.out.println(mapController.showMapByXY());
    }

    public static void showDetails(int x,int y){
        System.out.println(mapController.showDetails(x,y,false));
    }





    private enum Directions {
        RIGHT, UP;
    }

    private static HashMap<Directions, Integer> setDirections(String directionsString) {
        int rights=0,ups=0;
        String[] directionsArray = directionsString.trim().split(" ");
        for (String direction : directionsArray) {
            switch (direction) {
                case "right": rights++;
                    break;
                case "left": rights--;
                    break;
                case "up":  ups++;
                    break;
                case "down": ups--;
                    break;
            }
        }
        HashMap<Directions, Integer> enumDirections = new HashMap<Directions, Integer>();
        enumDirections.put(Directions.RIGHT,rights);
        enumDirections.put(Directions.UP,ups);
        return enumDirections;
    }

    private static void moveOnMap(String directionString) {
        int rights=setDirections(directionString).get(Directions.RIGHT);
        int ups=setDirections(directionString).get(Directions.UP);
        mapController.moveOnMap(rights,ups);
        System.out.println(mapController.showMapByXY());
    }


    public static void showHoleMap(){
        System.out.println(mapController.showWholeTheMap());
    }



}

