package model.game_stuff;

import java.util.HashMap;

public class Game {
    private HashMap<String, Government> players;
    private Map map;

    public Map getMap() {
        return map;
    }

    public Government getPlayerByNickname(String nickname) {
        return players.get(nickname);
    }
}
