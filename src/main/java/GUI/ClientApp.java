package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class ClientApp  extends Application  {
    private static controller MyControllerHandle;
    @Override
        public void start(Stage primaryStage) throws Exception {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
                Parent root = loader.load();
                MyControllerHandle =   loader.getController();

                    Client C = new Client();
                MyControllerHandle.setClientForController(C );//tie the client to my cont

                primaryStage.setTitle("BID_WAR");
                primaryStage.setScene(new Scene(root, 1200, 600));
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    public static controller getControllerIwant() {
        return MyControllerHandle;
    }


    public static void main(String[] args) {
        launch(args); //start GUI
    }
}
