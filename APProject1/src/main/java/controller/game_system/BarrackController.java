package controller.game_system;

import controller.ControllerUtils;
import view.game_system.messages.BarracksMessages;
import model.game_stuff.Troop;
import model.game_stuff.buildings.MenuBuildings;
import model.game_stuff.buildings.Storage;
import model.game_stuff.enums.Items;
//import model.game_stuff.people.Kicker;
//import model.game_stuff.people.Thrower;
//import model.game_stuff.people.enums.KickerTypes;
//import model.game_stuff.people.enums.ThrowerTypes;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;

public class BarrackController extends ControllerUtils {
    private static MenuBuildings storage;
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0){
            return BarracksMessages.INVALID_COMMAND;
        }

        Troops target=Troops.getTroopsByName(inputs.get("type"));

        if(target==null&&target.getNationality()==Nationality.ARAB){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(target.getCost()*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if(!currentPlayer.getWeaponries().contains(target.getWeapon()))
            if(currentPlayer.getPossession().getPeasant()==0){
                return BarracksMessages.PEOPLE_NEEDED;
            }
        switch (target.getType()){
            case "kicker":
                TroopTypes Troops=TroopTypes.getTroopByName(target.getName());
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                createKicker(Troops);
                break;
            case "thrower":
                TroopTypes troop=TroopTypes.getTroopByName(target.getName());
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                createThrower(troop);
                break;
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-target.getCost()*Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    public static void setStorage(MenuBuildings storage) {
        BarrackController.storage = storage;
    }

    private static void createThrower(TroopTypes target) {
         Troop troop=new Troop(currentPlayer,target);
        troop.setPosition(storage.getPosition());
        storage.getPosition().addPerson(troop);
        //currentplayer.barrack.getblock(0).add(thrower)
    }

    private static void createKicker(TroopTypes target) {
        Troop troop=new Troop(currentPlayer,target);
        troop.setPosition(storage.getPosition());
        storage.getPosition().addPerson(troop);
        //currentplayer.barrack.getblock(0).add(kicker)
    }
}
