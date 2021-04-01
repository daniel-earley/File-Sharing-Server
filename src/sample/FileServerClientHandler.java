package sample;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileServerClientHandler implements Runnable{

    private Socket socket = null;
    private BufferedReader requestInput = null;
    private DataOutputStream responseOutput = null;

    public FileServerClientHandler(Socket socket) throws IOException{
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new DataOutputStream(socket.getOutputStream());
    }

    public void run(){
        try {
            Scanner inStream = new Scanner(socket.getInputStream());
            System.out.println(inStream.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            fileTransfer(socket);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void fileTransfer(Socket socket) throws IOException{
        // copy file contents into another file
        Writer writer = null;
        try {
//            FileReader fileInput = new FileReader(file);
            var input = new BufferedInputStream(socket.getInputStream());
//            writer = new BufferedWriter(new FileWriter(fileCopy));
            var output = new FileOutputStream("./resources/server/");
            String line;
            byte[] buffer = new byte[4096];
            int count;
            while((count = input.read(buffer)) > 0){
                output.write(buffer, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

//    public void fileTransfer(Socket socket){
//        DataInputStream inStream = new DataInputStream(socket.getInputStream());
//        FileOutputStream outStream = new FileOutputStream()
//    }

}
