package SimpleNetworks.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server extends Thread{
    DatagramSocket socket;
    private byte[] buffer = new byte[256];

    public Server(int port){
        try{
            socket = new DatagramSocket(port);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        // Message from the user
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        System.out.println("Connection made with: " + packet.getAddress() + " " + packet.getPort());
        while(true){
            try{
                // Receives and prints the message from the user
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Received message:");
                System.out.println(received);

                packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        Server server = new Server(9000);
        server.start();
    }
}
