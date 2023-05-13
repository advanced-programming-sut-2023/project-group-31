package controller.game_system;

import controller.ControllerUtils;
import view.game_system.messages.BarracksMessages;
//import model.game_stuff.people.Kicker;
//import model.game_stuff.people.Thrower;
//import model.game_stuff.people.enums.KickerTypes;
//import model.game_stuff.people.enums.ThrowerTypes;
import model.game_stuff.Troop;
import model.game_stuff.buildings.MenuBuildings;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;
import view.game_system.messages.BarracksMessages;

public class MercenaryController extends ControllerUtils {
    private static MenuBuildings menuBuildings;
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0) {
            return BarracksMessages.INVALID_COMMAND;
        }
        Troops troop=Troops.getTroopsByName(inputs.get("type"));
        if(troop==null&&troop.getNationality()==Nationality.EUROPEAN){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(troop.getCost()*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if (!currentPlayer.getWeaponries().contains(troop.getWeapon())){
            return BarracksMessages.NOT_ENOUGH_WEAPON;}
        if(currentPlayer.getPossession().getPeasant()==0){
            return BarracksMessages.PEOPLE_NEEDED;
        }
        switch (troop.getType()){
            case "thrower":
                TroopTypes throwerTypes=TroopTypes.getTroopByName(troop.getName());
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                if(throwerTypes==null)
                    break;
                createThrower(throwerTypes);
            case "kicker":
               TroopTypes troopTypes= TroopTypes.getTroopByName(troop.getName());
               currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                if(troopTypes==null)
                    break;
                createKicker(troopTypes);
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-troop.getCost()*Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    private static void createKicker(TroopTypes kickerTypes) {
        Troop kicker =new Troop(currentPlayer,kickerTypes);
        kicker.setPosition(menuBuildings.getPosition());
        menuBuildings.getPosition().addPerson(kicker);
        //Mercenary post location
        //currentplayer.mercenaryPost.getBlock(0).add(thrower)
    }

    public void setMenuBuildings(MenuBuildings menuBuildings) {
        this.menuBuildings = menuBuildings;
    }

    private static void createThrower(TroopTypes throwerTypes) {
        Troop thrower=new Troop(currentPlayer,throwerTypes);
        thrower.setPosition(menuBuildings.getPosition());
        menuBuildings.getPosition().addPerson(thrower);
        //Mercenary post location
        //currentplayer.mercanaryPost.getBlock(0).add(thrower)
    }
}
