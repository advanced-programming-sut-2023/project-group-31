package model.chat;

import model.User;

public class Message {
    User sender;
    String content;
    Chat chat;

    boolean haveSeen;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.haveSeen = false;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
