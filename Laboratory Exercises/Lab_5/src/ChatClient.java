import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient{
    public static void main(String args[]) throws IOException{
        // Establishes connection
        Socket socket = new Socket(InetAddress.getByName("194.149.135.49"), 9753);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Makes a new file where
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/log.txt"));

        Sender sender = new Sender(writer, fileWriter);
        Receiver receiver = new Receiver(fileWriter, reader);

        sender.start();
        receiver.start();
    }
}

class Sender extends Thread{
    private BufferedWriter writer;
    private BufferedWriter fileWriter;

    public Sender(BufferedWriter writer, BufferedWriter fileWriter){
        this.writer = writer;
        this.fileWriter = fileWriter;
    }

    @Override
    public void run(){
        Scanner input = new Scanner(System.in);

        // Logs in with index
        try{
            writer.write("hello:232120");
            writer.newLine();
            writer.flush();

            System.out.println("Successfully sent hello message.");
        }catch (IOException e){
            e.printStackTrace();
        }

        String line = input.nextLine();

        while(!line.equals("STOP")){
            try{
                writer.write("Sent: " + line);
                writer.newLine();
                writer.flush();

                // Writes in the file what the sender wrote
                fileWriter.write("Sent: " + line);
                fileWriter.newLine();
                fileWriter.flush();

                line = input.nextLine();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

class Receiver extends Thread{
    private BufferedWriter fileWriter;
    private BufferedReader reader;


    public Receiver(BufferedWriter fileWriter, BufferedReader reader){
        this.fileWriter = fileWriter;
        this.reader = reader;
    }

    @Override
    public void run(){
        // Writes back what it receives from the server
        try{
            String line = reader.readLine();

            while(true){
                System.out.println(line);

                // Writes in the file what the sender received
                fileWriter.write("Received: "+ line);
                fileWriter.newLine();
                fileWriter.flush();

                line = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}