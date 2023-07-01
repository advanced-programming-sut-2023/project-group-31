package model.chat;

import model.DataBase;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Messenger {


    private ArrayList<Chat> chats;

    public Messenger() throws IOException {
        chats  = new ArrayList<>();
    }


    public void setMessengerChats() throws IOException {
        if(chats.size()>0){
            return;
        }
        ArrayList<User> users = DataBase.getDataBase().getUsers();
        for(String username:User.getUsers().keySet()){
            users.add(User.getUsers().get(username));
        }
        int index =0;
        PublicChat publicChat = new PublicChat();
        publicChat.setId("chatId"+index);
        index++;
        chats.add(publicChat);
        for(int i=0;i<users.size();i++){
            for(int j=i+1;j<users.size();j++){
                PrivateChat privateChat = new PrivateChat(users.get(i),users.get(j));
                privateChat.setId("chatId"+index);
                chats.add(privateChat);
                index++;
            }
        }
    }

    public void addUserToMessenger(User user) throws IOException {
        ArrayList<User> users = DataBase.getDataBase().getUsers();
        ((PublicChat)(chats.get(0))).members.add(user);
        int index = chats.size();
        for(int i=0;i<users.size();i++){
            PrivateChat privateChat = new PrivateChat(user,users.get(i));
            privateChat.setId("chatId"+index);
            chats.add(privateChat);
            index++;
        }
    }


    public static Chat getChatById(String id){

        for(Chat chat:DataBase.getUnloadDataBase().getMessenger().getChats()){
            if(chat.getId().equals(id)){
                return chat;
            }
        }
        return null;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
