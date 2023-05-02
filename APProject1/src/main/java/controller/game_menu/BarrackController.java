package main.java.controller.game_menu;

import main.java.view.game_system.messages.BarracksMessages;
import model.game_stuff.types.Nationality;

public class BarrackController extends controller.ControllerUtils {
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0){
            return BarracksMessages.INVALID_COMMAND;
        }
        boolean checker=false;
        int cost = 0;
        for (model.game_stuff.types.Troops value : model.game_stuff.types.Troops.values()) {
            if(value.getName().equals(inputs.get("type"))&&value.getNationality()== Nationality.EUROPEAN){
                checker=true;
                cost=value.getCost();
            }
        }
        if(checker==false){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(cost*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        //if (currentPlayer().getweapon(value.getWeapon)==0){
        // return NOT_ENOUGH_WEAPON}
        if(currentPlayer.getPopulation()==0){
            return BarracksMessages.PEOPLE_NEEDED;
        }
        //currentPlayer.getArmies.add(value.get("type"))
        //currentPlayer.getPossession.setGold(getGold-value.get(type).getCost*inputs.get("amount"))
        return BarracksMessages.SUCCESS;
    }
}
