package model.game_stuff;

import java.util.HashMap;

public class Game {
    private HashMap<String, Government> players;

    public Government getPlayerByNickname(String nickname) {
        return players.get(nickname);
    }
}
