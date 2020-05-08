package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApp  extends Application  {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws IOException {
        try {
            new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args); //start GUI
    }
}
