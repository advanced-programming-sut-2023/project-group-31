package model.chat;

import model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    User sender;
    String content;
    Chat chat;
    String sendTime;

    boolean haveSeen;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.haveSeen = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        sendTime =(dtf.format(now));
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
