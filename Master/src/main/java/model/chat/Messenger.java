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
        ArrayList<User> users = DataBase.getUnloadDataBase().getUsers();
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

    public static Chat getChatById(String id){
        for(Chat chat:DataBase.getUnloadDataBase().getMessenger().getChats()){
            if(chat.getId().equals(id)){
                return chat;
            }
        }
        return null;
    }

    public void addUserToMessenger(User user) throws IOException {
        ArrayList<User> users = DataBase.getDataBase().getUsers();
        if(chats.size()==0){
            chats.add(new PublicChat());
        }
        for(Chat chat: chats){
            for(User user1:chat.members){
                if(user.getUsername().equals(user1.getUsername())){
                    return;
                }
            }

        }
        ((chats.get(0))).members.add(user);
        int index = chats.size();

        for(int i=0;i<users.size();i++){
            PrivateChat privateChat = new PrivateChat(user,users.get(i));
            privateChat.setId("chatId"+index);
            chats.add(privateChat);
            index++;
        }
        System.out.println(chats.get(0).getMembers());
        DataBase.getUnloadDataBase().setMessenger(this);
        DataBase.saveDataBase();
    }


    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
