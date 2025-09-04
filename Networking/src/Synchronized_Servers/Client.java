package Synchronized_Servers;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client{
    public static void main(String args[]) throws IOException {
        // First connection
        Socket clientOne = new Socket(InetAddress.getByName("192.168.88.10"), 7771);
        BufferedWriter writerOne = new BufferedWriter(new OutputStreamWriter(clientOne.getOutputStream()));
        BufferedReader readerOne = new BufferedReader(new InputStreamReader(clientOne.getInputStream()));

        // Second connection
        Socket clientTwo = new Socket(InetAddress.getByName("192.168.88.10"), 7772);
        BufferedWriter writerTwo = new BufferedWriter(new OutputStreamWriter(clientOne.getOutputStream()));
        BufferedReader readerTwo = new BufferedReader(new InputStreamReader(clientOne.getInputStream()));

        Sender senderOne = new Sender(writerOne);
        Receiver receiverOne = new Receiver(readerOne);

        senderOne.start();
        receiverOne.start();

        Sender senderTwo = new Sender(writerOne);
        Receiver receiverTwo = new Receiver(readerOne);

        senderTwo.start();
        receiverTwo.start();


    }
}

class Sender extends Thread{
    private BufferedWriter writer;

    Sender(BufferedWriter writer){
        this.writer = writer;
    }

    @Override
    public void run(){
        try {
            writer.write("hello:232120");
            System.out.println("ASD");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Receiver extends Thread{
    BufferedReader reader;

    Receiver(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public void run(){
        while(true){
            try{
                System.out.println(reader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}