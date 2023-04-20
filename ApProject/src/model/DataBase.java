package model;

import java.util.*;

public class DataBase {
    private static ArrayList<String> slogans;
    private static HashSet<String> recoveryQuestions;
    private static ArrayList<User> users;

    static {
        slogans = new ArrayList<String>(List.of(
                "We are will do it!" ,
                "make America great again!" ,
                "Independence freedom Islamic republic!" ,
                "Woman life freedom!"));

        recoveryQuestions = new HashSet<>(List.of(

        ));

        users = new ArrayList<>();

    }

    static class CompareByHighScore implements Comparator<User> {
        public int compare(User a, User b) {
            return a.getHighScore() - b.getHighScore();
        }
    }
    public static void sortUsersByHighScore() {
        Collections.sort(users, new CompareByHighScore());
    }

    public static int getUsersRank(User user) {
        sortUsersByHighScore();
        int rank = 1;
        for (User user1 : users) {
            if(user1.equals(user))
                return rank;
            rank++;
        }
        return -1;
    }
    public static ArrayList<String> getSlogans() {
        return slogans;
    }

    public static String getRandomSlogan() {
        Random random = new Random();
        return slogans.get(random.nextInt(slogans.size()));
    }

}
