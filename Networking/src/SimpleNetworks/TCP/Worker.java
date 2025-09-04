package SimpleNetworks.TCP;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread{

    private Socket socket = null;

    public Worker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader reader = null;
        PrintWriter writer = null;

        try{
            System.out.println("New message from: " + socket.getInetAddress() + " " + socket.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;

            while((line=reader.readLine()) != null){
                System.out.println(line);

                writer.write(line);
                writer.flush();
            }

            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
