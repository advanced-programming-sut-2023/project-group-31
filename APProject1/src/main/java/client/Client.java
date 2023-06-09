package client;

import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client{
    private static Client client;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public Client(String host, int port) throws IOException {
        System.out.println("Starting Client service...");
        Socket socket;
        try {
            socket = new Socket("localhost", port);
        } catch (Exception exception){
            return;
        }
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        PacketReceiver packetReceiver = new PacketReceiver();
        packetReceiver.start();
    }

    public void loginInServer(String username) throws IOException {
        if(dataOutputStream == null){
            return;
        }
        Packet packet = new Packet("",PacketType.USER_LOGIN,username);
        dataOutputStream.writeUTF(packet.toJson());
    }

    public void updateDataBase(String methodName , String input) throws IOException {
        DataBaseUpdater dataBaseUpdater = new DataBaseUpdater(methodName,input);
        Packet packet = new Packet("",PacketType.UPDATE_DATABASE,dataBaseUpdater.toJson());
        dataOutputStream.writeUTF(packet.toJson());
    }

    public void ForceUsersToDo(String className, String methodName, HashMap<String,String> inputs, ArrayList<User> users) throws IOException {
        FunctionalOrder functionalOrder = new FunctionalOrder(users,className,methodName,inputs);
        Packet packet = new Packet("",PacketType.ORDER_FUNCTION,functionalOrder.toJson());
        dataOutputStream.writeUTF(packet.toJson());
    }

    public void getDataBase() throws IOException {
        Packet packet = new Packet("",PacketType.GET_DATABASE,"");
        dataOutputStream.writeUTF(packet.toJson());
     }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        Client.client = client;
    }
}
