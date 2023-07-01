package model.chat;

import model.DataBase;

import java.io.IOException;

public class PublicChat extends Chat{

    public PublicChat() throws IOException {
        super(ChatType.PUBLIC);
        name = "Public chat";
        members = DataBase.getDataBase().getUsers();
    }
}
