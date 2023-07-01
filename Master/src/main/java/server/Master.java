package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {

    private static ArrayList<Connection> connections = new ArrayList<>();

    public Master (int port){
        System.out.println("Starting Master service...");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                connections.add(connection);
                connection.start();
            }

        } catch (IOException e) {
            //TODO: try to reconnect...
        }

    }

    public static ArrayList<Connection> getConnections() {
        return connections;
    }

    public static void setConnections(ArrayList<Connection> connections) {
        Master.connections = connections;
    }
}
