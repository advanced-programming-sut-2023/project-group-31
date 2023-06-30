package model.chat;

import model.DataBase;
import model.User;

import java.util.ArrayList;

public class PublicChat extends Chat{
    ArrayList<User> members;

    public PublicChat() {
        super(ChatType.PUBLIC);
        members = DataBase.getDataBase().getUsers();
    }
}
