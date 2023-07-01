import model.DataBase;
import model.User;
import model.chat.Messenger;
import server.Master;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException {
        for(User user: DataBase.getDataBase().getUsers()) {
            User.addUser(user);
        }
        if(DataBase.getDataBase().getMessenger() ==null){
            DataBase.getUnloadDataBase().setMessenger( new Messenger());
            DataBase.saveDataBase();
        }
        DataBase.getUnloadDataBase().getMessenger().setMessengerChats();
        DataBase.saveDataBase();
        Master master = new Master(8080);
    }
}
