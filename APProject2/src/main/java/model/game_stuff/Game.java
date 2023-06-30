package model.game_stuff;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private int turn;
    private  ArrayList<Government>players;
    private Map map;

    public Game(Map map) {
        this.map = map;
    }

    public void setPlayers(ArrayList<Government> players) {
        this.players = players;
    }

    public Map getMap() {
        return map;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Government> getPlayers() {
        return players;
    }
    public Government getPlayerByNickname(String name) {
        for (Government player : players) {
            if(player.getName().equals(name))
                return player;
        }
        return null;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    //  public Government getPlayerByNickname(String nickname) {
     //   return players.get(nickname);
    //}
}
