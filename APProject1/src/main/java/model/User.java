package model;

import model.user_stuff.Score;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static HashMap<String, User> users = new HashMap<String, User>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private String passwordRecoveryQuestion;
    private String passwordRecoveryAnswer;
    private ArrayList<Score> scores;
    private int highScore;

    public User(String username, String password,
                String nickname,
                String email, String slogan) {
        this.username = username;
        this.password = password;
        if(nickname == null) {
            this.nickname = username;
        }else {
            this.nickname = nickname;
        }
        this.email = email;
        this.slogan = slogan;
        this.scores = new ArrayList<Score>();
        this.highScore = 0;
    }


    //getter setter
    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static void setUsers(HashMap<String, User> users) {
        User.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public String getPasswordRecoveryAnswer() {
        return passwordRecoveryAnswer;
    }

    public void setPasswordRecoveryAnswer(String passwordRecoveryAnswer) {
        this.passwordRecoveryAnswer = passwordRecoveryAnswer;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public int getHighScore() {
        int max = 0;
        for (Score score : scores) {
            if (score.getAmount() > max) {
                max = score.getAmount();
            }
        }
        highScore = max;
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    //static methods
    public static void addUser(User user) {
        users.put(user.username, user);
        DataBase.getDataBase().addUser(user);
    }

    public static void removeUser(User user) {
        users.remove(user.username);
    }

    public static boolean doesUserExit(String username) {
        return users.containsKey(username);
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static User getUserByEmail(String email) {
        for (String username : users.keySet()) {
            if (users.get(username).getEmail().equals(email)) {
                return users.get("username");
            }
        }
        return null;
    }

    public static boolean doesEmailExits(String email) {
        return getUserByEmail(email) != null;
    }

    //instance methods
    public boolean isPasswordCurrent(String password) {
        return this.password.equals(password);
    }

    public int getRank() {
        highScore = getHighScore();
        int rank = 1;
        for (String username : users.keySet()) {
            if (users.get(username).getHighScore() > highScore) {
                rank++;
            }
        }
        return rank;
    }

    @Override
    public String toString() {
        return "username: " +username + "\n" +
                "email: " + email + "\n" +
                "nickname: " + ((nickname == null) ? "not have been set" : nickname) + "\n" +
                "highScore=" + getHighScore() + "\n" +
                "rank=" + getRank() + "\n" +
                "slogan: " + slogan;
    }
}
