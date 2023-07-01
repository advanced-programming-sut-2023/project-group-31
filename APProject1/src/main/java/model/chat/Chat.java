package model.chat;

import model.User;

import java.util.ArrayList;

public class Chat {
    protected ChatType chatType;
    protected ArrayList<Message> messages;
    protected String name;
    protected String imageUrl;
    protected ArrayList<User> members;

    protected String id;

    public Chat(ChatType chatType ) {
        this.chatType = chatType;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.imageUrl = "/Media/Chat/groupAvatar.png";
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
