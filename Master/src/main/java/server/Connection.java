package server;

import com.google.gson.Gson;
import model.DataBase;
import model.User;
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
            String username=dataInputStream.readUTF();
            for(User user:DataBase.getDataBase().getUsers()){
                User.addUser(user);
            }
            User user;
            if((user = User.getUserByUsername(username))==null){
                System.out.println("Invalid username");
                return;
            } else {
                for(int i=0;i<Master.getConnections().size();i++){
                    Connection connection = Master.getConnections().get(i);
                    if(connection.user !=null &&connection.user.equals(user)){
                        Master.getConnections().remove(i);
                        i--;
                    }
                }
                this.user = user;
            }


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
        while(true){
            stringPacket = dataInputStream.readUTF();
            packet = Packet.getFromGson(stringPacket);
            FunctionalOrder functionalOrder;
            if(packet.packetType.equals(PacketType.UPDATE_DATABASE)){
                DataBaseUpdater dataBaseUpdater = DataBaseUpdater.getFromGson(packet.value);
                updateDataBase(dataBaseUpdater);
                DataBase.saveDataBase();
            } else if(packet.packetType.equals(PacketType.GET_DATABASE)){
                dataOutputStream.writeUTF(new Gson().toJson(DataBase.getDataBase()));
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

    private void updateDataBase(DataBaseUpdater dataBaseUpdater) {
        if(dataBaseUpdater.methodName.equals("addUser")){
            DataBase.getDataBase().addUser((User)dataBaseUpdater.input);
            User.addUser((User)dataBaseUpdater.input);
            DataBase.saveDataBase();
        } else if(dataBaseUpdater.methodName.equals("addMapTexture")){

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
