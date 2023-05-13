package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.buildings.enums.BuildingMenus;
import model.game_stuff.people.Engineer;
import model.game_stuff.people.LadderMan;
import model.game_stuff.people.Tunneler;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;
import view.game_system.messages.BarracksMessages;

public class EngineerGuildController extends ControllerUtils {
    private static MenuBuilding menuBuilding;
    public static BarracksMessages createUnit() {
        if(inputs.get("type")==null||Integer.parseInt(inputs.get("amount"))<=0){
            return BarracksMessages.INVALID_COMMAND;
        }
        Troops troop = Troops.getTroopsByName(inputs.get("type"));
        if(troop==null&&troop.getNationality()== Nationality.ARAB&&troop.getNationality()==Nationality.EUROPEAN){
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        if(troop.getCost()*Integer.parseInt(inputs.get("amount"))>currentPlayer.getPossession().getGold()){
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if(currentPlayer.getPossession().getPeasant()==0){
            return BarracksMessages.PEOPLE_NEEDED;
        }
        switch (troop.getName()){
            case "Tunneler":
                TroopTypes troopTypes=TroopTypes.getTroopByName("Tunneler");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                Tunneler tunneler=new Tunneler(currentPlayer,troopTypes);
                tunneler.setPosition(menuBuilding.getPosition());
                menuBuilding.getPosition().addPerson(tunneler);
                break;
            case "Engineer":
                TroopTypes types=TroopTypes.getTroopByName("Engineer");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                Engineer engineer=new Engineer(currentPlayer,types);
                engineer.setPosition(menuBuilding.getPosition());
                menuBuilding.getPosition().addPerson(engineer);
                break;
            case "Laddermen":
                TroopTypes types1=TroopTypes.getTroopByName("Laddermen");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant()-1);
                LadderMan ladderMan=new LadderMan(currentPlayer);
                ladderMan.setPosition(menuBuilding.getPosition());
                menuBuilding.getPosition().addPerson(ladderMan);
                break;
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()-troop.getCost());
        return BarracksMessages.SUCCESS;
    }

    public static void setStorage(MenuBuilding menuBuilding) {
        EngineerGuildController.menuBuilding = menuBuilding;
    }
}
