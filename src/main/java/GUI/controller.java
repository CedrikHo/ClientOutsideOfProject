package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class controller  {

    //Variables tied to FxId to use for action and events
    //LOGIN IN PAGE FXId's
    public TextField usernameTextBox;
    public Button Login_Button;
    public Button GuestButton;
    public PasswordField passwordTextBox;
    public Text Description1;
    public Text Description2;
    public Text Description3;
    public Text Description4;
    public Text Description5;
    public Text Description6;
    public Text Text_Item1;
    public Text Text_Item2;
    public Text Text_Item5;
    public Text Text_Item4;
    public Text Text_Item3;
    public Text Text_Item6;

    public TextField yourbid_1;
    public Button Bid_button_1;
    public Text current_bid_1;
    public Text Sold1;
    public Text Winner1;
    public Text MinPrice1;
    public Text MessageToUser1;
    public Button Bid_button_2;
    public TextField yourbid_2;
    public Text current_bid_2;
    public Text MinPrice2;
    public Text Sold2;
    public Text MessageToUser2;
    public Text Winner2;
    public Button Bid_button_3;
    public TextField yourbid_3;
    public Text current_bid_3;
    public Text MinPrice3;
    public Text Sold3;
    public Text MessageToUser3;
    public Text Winner3;
    public Button Bid_button_4;
    public TextField yourbid_4;
    public Text current_bid_4;
    public Text MinPrice4;
    public Text Sold4;
    public Text MessageToUser4;
    public Text Winner4;
    public Button Bid_button_5;
    public TextField yourbid_5;
    public Text current_bid_5;
    public Text MinPrice5;
    public Text Sold5;
    public Text MessageToUser5;
    public Text Winner5;
    public Button Bid_button_6;
    public TextField yourbid_6;
    public Text current_bid_6;
    public Text MinPrice6;
    public Text Sold6;
    public Text MessageToUser6;
    public Text Winner6;
    public Text BuyItnow1;

    String winnertxt = "WinnerID: ";
    public Text BuyItnow2;
    public Text BuyItnow3;
    public Text BuyItnow4;
    public Text BuyItnow5;
    public Text BuyItnow6;


    public void SetWinner1(String winnerID) {
        Winner1.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_1(double minPrice) {
        MinPrice1.setText(Double.toString(minPrice));
    }

    public void SetDescription1(String D)  {
        Description1.setText(D);
    }
    public void SetDescription2(String D) {
        Description2.setText(D);
    }
    public void SetDescription3(String  D) {
        Description3.setText(D);
    }
    public void SetDescription4(String  D) {
        Description4.setText(D);
    }
    public void SetDescription5(String  D) {
        Description5.setText(D);
    }
    public void SetDescription6(String  D) {
        Description6.setText(D);
    }
    public void SetItem1(String itemName) {
        Text_Item1.setText(itemName);
    }
    public void SetItem2(String itemName) {
        Text_Item2.setText(itemName);

    }
    public void SetItem3(String itemName) {
        Text_Item3.setText(itemName);

    }
    public void SetItem4(String itemName) {
        Text_Item4.setText(itemName);

    }
    public void SetItem5(String itemName) {
        Text_Item5.setText(itemName);

    }
    public void SetItem6(String itemName) {
        Text_Item6.setText(itemName);
    }

    /////////////////////////////////ITEM 1/////////////////////////

    public void SetMessageToUser1(String ErrorMessage , boolean sw ){
        MessageToUser1.setText(ErrorMessage);
        MessageToUser1.setVisible(sw);
    }
    public void  ClientBid1 (ActionEvent actionEvent) throws Exception {}
    @FXML
    public void BidButtonClicked1(ActionEvent actionEvent) throws Exception {

        Client.BidButtonHit(1 , yourbid_1.getText());
    }
    public void Setcurrent_bid_1(double newPrice){
        current_bid_1.setText(Double.toString(newPrice));
    }
    public void SetSold1(String terminationMessage) {
        Sold1.setText(terminationMessage);
    }


    /////////////////////////////////ITEM 2/////////////////////////
    public void BidButtonClicked2(ActionEvent actionEvent) throws Exception {
        Client.BidButtonHit(2 , yourbid_2.getText());
    }
    public void SetMessageToUser2(String ErrorMessage , boolean sw ){
        MessageToUser2.setText(ErrorMessage);
        MessageToUser2.setVisible(sw);
    }

    public void Setcurrent_bid_2(double newPrice){
        current_bid_2.setText(Double.toString(newPrice));
    }
    public void SetSold2(String terminationMessage) {
        Sold2.setText(terminationMessage);
    }
    public void SetWinner2(String winnerID) {
        Winner2.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_2(double minPrice) {
        MinPrice2.setText(Double.toString(minPrice));
    }




    /////////////////////////////////ITEM 3/////////////////////////

    public void BidButtonClicked3(ActionEvent actionEvent) throws Exception {
        Client.BidButtonHit(3 , yourbid_3.getText());
    }
    public void SetMessageToUser3(String ErrorMessage , boolean sw ){
        MessageToUser3.setText(ErrorMessage);
        MessageToUser3.setVisible(sw);
    }

    public void Setcurrent_bid_3(double newPrice){
        current_bid_3.setText(Double.toString(newPrice));
    }
    public void SetSold3(String terminationMessage) {
        Sold3.setText(terminationMessage);
    }
    public void SetWinner3(String winnerID) {
        Winner3.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_3(double minPrice) {
        MinPrice3.setText(Double.toString(minPrice));
    }

/////////////////////////////ITEM 4///////////////////////////////

    public void BidButtonClicked4(ActionEvent actionEvent) throws Exception {

        Client.BidButtonHit(4 , yourbid_4.getText());
    }

    public void SetMessageToUser4(String ErrorMessage , boolean sw ){
        MessageToUser4.setText(ErrorMessage);
        MessageToUser4.setVisible(sw);
    }

    public void Setcurrent_bid_4(double newPrice){
        current_bid_4.setText(Double.toString(newPrice));
    }
    public void SetSold4(String terminationMessage) {
        Sold4.setText(terminationMessage);
    }
    public void SetWinner4(String winnerID) {
        Winner4.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_4(double minPrice) {
        MinPrice4.setText(Double.toString(minPrice));
    }

/////////////////////ITEM 5//////////////////////////////

    public void BidButtonClicked5(ActionEvent actionEvent) throws Exception {

        Client.BidButtonHit(5, yourbid_5.getText());
    }

    public void SetMessageToUser5(String ErrorMessage , boolean sw ){
        MessageToUser5.setText(ErrorMessage);
        MessageToUser5.setVisible(sw);
    }

    public void Setcurrent_bid_5(double newPrice){
        current_bid_5.setText(Double.toString(newPrice));
    }
    public void SetSold5(String terminationMessage) {
        Sold5.setText(terminationMessage);
    }
    public void SetWinner5(String winnerID) {
        Winner5.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_5(double minPrice) {
        MinPrice5.setText(Double.toString(minPrice));
    }


///////////////////////ITEM 6 ///////////////////////

public void BidButtonClicked6(ActionEvent actionEvent) throws Exception {

    Client.BidButtonHit(6, yourbid_6.getText());
}

    public void SetMessageToUser6(String ErrorMessage , boolean sw ){
        MessageToUser6.setText(ErrorMessage);
        MessageToUser6.setVisible(sw);
    }

    public void Setcurrent_bid_6(double newPrice){
        current_bid_6.setText(Double.toString(newPrice));
    }
    public void SetSold6(String terminationMessage) {
        Sold6.setText(terminationMessage);
    }
    public void SetWinner6(String winnerID) {
        Winner6.setText(winnertxt + winnerID  );
    }
    public void SetMinPrice_6(double minPrice) {
        MinPrice6.setText(Double.toString(minPrice));
    }

///////////////Initilize all BUY NOW////////////////////////////
    String BuyNow = "Buy Now ";
    public void setBuyItnow1(double buyItnow) {
        BuyItnow1.setText( BuyNow + buyItnow);
    }
    public void setBuyItnow2(double buyItnow) {
        BuyItnow2.setText( BuyNow + buyItnow);
    }
    public void setBuyItnow3(double buyItnow) {
        BuyItnow3.setText( BuyNow + buyItnow);
    }
    public void setBuyItnow4(double buyItnow) {
        BuyItnow4.setText( BuyNow + buyItnow);
    }
    public void setBuyItnow5(double buyItnow) {
        BuyItnow5.setText( BuyNow + buyItnow);
    }
    public void setBuyItnow6(double buyItnow) {
        BuyItnow6.setText( BuyNow + buyItnow);
    }

////////////////////////////////////////////////////

























    //Change Scene Method
    public void changeSceenToBidHistory(ActionEvent event) throws IOException {
    }

    public void changeSceenToMainPage(ActionEvent event) throws IOException {
    }

    @FXML
    private void addScene(ActionEvent event)throws IOException{
        Parent view2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }


    /**
     * @param event // Login button changes to Main Page Scene
     * @throws IOException
     */
    @FXML
    public void Login_Button_Clicked(ActionEvent event) throws IOException {
        if (usernameTextBox.getText() == null || usernameTextBox.getText().trim().isEmpty()) {
            Parent view2 = FXMLLoader.load(getClass().getResource("/RoughDraftFinalProject.fxml"));
            Scene scene2 = new Scene(view2);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();

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
        //Need to reset everything in the program so that it is a new session. This is going to hard to do I think
    }


/*
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
*/

}


