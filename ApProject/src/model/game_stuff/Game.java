package model.game_stuff;

import java.util.HashMap;

public class Game {
    private HashMap<String, Player> players;

    public Player getPlayerByNickname(String nickname) {
        return players.get(nickname);
    }
}
