package Last_Word;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private String serverName;
    private int port;

    Client(String serverName, int port){
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            Socket socket = new Socket(serverName, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Handshake
            writer.println("HANDSHAKE");
            writer.flush();
            System.out.println(reader.readLine());

            // Client Input
            Scanner input = new Scanner(System.in);
            String clientInput = "";

            while(true){
                if(clientInput.equals("STOP"))
                    break;

                clientInput = input.nextLine();
                writer.println(clientInput);
                writer.flush();

                System.out.println(reader.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Client client = new Client("localhost", 7391);
        client.start();
    }
}
