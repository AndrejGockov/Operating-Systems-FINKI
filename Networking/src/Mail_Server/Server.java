package Mail_Server;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private int port;
    public static FileWriter fw;
    private String fileOutput; //kje bide dadeno tamu u konstruktorot
    {
        try{
            fw = new FileWriter("src/Mail_Server/server.txt", true);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Server(int port) {
        //konstruktorot kje bide so dve argumenti namesto so 1
        this.port = port;
    }


    public static synchronized void Save(String line) throws IOException{
        fw.write(line + "\n");
        fw.flush();
    }

    public static synchronized void flush() throws IOException{
        fw.flush();
    }


    @Override
    public void run() {
        System.out.println("SERVER: starting...");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("SERVER: started");
        System.out.println("SERVER: waiting for connections...");

        while (true) {
            Socket newClient = null;
            try {
                newClient = serverSocket.accept();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("SERVER: new client - creating new worker thread...");
            new Worker(newClient).start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8765);
        server.start();
    }
}