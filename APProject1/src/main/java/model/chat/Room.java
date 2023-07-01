package model.chat;

import model.User;

import java.util.ArrayList;

public class Room extends Chat{


    public Room(String name,ArrayList<User> members) {
        super(ChatType.ROOM);
        super.members = members;
        super.name = name;
    }
}
