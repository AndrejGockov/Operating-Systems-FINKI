package SimpleNetworks.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private int port;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run(){
        System.out.println("Server's starting");
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(port);
        } catch(IOException e){
            System.out.println("Server failed to start");
            return;
        }

        System.out.println("Server started");
        System.out.println("Waiting for connections...");

        while(true){
            Socket socket = null;

            try{
                socket = serverSocket.accept();
            }catch(IOException e){
                e.printStackTrace();
            }

            System.out.println("Starting worker thread:");
            // Handles multiple clients connecting
            Worker worker = new Worker(socket);
            new Worker(socket).start();
        }
    }

    public static void main(String[] args){
        Server server = new Server(9000);
        server.start();
    }
}
