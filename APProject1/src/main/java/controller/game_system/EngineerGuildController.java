package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.enums.Direction;
import model.game_stuff.people.Engineer;
import model.game_stuff.people.LadderMan;
import model.game_stuff.people.Troop;
import model.game_stuff.people.Tunneler;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;
import view.game_system.messages.BarracksMessages;

import java.util.ArrayList;
import java.util.LinkedList;

public class EngineerGuildController extends ControllerUtils {
    private static MenuBuilding menuBuilding;

    public static BarracksMessages createUnit() {
        if (inputs.get("type") == null || Integer.parseInt(inputs.get("amount")) <= 0) {
            return BarracksMessages.INVALID_COMMAND;
        }
        Troops troop = Troops.getTroopsByName(inputs.get("type"));
        if (troop == null || troop.getNationality() == Nationality.ARAB || troop.getNationality() == Nationality.EUROPEAN) {
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        int number = Integer.parseInt(inputs.get("amount"));
        if (troop.getCost() * Integer.parseInt(inputs.get("amount")) > currentPlayer.getPossession().getGold()) {
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if (currentPlayer.getPossession().getPeasant() < number) {
            return BarracksMessages.PEOPLE_NEEDED;
        }
        switch (troop.getName()) {
            case "Tunneler":
                TroopTypes troopTypes = TroopTypes.getTroopByName("Tunneler");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant() - 1);
                Tunneler tunneler = new Tunneler(currentPlayer, troopTypes);
                //tunneler.setPosition(menuBuilding.getPosition());
                //menuBuilding.getPosition().setPerson(tunneler);
                initializeTroop(tunneler);
                break;
            case "Engineer":
                TroopTypes types = TroopTypes.getTroopByName("Engineer");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant() - 1);
                Engineer engineer = new Engineer(currentPlayer, types);
                //engineer.setPosition(menuBuilding.getPosition());
                //menuBuilding.getPosition().setPerson(engineer);
                initializeTroop(engineer);
                break;
            case "Laddermen":
                TroopTypes types1 = TroopTypes.getTroopByName("Laddermen");
                currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant() - 1);
                LadderMan ladderMan = new LadderMan(currentPlayer);
                initializeTroop(ladderMan);
                //ladderMan.setPosition(menuBuilding.getPosition());
                //menuBuilding.getPosition().setPerson(ladderMan);
                break;
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold() - troop.getCost());
        return BarracksMessages.SUCCESS;
    }

    private static void initializeTroop(Troop troop) {
        ArrayList<Direction> moveOrderArrayList = new ArrayList<>();
        moveOrderArrayList = UnitController.routUnit(troop.getPosition().getX(), troop.getPosition().getY(),moveOrderArrayList,
            menuBuilding.getPosition().getX() - troop.getPosition().getX(),
            menuBuilding.getPosition().getY() - troop.getPosition().getY());
        LinkedList<Direction> moveOrder = new LinkedList<>(moveOrderArrayList);
        troop.setMoveOrder(moveOrder);
        currentPlayer.addTroopToWaitingList(troop);
    }

    public static void setStorage(MenuBuilding menuBuilding) {
        EngineerGuildController.menuBuilding = menuBuilding;
    }
}
