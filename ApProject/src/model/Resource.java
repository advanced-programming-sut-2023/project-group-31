package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource {

    private static ArrayList<String> slogans;
    {
        slogans=new ArrayList<String>(List.of(
                "We are will do it!"
                ,"make America great again!",
                "Independence freedom Islamic republic!",
                "Woman life freedom!"));
    }


    public static ArrayList<String> getSlogans() {
        return slogans;
    }
}
