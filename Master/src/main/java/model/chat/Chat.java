package model.chat;

import java.util.ArrayList;

public class Chat {
    private ChatType chatType;
    private ArrayList<Message> messages;

    public Chat(ChatType chatType) {
        this.chatType = chatType;
        this.messages = new ArrayList<>();
    }
}
