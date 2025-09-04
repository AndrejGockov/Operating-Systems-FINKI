package SimpleNetworks.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client extends Thread{
    private String serverName;
    private int serverPort;

    private DatagramSocket socket;
    private InetAddress ipAddress;
    private byte[] buffer;

    private String message;

    public Client(String serverName, int serverPort, String message){
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.message = message;

        try{
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(serverName);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.ipAddress, this.serverPort);

        try{
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length, ipAddress, serverPort);
            socket.receive(packet);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        Client client = new Client("localhost", 9000, "Connected to the server");
        client.start();
    }
}
