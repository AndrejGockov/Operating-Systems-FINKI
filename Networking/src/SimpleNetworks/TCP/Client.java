package SimpleNetworks.TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{

    private String serverName;
    private int serverPort;

    Client(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    @Override
    public void run(){
        Scanner input = new Scanner(System.in);
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try{
            socket = new Socket(serverName, serverPort);
            writer = new PrintWriter(socket.getOutputStream());

            while(true){
                String line = input.nextLine();
                writer.println(line);
                writer.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Client client = new Client("localhost", 9000);
        client.start();
    }
}
