package model;

import com.google.gson.Gson;
import controller.ControllerUtils;

import java.io.*;

public class Session {

    private static Session session;

    private String loggedInUserUsername;
    private String loggedInUserPassword;

    public Session() {
        this.loggedInUserUsername = null;
        this.loggedInUserPassword = null;
    }

    public static void saveResource(String filename, String str) {
        try {
            FileWriter myWriter = new FileWriter(Session.class.getResource("/"+filename).toString());
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSession() {

        Gson gson = new Gson();
        String jsonString = gson.toJson(session);
        saveResource("session.txt", jsonString);

    }

    public static void connectToSession() {
        File sessionFile = new File(Session.class.getResource("/session.txt").toExternalForm());
        if(session!=null){
            return;
        }
        if ( sessionFile.length() == 0) {
            session = new Session();

        } else {
            Gson gson = new Gson();

            try (Reader reader = new FileReader(Session.class.getResource("/session.txt").toString())) {

                session = gson.fromJson(reader, Session.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static Session getSession() {
        connectToSession();
        return session;
    }

    public static void setSession(Session session) {
        Session.session = session;
    }

    public String getLoggedInUserUsername() {
        return loggedInUserUsername;
    }

    public void setLoggedInUserUsername(String loggedInUserUsername) {
        this.loggedInUserUsername = loggedInUserUsername;
    }

    public String getLoggedInUserPassword() {
        return loggedInUserPassword;
    }

    public void setLoggedInUserPassword(String loggedInUserPassword) {
        this.loggedInUserPassword = loggedInUserPassword;
    }
}
