package Last_Word;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private int port;

    Server(int port){
        this.port = port;
    }

    @Override
    public void run(){
        ServerSocket serverSocket = null;
        Socket socket = null;

        try{
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        }catch(IOException e){
            e.printStackTrace();
        }

        // Starts Worker thread
        Worker worker = new Worker(socket);
        worker.start();
    }

    public static void main(String[] args){
        Server server = new Server(7391);
        server.start();
    }
}
