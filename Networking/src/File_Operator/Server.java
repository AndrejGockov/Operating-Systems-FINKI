package File_Operator;

import java.io.IOException;
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
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket = null;

        try {
            socket = serverSocket.accept();
        }catch (IOException e){
            e.printStackTrace();
        }

        Worker worker = new Worker(socket);
        worker.start();
    }

    public static void main(String args[]){
        Server server = new Server(7953);
        server.start();
    }
}