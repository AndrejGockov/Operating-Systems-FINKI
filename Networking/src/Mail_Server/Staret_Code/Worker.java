package Mail_Server.Staret_Code;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Worker extends Thread {
    private Socket socket;
    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            writer.write("START" + socket.getInetAddress().getHostAddress() + "\n");
            writer.flush();

            String mailCC;
            String mailTo = null;
            String line;
            int cnt = 0;

            while((line = reader.readLine()) != null) {
                if(line.startsWith("MAIL TO:") ) {
                    if(line.contains("@")){
                        mailTo = line.split(": ")[1];
                        writer.write("TNX" + "\n");
                        writer.flush();
                    }
                    else{
                        throw new RuntimeException();
                    }
                }
                else if(line.startsWith("MAIL FROM:")) {
                    if(line.contains("@")){
                        writer.write("200" + "\n");
                        writer.flush();
                    }
                    else{
                        throw new RuntimeException();
                    }
                }
                else if(line.startsWith("CC:")) {
                        mailCC = line.split(": ")[1];
                        writer.write("RECEIVERS: " + mailTo+ mailCC + "\n");
                        writer.flush();
                }
                else if(line.equals("?")){
                    cnt++;
                    writer.write("RECEIVED " + cnt + " lines" + "\n");
                    writer.flush();
                }
                else if(line.equals("EXIT")) {
                    Server.flush();
                }
                else{
                    cnt++; //da gi broi liniite od samiot content na mejlot
                }
                Server.Save(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Request {
        public String command;
        public String uri;
        public String version;
        public Map<String, String> headers;
        public String body;

        public Request(String[] line) {
            this.command = line[0];
            this.uri = String.join(" ", Arrays.copyOfRange(line, 1, line.length - 1));
            this.version = line[line.length - 1];
            this.headers = new HashMap<>();
        }
    }
}