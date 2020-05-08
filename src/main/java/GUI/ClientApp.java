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
         Client temp =  new Client();
            temp.setUpNetworking();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        primaryStage.setTitle("BID_WAR");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
        //Send over all the descriptions and Maybe Images of the Items I have just built

    }
    public static void main(String[] args) {
        launch(args); //start GUI
    }
}
