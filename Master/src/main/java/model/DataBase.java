package model;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import model.chat.Messenger;
import model.game_stuff.MapTexture;


public class DataBase {

    private static DataBase dataBase;
    private ArrayList<String> slogans;
    private ArrayList<String> recoveryQuestions;
    private ArrayList<User> users;

    private Messenger messenger;

    //private ArrayList<MapTexture> mapTextures;

    private final static String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\";
    ;


    public DataBase() throws IOException {
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
        this.users = new ArrayList<User>();
        messenger = new Messenger();
        //mapTextures = new ArrayList<>();

    }

    public static String readResource(String filename) {
        filename = new File("").getAbsolutePath() + "\\src\\main\\resources\\" + filename;

        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filename))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void saveResource(String filename, String str) {
        try {
            FileWriter myWriter = new FileWriter(path + filename);
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectToDatabase() throws IOException {
        File databaseFile = new File(path + "dataBase.txt");
        if ( databaseFile.length() <1) {
            dataBase = new DataBase();
            saveDataBase();
        } else {
            Gson gson = new Gson();

            try (Reader reader = new FileReader(path + "dataBase.txt")) {

                dataBase = gson.fromJson(reader, DataBase.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        saveDataBase();

    }

    public static void saveDataBase() {

        Gson gson = new Gson();
        String jsonString = gson.toJson(dataBase);
        saveResource("dataBase.txt", jsonString);

    }

    public static void loadApp() throws IOException {
        connectToDatabase();
        HashMap<String,User> hashUsers= new HashMap<>();
        for(User user:dataBase.users){
            hashUsers.put(user.getUsername(),user);
        }
        User.setUsers(hashUsers);

    }


    static class CompareByHighScore implements Comparator<User> {
        public int compare(User a, User b) {
            return a.getHighScore() - b.getHighScore();
        }
    }

    public static ArrayList<User> sortUsersByHighScore() {
        ArrayList<User>copy=new ArrayList<>(DataBase.dataBase.users);
        Collections.sort(copy, new CompareByHighScore());
        return copy;
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


    public static DataBase getDataBase() throws IOException {
        connectToDatabase();

        return dataBase;
    }

    public ArrayList<String> getSlogans() {
        return slogans;
    }

    public static String getRandomSlogan() {
        Random random = new Random();
        return DataBase.dataBase.slogans.get(random.nextInt(DataBase.dataBase.slogans.size()));
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        dataBase.users.add(user);
    }

    public static void setDataBase(DataBase dataBase) {
        DataBase.dataBase = dataBase;
    }

    public void setSlogans() {
        this.slogans = new ArrayList<String>(List.of(
                "We are will do it!",
                "make America great again!",
                "Independence freedom Islamic republic!",
                "Woman life freedom!"));;
    }

    public static DataBase getUnloadDataBase(){
        return dataBase;
    }

    public void setRecoveryQuestions() {
        this.recoveryQuestions = new ArrayList<String>(List.of(
                "What is your best food?",
                "where does your mather?",
                "What do you prefer between Messi and Haj Qasem?",
                "Chips mikhori?"));
    }

    public Messenger getMessenger() {
        return dataBase.messenger;
    }

    public void setMessenger(Messenger messenger) {
        dataBase.messenger = messenger;
    }

    public static ArrayList<String> getRecoveryQuestions() {
        return dataBase.recoveryQuestions;
    }

//    public void addMapTexture(MapTexture newMapTexture){
//        for(MapTexture mapTexture:mapTextures){
//            if(mapTexture.equals(newMapTexture)){
//                return;
//            }
//        }
//        mapTextures.add(newMapTexture);
//    }
//
//    public ArrayList<MapTexture> getMapTextures() {
//        return mapTextures;
//    }
}
