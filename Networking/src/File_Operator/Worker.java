package File_Operator;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Worker extends Thread{
    private Socket socket;
    private PrintWriter writer = null;
    private BufferedReader reader = null;

    public Worker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String response = null;

            while (true){
                // Splits client's message into blocks
                response = reader.readLine();

                if(response.equals("STOP"))
                    break;

                String[] commands = response.split("\\s+");

                executeCommands(commands);
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeCommands(String[] commands){
        if (commands.length < 1) {
            writer.println("Command written improperly");
            return;
        }

        String response = "";

        switch(commands[0]){
            case "LIST":
                LIST(commands[1]);
                break;
            case "COPY":
                System.out.println(commands[1] + " " + commands[2]);
                COPY(commands[1], commands[2]);
                break;
            case "DELETE":
                DELETE(commands[1]);
                break;
            default:
                break;
        }

        if (!response.equals(""))
            writer.println(response);
    }

    public void LIST(String dir){
        File directory = new File(dir);
        File[] files = directory.listFiles();

        String ans = null;

        for(File file : files) {
            BasicFileAttributes attr = null;

            try {
                attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // If it's a directory it prints everything insde the directory
            if (attr.isDirectory()) {
                LIST(file.getAbsolutePath());
                continue;
            }

            // Prints only on the server, println only prints one line when it's readLine()
            if (file.getName().contains(".txt")) {
//                writer.println("NAME:" + file.getName() + " SIZE:" + file.length() + " DATE_CREATED:" + attr.creationTime() + "\n");
                ans += "NAME:" + file.getName() + " SIZE:" + file.length() + " DATE_CREATED:" + attr.creationTime();
            }
        }

        System.out.println(ans);
        writer.println(ans);
    }

    public void COPY(String fileName, String destination){
        File file = new File(fileName);

        if(!file.exists()){
            writer.println("File doesn't exist");
            return;
        }

        try {
            // 1. Gets the name of the file that's about to be copied
            // 2. Then adds them to the destination's path
            // 3. :skull:
            String[] fileSlice = fileName.split("/");
            destination += "/" + fileSlice[fileSlice.length - 1];
            Files.copy(Paths.get(fileName), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);

            writer.println("File copied successfully");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void DELETE(String fileName){
        File file = new File(fileName);

        if(file.delete()){
            writer.println("File deleted");
        }else{
            writer.println("File failed to delete");
        }
    }
}
