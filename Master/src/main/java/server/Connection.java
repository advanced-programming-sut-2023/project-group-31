package server;

import com.google.gson.Gson;
import model.DataBase;
import model.User;
import model.chat.Messenger;
import model.game_stuff.MapTexture;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{
    public Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private User user;

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public synchronized void run(){
        try {



            receivePacket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void receivePacket() throws IOException, NoSuchMethodException {
        String stringPacket;
        Packet packet;
        while(true) {
            stringPacket = dataInputStream.readUTF();
            packet = Packet.getFromGson(stringPacket);
            FunctionalOrder functionalOrder;
            if(packet.packetType.equals(PacketType.USER_LOGIN)){
                if((user = User.getUserByUsername(packet.value))==null){
                    System.out.println("Invalid username");
                    return;
                }
                user = User.getUserByUsername(packet.value);
                for(int i=0;i<Master.getConnections().size();i++){
                    Connection connection = Master.getConnections().get(i);
                    if(connection.user !=null &&connection.user.equals(user)){
                        Master.getConnections().remove(i);
                        i--;
                    }
                }
                System.out.println("user "+user.getUsername() + " connected.");
            }else if(packet.packetType.equals(PacketType.UPDATE_DATABASE)){
                DataBaseUpdater dataBaseUpdater = DataBaseUpdater.getFromGson(packet.value);
                updateDataBase(dataBaseUpdater);
                DataBase.saveDataBase();
            } else if(packet.packetType.equals(PacketType.GET_DATABASE)){
                dataOutputStream.writeUTF(new Packet("",PacketType.GET_DATABASE,new Gson().toJson(DataBase.getDataBase())).toJson());
            } else if(packet.packetType.equals(PacketType.ORDER_FUNCTION)){
                functionalOrder =FunctionalOrder.getFromGson(packet.value);
                for(Connection connection:Master.getConnections()){
                    if(functionalOrder.users.contains(connection.getUser())){
                        dataOutputStream.writeUTF(packet.toJson());
                    }
                }
            }
        }
    }

    private void updateDataBase(DataBaseUpdater dataBaseUpdater) throws IOException {
        if(dataBaseUpdater.methodName.equals("addUser")){
            User user = new Gson().fromJson((String)( dataBaseUpdater.input),User.class);
            DataBase.getUnloadDataBase().addUser(user);
            DataBase.saveDataBase();
            User.addUser(user);
            DataBase.getUnloadDataBase().getMessenger().addUserToMessenger(user);
            System.out.println(DataBase.getUnloadDataBase().getMessenger().getChats().get(0).getMembers());
            DataBase.saveDataBase();
            System.out.println("user "+user.getUsername() + " added");
            System.out.println(DataBase.getUnloadDataBase().getMessenger().getChats().get(0).getMembers());

        } else if(dataBaseUpdater.methodName.equals("addMapTexture")){

        } else if(dataBaseUpdater.methodName.equals("sendMessenger")){
            System.out.println("goto to add message");
            Messenger messenger = new Gson().fromJson((String)( dataBaseUpdater.input), Messenger.class);
            DataBase.getUnloadDataBase().setMessenger(messenger);
            System.out.println("messenger updated");
            DataBase.saveDataBase();
        }
    }

    public void sendFunctionalOrderToUser(){

    }

    public void sendDataBaseToUser(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
