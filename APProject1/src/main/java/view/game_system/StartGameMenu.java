package view.game_system;

import controller.ControllerUtils;
import controller.game_system.StartGameController;
import view.game_system.commands.StartGameCommands;
import view.game_system.messages.StartGameMessages;
import view.user_system.messages.MenuSwitcherMessages;
import view.ViewUtils;
import view.user_system.messages.UserMessages;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class StartGameMenu extends ViewUtils{
    //TODO: dastoor e clear
    public static MenuSwitcherMessages run() {
        System.out.println("------Start game menu-----");
        Matcher matcher;
        String command;
        while (true) {
            command = scanner.nextLine().trim();
            if (StartGameCommands.getMatcher(command, StartGameCommands.EXIT) != null) {
                return MenuSwitcherMessages.MAIN;
            } else if (StartGameCommands.getMatcher(command, StartGameCommands.GOTO_MAP_MENU) != null) {
                gotoMapMenu();
            } else if (StartGameCommands.getMatcher(command, StartGameCommands.SHOW_PLAYERS) != null) {
                System.out.println(StartGameController.showAllPlayers());
            } else if (StartGameCommands.getMatcher(command, StartGameCommands.SHOW_CHOSEN_PLAYERS) != null) {
                System.out.println(StartGameController.showPlayersInTheGame());
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.CHOOSE_MAP)) != null) {
                chooseMap(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_A_BLOCK_TEXTURE)) != null) {
                setABlocksTexture(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_TEXTURE)) != null) {
                setTextureForARectangle(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SHOW_DETAILS)) != null) {
                showDetails(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SHOW_MAPS)) != null) {
                System.out.println(StartGameController.showMaps());
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.ADD_PLAYER)) != null) {
                addPlayer(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.REMOVE_PLAYER)) != null) {
                removePlayer(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_PLAYERS_LORD_HOUSE)) != null) {
                setPlayersLordHouse(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SET_PLAYERS_TEAM)) != null) {
                setPlayersTeam(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SHOW_LORD_HOUSES_LEFT)) != null) {
                System.out.println(StartGameController.showLordHousesLeft());
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.SAVE_MAP)) != null) {
                saveMap();
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.DROP_TREE)) != null) {
                dropTree(matcher);
            } else if ((matcher = StartGameCommands.getMatcher(command, StartGameCommands.START)) != null) {
                if(startTheGame()) {
                    TurnMenu.run();
                    //mishe jash berim to menu elam natayej or something
                    return MenuSwitcherMessages.MAIN;
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private static void saveMap() {
        StartGameMessages message = StartGameController.saveMap();
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("save map failed: " + message.getTxt());
    }

    private static void dropTree(Matcher matcher){
        StartGameMessages result=StartGameController.dropTree(Integer.parseInt(matcher.group("x")),
                Integer.parseInt(matcher.group("y")),matcher.group("type"));
        if(result.equals(StartGameMessages.SUCCESS)){
            System.out.println("Tree dropped successfully.");
        }else{
            System.out.println(result);
        }
    }

    private static boolean startTheGame() {
        StartGameMessages message = StartGameController.start();
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return true;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return false;
        }
        System.out.println("start game failed: " + message.getTxt());
        return false;
    }

    private static void setPlayersTeam(Matcher matcher) {
        String username = ViewUtils.fixDoubleQuotes(matcher.group("username"));
        String color = ViewUtils.fixDoubleQuotes(matcher.group("color"));
        StartGameMessages message = StartGameController.setPlayersTeam(username, color);
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("remove player failed: " + message.getTxt());
    }

    private static void setPlayersLordHouse(Matcher matcher) {
        String username = ViewUtils.fixDoubleQuotes(matcher.group("username"));
        int lordHouseNumber = Integer.parseInt(matcher.group("lordHouse"));
        StartGameMessages message = StartGameController.setPlayersLordHouse(username, lordHouseNumber);
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("set lord house failed: " + message.getTxt());
    }

    private static void removePlayer(Matcher matcher) {
        String username = ViewUtils.fixDoubleQuotes(matcher.group("username"));
        StartGameMessages message = StartGameController.removePlayer(username);
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("remove player failed: " + message.getTxt());
    }

    private static void addPlayer(Matcher matcher) {
        String username = matcher.group("username");
        StartGameMessages message = StartGameController.addPlayer(username);
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("add player failed: " + message.getTxt());
    }

    private static void gotoMapMenu() {
        MapMenu mapMenu=new MapMenu();
        mapMenu.run();
    }

    private static void showDetails(Matcher matcher) {
        ControllerUtils.setInputs(ViewUtils.putInHashmap(matcher, StartGameCommands.SET_A_BLOCK_TEXTURE.getRegex()));
        StartGameMessages message = StartGameController.showDetails();
        if (message == StartGameMessages.SUCCESS) {
            System.out.println(StartGameMessages.SUCCESS.getTxt());
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("show details failed: " + message.getTxt());
    }

    private static void setTextureForARectangle(Matcher matcher) {
        ControllerUtils.setInputs(ViewUtils.putInHashmap(matcher, StartGameCommands.SET_A_BLOCK_TEXTURE.getRegex()));
        StartGameMessages message = StartGameController.setARectanglesTexture();
        if (message == StartGameMessages.SUCCESS) {
            System.out.println("set texture successful!");
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("set texture for rectangle failed: " + message.getTxt());
    }

    private static void setABlocksTexture(Matcher matcher) {
        ControllerUtils.setInputs(ViewUtils.putInHashmap(matcher, StartGameCommands.SET_A_BLOCK_TEXTURE.getRegex()));
        StartGameMessages message = StartGameController.setABlockTexture();
        if (message == StartGameMessages.SUCCESS) {
            System.out.println("set texture successful!");
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("set texture for a block failed: " + message.getTxt());
    }

    private static void chooseMap(Matcher matcher) {
        String name = matcher.group("name").trim().replaceAll("\"", "");
        StartGameMessages message = StartGameController.chooseMap(name);
        if (message == StartGameMessages.SUCCESS) {
            System.out.println("choose map successful!");
            return;
        }
        if (message == StartGameMessages.INVALID_COMMAND) {
            System.out.println("invalid command!");
            return;
        }
        System.out.println("choose map failed: " + message.getTxt());
    }
}
