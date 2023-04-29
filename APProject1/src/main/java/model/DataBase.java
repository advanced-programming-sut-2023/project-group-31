package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import controller.ControllerUtils;


public class DataBase {

    private static DataBase dataBase;
    private ArrayList<String> slogans;
    private ArrayList<String> recoveryQuestions;
    private ArrayList<User> users;

    private final static String path=new File("").getAbsolutePath()+"\\src\\main\\resources\\";;

    private User loggedInUser;

    public DataBase(ArrayList<User> users, User loggedInUser) {
        this.slogans = new ArrayList<String>(List.of(
                "We are will do it!",
                "make America great again!",
                "Independence freedom Islamic republic!",
                "Woman life freedom!"));
        this.recoveryQuestions = new ArrayList<String>(List.of(
                "What is your best food?",
                "where does your mather?",
                "What do you prefer between Messi and Haj Qasem?",
                "Chips mikhori?"));
        this.users = users;
        this.loggedInUser = loggedInUser;

    }

    public static String readResource(String filename)
    {
        filename = new File("").getAbsolutePath()+"\\src\\main\\resources\\"+filename;
        // Declaring object of StringBuilder class
        StringBuilder builder = new StringBuilder();

        // try block to check for exceptions where
        // object of BufferedReader class us created
        // to read filepath
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filename))) {

            String str;

            // Condition check via buffer.readLine() method
            // holding true upto that the while loop runs
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        }

        // Catch block to handle the exceptions
        catch (IOException e) {

            // Print the line number here exception occurred
            // using printStackTrace() method
            e.printStackTrace();
        }

        // Returning a string
        return builder.toString();
    }

    public static void connectToDatabase() {
        File databaseFile = new File(path+"dataBase.txt");
        if (databaseFile.length() == 0) {
            dataBase = new DataBase(new ArrayList<User>(), null);
        } else {
            //TODO
        }
    }

    public static void loadApp(){
        connectToDatabase();
        ControllerUtils.setCurrentUser(dataBase.loggedInUser);
        if(dataBase.isUserLoggedInBefore()){
            ControllerUtils.setCurrentUser(dataBase.loggedInUser);
        }
    }



    static class CompareByHighScore implements Comparator<User> {
        public int compare(User a, User b) {
            return a.getHighScore() - b.getHighScore();
        }
    }

    public static void sortUsersByHighScore() {
        Collections.sort(DataBase.dataBase.users, new CompareByHighScore());
    }

    public static int getUsersRank(User user) {
        sortUsersByHighScore();
        int rank = 1;
        for (User user1 : DataBase.dataBase.users) {
            if (user1.equals(user))
                return rank;
            rank++;
        }
        return -1;
    }

    public static ArrayList<String> getSlogans() {
        return DataBase.dataBase.slogans;
    }

    public static String getRandomSlogan() {
        Random random = new Random();
        return DataBase.dataBase.slogans.get(random.nextInt(DataBase.dataBase.slogans.size()));
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isUserLoggedInBefore(){
        return loggedInUser!=null;
    }


}
