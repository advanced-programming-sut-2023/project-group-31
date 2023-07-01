package model.chat;

import model.DataBase;
import model.User;

import java.util.ArrayList;

public class Messenger {

    private ArrayList<User> users;

    private ArrayList<Chat> chats;

    public Messenger() {
        for(String username:User.getUsers().keySet()){
            users.add(User.getUsers().get(username));
        }
        this.chats = new ArrayList<>();
        for(User user:users){

        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
