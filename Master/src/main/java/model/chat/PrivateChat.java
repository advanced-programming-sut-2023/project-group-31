
package model.chat;

import model.User;

import java.util.ArrayList;

public class PrivateChat extends Chat{
    public PrivateChat(User user1,User user2) {
        super(ChatType.PRIVATE);
        members.add(user1);
        members.add(user2);
        name = "private";
    }
}
