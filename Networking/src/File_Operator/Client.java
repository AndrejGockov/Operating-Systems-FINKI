package File_Operator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private String name;
    private int port;

    Client(String name, int port){
        this.name = name;
        this.port = port;
    }

    @Override
    public void run(){
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(name, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner input = new Scanner(System.in);
        String clientCommand = "";

        while (true){
            // For sending command to server
            clientCommand = input.nextLine();
            writer.println(clientCommand);


            try {
                String serverResponse = reader.readLine();
                System.out.println(serverResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args){
        Client client = new Client("localhost", 7953);
        client.start();
    }
}