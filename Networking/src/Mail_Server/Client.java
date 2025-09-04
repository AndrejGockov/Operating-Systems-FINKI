package Mail_Server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread {
    private String mailTo = "@mail_1";
    private String mailFrom = "@mail_2";
    private String mailCC = "Mail CC";
    private String data = "?";

    private int serverPort;

    public Client(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        Socket socket;
        Random random = new Random();

        try {
            socket = new Socket(InetAddress.getLocalHost(), serverPort);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter bw = new PrintWriter(socket.getOutputStream(), true);

            bw.write((random.nextInt(10) % 2 == 0 ? "GET" : "POST") +
                    " /movies/" + random.nextInt(100) + " HTTP/1.1\n");
            bw.write("User: FINKI\n");
            bw.write("\n");
            bw.flush();

            String line = "/";
            int lineCount = 1;

            while ((line = br.readLine()) != null) {
                System.out.println("HttpClient received:" + line);

                if(line.contains("START")){
                    bw.println("MAIL TO: " + mailTo);
                    bw.flush();
                    System.out.println("ASD");
                }

                if (line.equals("TNX")){
                    bw.println("MAIL FROM:" + mailFrom);
                    bw.flush();
                }
                if(line.equals("200")){
                    bw.println("CC: " + mailCC);
                    bw.flush();
                }

                if(line.contains("RECEIVERS:")){
                    bw.println(data);
                    bw.flush();
                }

                if(line.startsWith("RECEIVED:")){
                    bw.println("EXIT");
                    bw.flush();
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(8765);
        client.start();
    }
}
