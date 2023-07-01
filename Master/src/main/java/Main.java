import model.DataBase;
import model.User;
import server.Master;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args){
        for(User user: DataBase.getDataBase().getUsers()){
            User.addUser(user);
        }
        Master master = new Master(8080);
    }
}
