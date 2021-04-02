package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileServer {
    private static ServerSocket serverSocket = null;
    private int port;

    public FileServer(){}

    public FileServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    public void handleRequests() throws IOException{
        System.out.println("Listening to port: " + port);

        // Create a thread to handle each client
        while(true){
            Socket clientSocket = serverSocket.accept();
            FileServerClientHandler handler = new FileServerClientHandler(clientSocket);
            Thread handlerThread = new Thread(handler);
            handlerThread.start();
        }
    }

    public static void main(String[] args){
        int port = 8080;

        try {
            FileServer server = new FileServer(port);
            server.handleRequests();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
