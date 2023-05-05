package controller.game_menu;

import main.java.view.game_system.messages.BarracksMessages;
import model.game_stuff.enums.Items;
import model.game_stuff.people.Kicker;
import model.game_stuff.people.Thrower;
import model.game_stuff.people.enums.KickerTypes;
import model.game_stuff.people.enums.ThrowerTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;

public class BarrackController extends controller.ControllerUtils {
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0){
            return BarracksMessages.INVALID_COMMAND;
        }

        Troops target=Troops.getTroopByName(inputs.get("type"));

        if(target==null&&target.getNationality()==Nationality.ARAB){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(target.getCost()*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if(!currentPlayer.getWeaponries().contains(target.getWeapon()))
            if(currentPlayer.getPopulation()==0){
                return BarracksMessages.PEOPLE_NEEDED;
            }
        switch (target.getType()){
            case "kicker":
                KickerTypes kickerTypes=KickerTypes.getKickerByName(target.getName());
                createKicker(kickerTypes);
                break;
            case "thrower":
                ThrowerTypes throwerTypes=ThrowerTypes.getThrowerByName(target.getName());
                createThrower(throwerTypes);
                break;
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-target.getCost()*Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    private static void createThrower(ThrowerTypes target) {
        Thrower thrower=new Thrower(currentPlayer,target);
        thrower.setPosition();
        //currentplayer.barrack.getblock(0).add(thrower)
    }

    private static void createKicker(KickerTypes target) {
        Kicker kicker=new Kicker(currentPlayer,target);
        kicker.setPosition();
        //currentplayer.barrack.getblock(0).add(kicker)
    }
}
