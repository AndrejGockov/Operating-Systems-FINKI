package Last_Word;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Worker extends Thread{
    private Socket socket;
    private Socket socket1;

    Worker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        PrintWriter writer = null;
        BufferedReader reader = null;
        BufferedWriter textFile = null;

        HashSet<String> words = new HashSet<>();

        // Initiate reader & writer
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            // Text File
            textFile = new BufferedWriter(new FileWriter("src/Last_Word/WORDS_FILE.txt"));

            // HANDSHAKE validation
            if (!reader.readLine().equals("HANDSHAKE")) {
                writer.write("Handshake failed. Connection terminated");
                socket.close();
                return;
            }

            writer.println("Logged in:" + socket.getInetAddress());
        }catch(IOException e){
            e.printStackTrace();
        }

        String workerOutput = "";

        while(true) {
            try {
                workerOutput = reader.readLine();

                if(workerOutput.equals("STOP")){
                    writer.println("Number of words known: " + words.size());
                    writer.println("LOGGED OUT");
                    break;
                }

                // Adds to text file
                if(!words.contains(workerOutput)){
                    writer.println("NEMA");

                    words.add(workerOutput);

                    // Writes in text file
                    textFile.write(socket.getInetAddress() + " " + LocalDateTime.now() + " " + workerOutput);
                    textFile.flush();
                    textFile.newLine();
                }else{
                    writer.println("IMA");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
