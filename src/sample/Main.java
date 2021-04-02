package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {

    public Stage primaryStage = null;
    public static String filePath;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("File Sharer");
        primaryStage.setScene(new Scene(root, 500, 500));
        setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0){
            // first arg is computer name
            // 2nd arg is shared folder (server folder)
            System.out.println("Welcome " + args[0]);
            if (args.length > 1) {
                System.out.printf("File path is -> %s\n", args[1]);
                setFilePath(args[1]);
            }
        }
        launch(args);
    }

    public static void sendFilePath(String filePath){
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            var output = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
            output.println(filePath);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String path) {
        if (!path.endsWith("/")){
            filePath = path + "/";
        } else {
            filePath = path;
        }
        System.out.println(filePath);
    }


}
