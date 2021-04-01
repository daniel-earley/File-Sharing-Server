package sample;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private ServerSocket serverSocket = null;
    private static File sharedFolder = null;
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

//        if (args.length > 0){
//            // first arg is computer name
//            // 2nd arg is shared folder (server folder)
//            System.out.println("Welcome " + args[0]);
//            if (args.length > 1) {
//                System.out.printf("File path is -> %s\n", args[1]);
//                setSharedFolder(new File(args[1]));
//            }
//        }

        try {
            FileServer server = new FileServer(port);
            server.handleRequests();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static File getSharedFolder() {
        return sharedFolder;
    }

    public static void setSharedFolder(File folder) {
        sharedFolder = folder;
    }

}
