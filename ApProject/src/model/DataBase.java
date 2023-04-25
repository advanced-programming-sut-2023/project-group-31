package model;

import java.io.File;
import java.util.*;

public class DataBase {

    private static DataBase dataBase;
    private ArrayList<String> slogans;
    private ArrayList<String> recoveryQuestions;
    private ArrayList<User> users;

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

    public static void connectToDatabase() {
        File databaseFile = new File("dataBase.txt");
        if (databaseFile.length() == 0) {
            dataBase = new DataBase(new ArrayList<User>(), null);
        } else {
            JSONObject obj = new JSONObject();
            databaseFile = new DataBase()
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

    public static User getLoggedInUser() {
        return DataBase.dataBase.loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        DataBase.dataBase.loggedInUser = loggedInUser;
    }


}
