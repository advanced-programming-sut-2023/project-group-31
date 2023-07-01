package client;
import com.google.gson.Gson;

public class Packet {
    PacketType packetType;
    String command;
    String value;

    public Packet(String command, PacketType packetType, String value) {
        this.command = command;
        this.packetType = packetType;
        this.value = value;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static Packet getFromGson(String gson){
        return (Packet) new Gson().fromJson(gson, Packet.class);
    }
}
