package model.chat;

import model.User;

import java.util.ArrayList;

public class Room extends Chat{
    private ArrayList<User> members;

    public Room(ArrayList<User> members) {
        super(ChatType.ROOM);
        this.members = members;
    }
}
