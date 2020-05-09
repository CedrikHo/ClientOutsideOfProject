package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class controller  {

    private Client ClientForController;

    public void setClientForController(Client clientForController) {
        ClientForController = clientForController;
    }

    //Variables tied to FxId to use for action and events
    //LOGIN IN PAGE FXId's
    @FXML
    public TextField usernameTextBox;
    @FXML
    public Button Login_Button;
    @FXML
    public Button GuestButton;
    @FXML
    public PasswordField passwordTextBox;
    @FXML
    public Label Description_1;
    @FXML
    public Text Text_Item1;
    @FXML
    public TextField TimeLeft1;

    public controller() throws Exception {
    }

    public void SetDescription1( String DescriptionFromServer) {
        Description_1.setText(DescriptionFromServer);
    }


    //Change Scene Method
    public void changeSceenToBidHistory(ActionEvent event) throws IOException {
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



    public void Guest_Button_Clicked(ActionEvent event) throws IOException{
        usernameTextBox.clear();
        passwordTextBox.clear();
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


    public void ClientBid2(ActionEvent actionEvent) {
    }

    public void BidButton2Clicked(ActionEvent actionEvent) {
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


    public void ClientBid6(ActionEvent actionEvent) {
    }

    public void BidButton6Clicked(ActionEvent actionEvent) {
    }

    public void BidButton1Clicked(ActionEvent actionEvent) throws Exception {
    Client c = new Client();
    c.BidButtonHit(1);
        Message BIDFROMUSER = new Message("BIDFROMUSER", 1, Client.USERID, Client.AuctionItemClientSide);
        c.setObj(BIDFROMUSER);
    }

    public void ClientBid1(ActionEvent actionEvent) {
    }


}


