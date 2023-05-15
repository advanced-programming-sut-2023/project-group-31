package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.people.Assassin;
import view.game_system.messages.BarracksMessages;
//import model.game_stuff.people.Kicker;
//import model.game_stuff.people.Thrower;
//import model.game_stuff.people.enums.KickerTypes;
//import model.game_stuff.people.enums.ThrowerTypes;
import model.game_stuff.people.Troop;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;
import view.game_system.messages.BarracksMessages;

public class MercenaryController extends ControllerUtils {
    private static MenuBuilding menuBuildings;
    public static BarracksMessages createUnit(){
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0) {
            return BarracksMessages.INVALID_COMMAND;
        }
        Troops troop=Troops.getTroopsByName(inputs.get("type"));
        if(troop==null&&troop.getNationality()==Nationality.EUROPEAN){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        int amount =Integer.parseInt(inputs.get("amount"));
        if(troop.getCost()*Integer.parseInt(inputs.get("amount"))> currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
//        if (!currentPlayer.getWeaponries().contains(troop.getWeapon())){
//            if(!troop.getName().equals("Slaves"))
//            return BarracksMessages.NOT_ENOUGH_WEAPON;}
        if(currentPlayer.getPossession().getPeasant()<amount){
            return BarracksMessages.PEOPLE_NEEDED;
        }
        switch (troop.getType()){
            case "thrower":
                while (amount>0) {
                    TroopTypes throwerTypes = TroopTypes.getTroopByName(troop.getName());
                    if (throwerTypes == null)
                        break;
                    createThrower(throwerTypes);
                    amount--;
                }
                break;
            case "kicker":
                while (amount>0) {
                    TroopTypes troopTypes = TroopTypes.getTroopByName(troop.getName());
                    if (troopTypes == null)
                        break;
                    createKicker(troopTypes);
                    amount--;
                }
                break;
            case "assassins":
                while (amount>0) {
                    TroopTypes types = TroopTypes.getTroopByName(troop.getName());
                    if (types == null) {
                        break;
                    }
                    createAssassins(types);
                    amount--;
                }
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-troop.getCost()*Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    private static void createAssassins(TroopTypes types) {
        Assassin assassin=new Assassin(currentPlayer);
        currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
        assassin.setPosition(menuBuildings.getPosition());
        menuBuildings.getPosition().addPerson(assassin);
    }

    private static void createKicker(TroopTypes kickerTypes) {
        Troop kicker =new Troop(currentPlayer,kickerTypes);
        currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
        kicker.setPosition(menuBuildings.getPosition());
        menuBuildings.getPosition().addPerson(kicker);
        //Mercenary post location
        //currentplayer.mercenaryPost.getBlock(0).add(thrower)
    }

    public static void setMenuBuildings(MenuBuilding menuBuildings) {
        MercenaryController.menuBuildings = menuBuildings;
    }

    private static void createThrower(TroopTypes throwerTypes) {
        Troop thrower=new Troop(currentPlayer,throwerTypes);
        currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
        thrower.setPosition(menuBuildings.getPosition());
        menuBuildings.getPosition().addPerson(thrower);
        //Mercenary post location
        //currentplayer.mercanaryPost.getBlock(0).add(thrower)
    }
}
