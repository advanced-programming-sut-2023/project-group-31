package model.chat;

import model.User;

import java.util.ArrayList;

public class PrivateChat extends Chat{
    ArrayList<User> users;
    public PrivateChat() {
        super(ChatType.PRIVATE);
    }
}
