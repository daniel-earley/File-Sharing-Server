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
    }


}
