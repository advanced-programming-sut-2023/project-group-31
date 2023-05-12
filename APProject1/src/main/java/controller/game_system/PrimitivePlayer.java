package controller.game_system;

import model.User;
import model.game_stuff.Colors;

public class PrimitivePlayer {
    private User user;
    private Colors color;
    private int lordHouseNumber;

    public PrimitivePlayer(User user, Colors color, int lordHouseNumber) {
        this.user = user;
        this.color = color;
        this.lordHouseNumber = lordHouseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getLordHouseNumber() {
        return lordHouseNumber;
    }

    public void setLordHouseNumber(int lordHouseNumber) {
        this.lordHouseNumber = lordHouseNumber;
    }
}
