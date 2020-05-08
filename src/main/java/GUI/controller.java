package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class controller extends Application {

//Variables tied to FxId to use for action and events
    //LOGIN IN PAGE FXId's
    public TextField usernameTextBox;
    public Button Login_Button;
    public Button GuestButton;
    public PasswordField passwordTextBox;
//END OF LOGIN PAGE FXId's
@Override
public void start(Stage primaryStage) throws Exception {
    try {

      Parent root = FXMLLoader.load( getClass().getResource("/LoginPage.fxml")) ;
        primaryStage.setTitle("BID_WAR");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        // starts the FX toolkit, instantiates this class,
        // and calls start(...) on the FX Application thread:
        launch(args);
    }

    //Change Scene Method
    public void changeSceenToBidHistory(ActionEvent event) throws IOException {

        Parent BidHistory = FXMLLoader.load(getClass().getResource("/BidHistory.fxml"));
        Scene BidHistoryScene = new Scene((BidHistory));
        Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Window.setScene(BidHistoryScene);
        Window.show();
    }

    /**
     * /*
     * THIS IS A DUMMY EXAMPLE FOR TA
     */
    public void changeSceenToBidHistoryDUMMYFORTA(ActionEvent event) throws IOException {
        //SEND JSON REPSONSE THAT SCENE NEEDS TO BE CHANGED. IN CLIENT HANDLER call this function instead

        Parent BidHistory = FXMLLoader.load(getClass().getResource("/BidHistory.fxml"));
        Scene BidHistoryScene = new Scene((BidHistory));
        Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Window.setScene(BidHistoryScene);
        Window.show();
    }

    public void changeSceenToMainPage(ActionEvent event) throws IOException {
        Parent MainPage = FXMLLoader.load(getClass().getResource("/RoughDraftFinalProject.fxml"));
        Scene MainPageScene = new Scene((MainPage));
        Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Window.setScene(MainPageScene);
        Window.show();
    }

    /**
     * @param event // Login button changes to Main Page Scene
     * @throws IOException
     */
    public void Login_Button_Clicked(ActionEvent event) throws IOException {

        if (usernameTextBox.getText() == null || usernameTextBox.getText().trim().isEmpty()) {
         //Check to see if UserName is Empty, if not Empty then just
            Parent MainPage = FXMLLoader.load(getClass().getResource("/RoughDraftFinalProject.fxml"));
            Scene MainPageScene = new Scene((MainPage));
            Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Window.setScene(MainPageScene);
            Window.show();
            usernameTextBox.clear();
            passwordTextBox.clear();
        }
        else {
            //here need to tell user name is not optional or use Guest

            usernameTextBox.clear();
            passwordTextBox.clear();

        }
    }

    /**
     *
     * @param event saves Password given for User.
     *              NOTE hand does not mandate to use the password so this may be useless if I don't get to it
     * @throws IOException
     */
    public void getPassword(ActionEvent event) throws IOException {
        String PassWord = passwordTextBox.getText();
    }

    /**
     *
     * @param event save username given, this should be passed on to Client when creating a client
     * @throws IOException
     */
    public void getUserName(ActionEvent event) throws IOException{
        String USER_ID = usernameTextBox.getText();
}

    /**
     *
     * @param event Provided that Credentials are acceptable we LOGIN
     *
     * @throws IOException
     */


    public int User_ID_Bank =0;

    public void Guest_Button_Clicked(ActionEvent event) throws IOException{
        //Create a new client to connect to the server with the credentials given.
        String USER_ID = Integer.toString (User_ID_Bank);
        usernameTextBox.clear();
        passwordTextBox.clear();
        User_ID_Bank++;//make sure the variable is incremented so we never use same ID again.
        changeSceenToMainPage(event);
    }
    public void LogOutButtonClicked(ActionEvent event) throws IOException{
        //Got back to the main page.
        changeSceenToMainPage(event);
        Parent MainPage = FXMLLoader.load(getClass().getResource("/RoughDraftFinalProject.fxml"));
        Scene MainPageScene = new Scene((MainPage));
        Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Window.setScene(MainPageScene);
        Window.show();
        //Need to reset everything in the program so that it is a new session. This is going to hard to do I think

    }


    public void BidButton1Clicked(ActionEvent actionEvent) {
        //Button clicked
        //Should share single method for all 5 buttons.
        //Formulate some sort of JSON response and send to Server.
        //Server needs to have synchronization so tha that bids can happen at the same time.

    }

    public void BidButton3Clicked(ActionEvent actionEvent) {
    }

    public void ClientBid3(ActionEvent actionEvent) {
    }

    public void ClientBid4(ActionEvent actionEvent) {
    }

    public void BidButton4Clicked(ActionEvent actionEvent) {
    }

    public void ClientBid5(ActionEvent actionEvent) {
    }

    public void BidButton5Clicked(ActionEvent actionEvent) {
    }

    public void ClientBid2(ActionEvent actionEvent) {
    }

    public void BidButton2Clicked(ActionEvent actionEvent) {
    }

    public void ClientBid1(ActionEvent actionEvent) {
    }

    public void ClientBid6(ActionEvent actionEvent) {
    }

    public void BidButton6Clicked(ActionEvent actionEvent) {
    }
}


