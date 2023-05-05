package controller.game_menu;

import controller.ControllerUtils;
import main.java.view.game_system.messages.BarracksMessages;
import model.game_stuff.people.Kicker;
import model.game_stuff.people.Thrower;
import model.game_stuff.people.enums.KickerTypes;
import model.game_stuff.people.enums.ThrowerTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;

public class MercenaryController extends ControllerUtils {
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0) {
            return BarracksMessages.INVALID_COMMAND;
        }
        Troops troop=Troops.getTroopByName(inputs.get("type"));
        if(troop==null&&troop.getNationality()==Nationality.EUROPEAN){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(troop.getCost()*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if (!currentPlayer.getWeaponries().contains(troop.getWeapon())){
            return BarracksMessages.NOT_ENOUGH_WEAPON;}
        if(currentPlayer.getPopulation()==0){
            return BarracksMessages.PEOPLE_NEEDED;
        }
        switch (troop.getType()){
            case "thrower":
                ThrowerTypes throwerTypes=ThrowerTypes.getThrowerByName(troop.getName());
                if(throwerTypes==null)
                    break;
                createThrower(throwerTypes);
            case "kicker":
                KickerTypes kickerTypes=KickerTypes.getKickerByName(troop.getName());
                if(kickerTypes==null)
                    break;
                createKicker(kickerTypes);
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-troop.getCost()*Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    private static void createKicker(KickerTypes kickerTypes) {
        Kicker kicker =new Kicker(currentPlayer,kickerTypes);
        kicker.setPosition();//Mercenary post location
        //currentplayer.mercenaryPost.getBlock(0).add(thrower)
    }

    private static void createThrower(ThrowerTypes throwerTypes) {
        Thrower thrower=new Thrower(currentPlayer,throwerTypes);
        thrower.setPosition();//Mercenary post location
        //currentplayer.mercanaryPost.getBlock(0).add(thrower)
    }
}
