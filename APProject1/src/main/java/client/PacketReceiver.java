package client;

import com.google.gson.Gson;
import model.DataBase;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PacketReceiver extends Thread{
    @Override
    public void run() {
        while(true){
            try {
                Packet packet =Packet.getFromGson(Client.getClient().dataInputStream.readUTF());
                if(packet.packetType.equals(PacketType.GET_DATABASE)){
                    System.out.println("dataBase Updated");
                    DataBase.setDataBase(new Gson().fromJson(packet.value,DataBase.class));
                    DataBase.saveDataBase();
                } else if(packet.packetType.equals(PacketType.ORDER_FUNCTION)){
                    FunctionalOrder functionalOrder;
                    functionalOrder = FunctionalOrder.getFromGson(packet.value);
                    Object output = new Object();
                    Class.forName(functionalOrder.className).getDeclaredMethod(functionalOrder.methodName).invoke(output);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
