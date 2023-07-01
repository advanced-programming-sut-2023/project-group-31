package client;

import com.google.gson.Gson;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseUpdater {
    String methodName;

    Object input;

    public DataBaseUpdater( String methodName, Object input) {

        this.methodName = methodName;
        this.input = input;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static DataBaseUpdater getFromGson(String gson){
        return (DataBaseUpdater) new Gson().fromJson(gson, DataBaseUpdater.class);
    }

}

