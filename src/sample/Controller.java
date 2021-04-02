package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    @FXML
    public ListView clientSide;
    public ListView serverSide;

    private ObservableList<String> serverList;
    private ObservableList<String> clientList;

    private File clientDirectory = null;//new File("./resources/client");
    private File serverDirectory = null;//new File("./resources/server");
    private final String serverFolder = "./resources/server/";
    private final String sharedFolder = Main.getFilePath();
    public void initialize(){
        clientDirectory = new File(Main.getFilePath());
        serverDirectory = new File(serverFolder);

        if (clientDirectory == null) {
            System.out.println("shared dir was null");
            clientDirectory = new File("./resources/client/");
        }
        clientSide.setItems(FXCollections.observableArrayList(clientDirectory.list()));
        serverSide.setItems(FXCollections.observableArrayList(serverDirectory.list()));
    }

    public void uploadHandler(ActionEvent actionEvent) throws IOException {
        String fileName = (String) clientSide.getSelectionModel().getSelectedItem();
        String message = "Uploading from client to server";

        File file = new File(sharedFolder + fileName);
        fileTransfer(file, serverFolder, message);

        refresh();
    }

    public void downloadHandler(ActionEvent actionEvent) {
        String fileName = (String) serverSide.getSelectionModel().getSelectedItem();
        String message = "Downloading from server to client";

        File file = new File(serverFolder + fileName);
        fileTransfer(file, sharedFolder, message);

        refresh();
    }

    public void uploadAllHandler(ActionEvent actionEvent) {
        // Setup file paths
        String message = "Uploading from client to server";
        File folder = new File (sharedFolder);

        // upload each file
        for (File file : Objects.requireNonNull(folder.listFiles())){
            if (!file.isDirectory()) {
                fileTransfer(file, serverFolder, message);
            }
        }
        refresh();
    }

    public void downloadAllHandler(ActionEvent actionEvent) {
        // Setup file paths
        String message = "Downloading from server to client";
        File folder = new File(serverFolder);

        // download each file
        for (File file : Objects.requireNonNull(folder.listFiles())){
            if (!file.isDirectory()) {
                fileTransfer(file, sharedFolder, message);
            }
        }
        refresh();
    }

    private void fileTransfer(File file, String outFolder, String message){
        Socket socket = null;

        try {
            // Setup Socket
            socket = new Socket("localhost", 8080);

            // Setup input and output stream
            FileInputStream inStream = new FileInputStream(file);
            FileOutputStream outStream = new FileOutputStream(outFolder+file.getName());
            InputStreamReader reader = new InputStreamReader(inStream, "UTF-8");

            // Output message
            var printOut = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
            printOut.println(message);
            printOut.flush();

            // Read and write characters
            int character;
            while ((character = reader.read()) != -1) {
                outStream.write((char) character);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {
        clientSide.setItems(FXCollections.observableArrayList(clientDirectory.list()));
        serverSide.setItems(FXCollections.observableArrayList(serverDirectory.list()));
    }

    // Getters and Setters
    public File getClientDirectory() {
        return clientDirectory;
    }

    public void setClientDirectory(File clientDirectory) {
        this.clientDirectory = clientDirectory;
    }

    public File getServerDirectory() {
        return serverDirectory;
    }

    public void setServerDirectory(File serverDirectory) {
        this.serverDirectory = serverDirectory;
    }
}
