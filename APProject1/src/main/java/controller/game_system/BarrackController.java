package controller.game_system;

import controller.ControllerUtils;
import model.game_stuff.buildings.MenuBuilding;
import model.game_stuff.people.Troop;
import model.game_stuff.people.enums.TroopTypes;
import model.game_stuff.types.Nationality;
import model.game_stuff.types.Troops;
import view.game_system.messages.BarracksMessages;

public class BarrackController extends ControllerUtils {
    private static MenuBuilding menuBuilding;

    public static BarracksMessages createUnit() {
        if (inputs.get("type") == null || Integer.parseInt(inputs.get("amount")) <= 0) {
            return BarracksMessages.INVALID_COMMAND;
        }

        Troops target = Troops.getTroopsByName(inputs.get("type"));

        if (target == null && target.getNationality() == Nationality.ARAB) {
            return BarracksMessages.INVALID_TROOP_TYPE;
        }
        int number = Integer.parseInt(inputs.get("amount"));
        if (target.getCost() * Integer.parseInt(inputs.get("amount")) > currentPlayer.getPossession().getGold()) {
            return BarracksMessages.NOT_ENOUGH_GOLD;
        }
        if (!currentPlayer.getWeaponries().contains(target.getWeapon()))
            if (currentPlayer.getPossession().getPeasant() < number) {
                return BarracksMessages.PEOPLE_NEEDED;
            }
        if (currentPlayer.getWeaponries().contains(target.getWeapon())) {
            return BarracksMessages.NOT_ENOUGH_WEAPON;
        }
        switch (target.getType()) {
            case "kicker":
                TroopTypes Troops = TroopTypes.getTroopByName(target.getName());
                while (number > 0) {
                    createKicker(Troops);
                    number--;
                }
                break;
            case "thrower":
                TroopTypes troop = TroopTypes.getTroopByName(target.getName());
                while (number > 0) {
                    createThrower(troop);
                    number--;
                }
                break;
            default:
                break;
        }
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold() - target.getCost() * Integer.parseInt(inputs.get("amount")));
        return BarracksMessages.SUCCESS;
    }

    public static void setMenuBuilding(MenuBuilding menuBuilding) {
        BarrackController.menuBuilding = menuBuilding;
    }

    private static void createThrower(TroopTypes target) {
        Troop troop = new Troop(currentPlayer, target);
        currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant() - 1);
        //System.out.println(currentPlayer.getPossession().getPeasant());
        troop.setPosition(menuBuilding.getPosition());
        menuBuilding.getPosition().setPerson(troop);
        //currentplayer.barrack.getblock(0).add(thrower)
    }

    private static void createKicker(TroopTypes target) {
        Troop troop = new Troop(currentPlayer, target);
        currentPlayer.getPossession().setPeasant(currentPlayer.getPossession().getPeasant() - 1);
        //System.out.println(currentPlayer.getPossession().getPeasant());
        troop.setPosition(menuBuilding.getPosition());
        menuBuilding.getPosition().setPerson(troop);
        //currentplayer.barrack.getblock(0).add(kicker)
    }
}
