package view.game_system;

import controller.ControllerUtils;
import controller.game_system.UnitController;
import view.ViewUtils;
import view.game_system.commands.UnitCommands;
import view.game_system.messages.UnitMessages;

import java.util.regex.Matcher;

public class UnitMenu extends ViewUtils {
    public static void run() {
        Matcher matcher;
        String command;
        // TODO: patrol va digTunnel va piade sazi kar ladderMan
        while(true) {
            command = scanner.nextLine().trim();
            if ((matcher = UnitCommands.getMatcher(command, UnitCommands.RIGHT_CLICK)) != null) {
                return;
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.SET_STATE)) != null) {
                setState(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.SET_MOVE_ORDER)) != null) {
                setMoveOrder(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.MOVE)) != null) {
                move(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.SELECT_SPECIAL_TROOPS)) != null) {
                selectSpecialTroops(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.ATTACK)) != null) {
                attack(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.DIG_TUNNEL)) != null) {
                digTunnel(matcher);
            } else if ((matcher = UnitCommands.getMatcher(command, UnitCommands.FILL_TUNNEL)) != null) {
                fillTunnel(matcher);
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private static void move(Matcher matcher) {
        if(matcher.group("x") == null || matcher.group("y") == null) {
            System.out.println("invalid command!");
        }
        int destinationX = Integer.parseInt(matcher.group("x"));
        int destinationY = Integer.parseInt(matcher.group("y"));
        UnitMessages message = UnitController.setDestination(destinationX, destinationY);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("move successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("move failed: " + message.getTxt());
        }
    }

    private static void fillTunnel(Matcher matcher) {
        String directionString = fixDoubleQuotes(matcher.group("direction"));
        UnitMessages message = UnitController.fillTunnel(directionString);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("dig tunnel successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("dig tunnel failed: " + message.getTxt());
        }
    }

    private static void digTunnel(Matcher matcher) {
        String directionString = fixDoubleQuotes(matcher.group("direction"));
        UnitMessages message = UnitController.digTunnel(directionString);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("dig tunnel successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("dig tunnel failed: " + message.getTxt());
        }
    }

    private static void attack(Matcher matcher) {
        ControllerUtils.setInputs(ViewUtils.putInHashmap(matcher, UnitCommands.ATTACK.getRegex()));
        UnitMessages message = UnitController.setAttackTarget();
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("attack successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("attack failed: " + message.getTxt());
        }
    }

    private static void selectSpecialTroops(Matcher matcher) {
        String troopTypeString = fixDoubleQuotes(matcher.group("troopType"));
        UnitMessages message = UnitController.selectSpecialTroops(troopTypeString);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("selection successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("selection failed: " + message.getTxt());
        }
    }

    private static void setMoveOrder(Matcher matcher) {
        String moveOrderString = matcher.group("moveOrder").trim();
        UnitMessages message = UnitController.setMoveOrder(moveOrderString);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("setMoveOrder successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("set moveOrder failed: " + message.getTxt());
        }
    }

    private static void setState(Matcher matcher) {
        String state = fixDoubleQuotes(matcher.group("state"));
        UnitMessages message = UnitController.setState(state);
        if(message.equals(UnitMessages.SUCCESS)) {
            System.out.println("set state successful!");
        } else if(message.equals(UnitMessages.INVALID_COMMAND)) {
            System.out.println("invalid command!");
        } else {
            System.out.println("set state failed: " + message.getTxt());
        }
    }
}
