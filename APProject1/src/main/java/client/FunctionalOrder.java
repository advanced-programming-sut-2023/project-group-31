package client;


import com.google.gson.Gson;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionalOrder {
    ArrayList<User> users;
    String className;
    String methodName;

    HashMap<String,String> inputs;

    public FunctionalOrder(ArrayList<User> users, String className, String methodName, HashMap<String, String> inputs) {
        this.users = users;
        this.className = className;
        this.methodName = methodName;
        this.inputs = inputs;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static FunctionalOrder getFromGson(String gson){
        return (FunctionalOrder) new Gson().fromJson(gson, FunctionalOrder.class);
    }


}
