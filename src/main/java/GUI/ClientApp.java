package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ClientApp  extends Application  {
    public Viewer View;
    public static int ClientNumber;
    @Override
        public void start(Stage primaryStage) throws Exception {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoughDraftFinalProject.fxml"));
                Parent root = loader.load();
                Client C =  new Client(loader.getController());
                View = new Viewer( loader.getController(),C ); //Tie the controller and the Client Together

                //Try and trick Log in to set up a page by creating new scene and when button is clicked move on


                primaryStage.setTitle("BID_WAR");
                primaryStage.setScene(new Scene(root, 820, 600));
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }



    public static void main(String[] args) {
        launch(args); //start GUI
    }
}
