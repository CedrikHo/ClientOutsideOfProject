package GUI;

import GUI.AuctionItem;
import GUI.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class Client {

    static int USERID = 0;
    private static String host = "127.0.0.1";
    private BufferedReader fromServer;
    protected PrintWriter toServer;
    private static controller Mycontroller;

    //private Scanner consoleInput = new Scanner((System.in));

    static String commandPasedToConsole = "NoCommand";
    InputStream stdin = System.in;

    // public BufferedReader LikeConsoleBuffer; //added by me
    //LikeConsoleBuffer = new BufferedReader(new InputStreamReader(LikeConsole));
    //private Scanner consoleInput = new Scanner((LikeConsole));

    Client() throws Exception {
        USERID++;
        this.setUpNetworking();
    }
    Client(controller Mycontroller) throws Exception {
        USERID++;
        commandPasedToConsole = "KICKOFF";
        this.setUpNetworking();
        Client.Mycontroller = Mycontroller;
        System.out.println(USERID);
    }




    protected void sendToServer(String string) {
        System.out.println("Sending to server: " + string);
        toServer.println(string);
        toServer.flush();
        commandPasedToConsole = "NoCommand";//reset command after each message as to not keep sending message

    }

    void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        Socket socket = new Socket(host, 4242);
        System.out.println("Connecting to... " + socket);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream());


        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String input;
                try {
                    while ((input = fromServer.readLine()) != null) {
                        System.out.println("From server: " + input);
                        processRequest(input);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread writerThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    System.setIn(new ByteArrayInputStream(commandPasedToConsole.getBytes()));
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    if (!input.equals("NoCommand")) {
                        Message MessageSpecificToTheCommand = CreateMessageBasedOnCommand(input);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        sendToServer(gson.toJson(MessageSpecificToTheCommand));
                    }
                }
            }
        });
        readerThread.start();
        writerThread.start();
    }


    /////////////////////////////////////////////
    //BELOW IS ALL THE METHODS TO READ STUFF FROM SERVER AND HOW TO REACT TO IT.
    protected void processRequest(String input) throws IOException {
        //Helper Method to standardize GSON to Message Class before doing any "Work" for the class.
        String output = "DefaultMessageIfNoCaseHit";
        Gson gson = new Gson();
        Message message = gson.fromJson(input, Message.class);//Converting Back into a Message
        ParseInputGivenByServer(message.command, message);//This will go to execute task and create response if Needed Use the Output to write back to server
    }



    public ArrayList<AuctionItem> localAuctionList;
    public void ParseInputGivenByServer(String Commmand, Message OG_MessageFromServer) throws IOException {
        localAuctionList =OG_MessageFromServer.ListofAucitonItems; //Copy servers list to my list
        System.out.println("valid bid has been hit and would update here");//DEBUG
        //Parse The Auction list for the item based on the Unique Number given back. Now update UI based on options
        //update SOLD UNSOLD (sold parameter of Auction Item)
        //update VALID INVALID BID (USERID, makeChange field from Message class)
        //update WINNER ID (sold status and Winner id filed from AuctionItem class)
        Mycontroller.SetMessageToUser1("No Message",  false);
        Mycontroller.SetMessageToUser2("No Message",  false);
        Mycontroller.SetMessageToUser3("No Message",  false);
        Mycontroller.SetMessageToUser4("No Message",  false);
        Mycontroller.SetMessageToUser5("No Message",  false);
        Mycontroller.SetMessageToUser6("No Message",  false);

        if (OG_MessageFromServer.command.equals("BIDFROMUSER")) {
            double newPrice = OG_MessageFromServer.BidPrice;
            AuctionItem ItemThatWasBidOn = OG_MessageFromServer.ListofAucitonItems.get(OG_MessageFromServer.AuctionID);
            int USERID_of_Person_Who_Bid =    ItemThatWasBidOn.WinnerID;
            int ItemThatWasBidOn_AuctionID = OG_MessageFromServer.AuctionID;

            switch(ItemThatWasBidOn_AuctionID) {
                case 0:
                    Mycontroller.SetMessageToUser1("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_1(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold1(terminationMessage);
                            Mycontroller.SetWinner1(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_1(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser1("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold){
                        Mycontroller.SetMessageToUser1("SOLD ALREADY!", true);
                    }
                    break;
                case 1:
                    Mycontroller.SetMessageToUser2("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_2(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold2(terminationMessage);
                            Mycontroller.SetWinner2(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_2(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser2("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold){
                        Mycontroller.SetMessageToUser2("SOLD ALREADY!", true);
                    }
                    break;
                case 2:
                    Mycontroller.SetMessageToUser3("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_3(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold3(terminationMessage);
                            Mycontroller.SetWinner3(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_3(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser3("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold){
                        Mycontroller.SetMessageToUser3("SOLD ALREADY!", true);
                    }
                    break;

                case 3:
                    Mycontroller.SetMessageToUser4("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_4(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold4(terminationMessage);
                            Mycontroller.SetWinner4(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_4(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser4("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold){
                        Mycontroller.SetMessageToUser4("SOLD ALREADY!", true);
                    }
                    break;

                case 4:Mycontroller.SetMessageToUser5("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_5(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold5(terminationMessage);
                            Mycontroller.SetWinner5(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_5(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser5("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold) {
                        Mycontroller.SetMessageToUser5("SOLD ALREADY!", true);
                    }
                    break;
                case 5: Mycontroller.SetMessageToUser6("No Message",  false);
                    if(OG_MessageFromServer.MakeChange ) {
                        Mycontroller.Setcurrent_bid_6(newPrice);
                        if (ItemThatWasBidOn.Sold ) {//update UI for the SOLD category
                            String terminationMessage = "SOLD";
                            Mycontroller.SetSold6(terminationMessage);
                            Mycontroller.SetWinner6(Integer.toString(USERID_of_Person_Who_Bid));
                            Mycontroller.Setcurrent_bid_6(newPrice);
                        }
                    }
                    if(OG_MessageFromServer.tooLow ){
                        //Warning that MinPrice is Too low
                        Mycontroller.SetMessageToUser6("REJECTED, BID TO LOW",  true);
                    }
                    if(OG_MessageFromServer.AlreadySold) {
                        Mycontroller.SetMessageToUser6("SOLD ALREADY!", true);
                    }
                default:
            }

        }

        if (Commmand.equals("Initialization")) {
            InitializeMethod(OG_MessageFromServer);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    //BELOW ARE various functions that may do calculations or update local values.
    // It also contain initiliazation of entire bid sites UI elements. Some functions may require some more "Leg work"
    //before giving a response or modifying a UI elements.
    //One of those functions could include checking to see if the Bidder who go rejected was me, and thus a UI "Rejection" is in order.
    ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<AuctionItem> AuctionItemClientSide;

    ////METHODS THAT CHANGE THE UI based on info given by the SERVER
    public void InitializeMethod(Message OG_MessageFromServer) throws IOException {
        //Method that creates local side copy of all items for sale.
        //NOTE that is not Dynamically allocated, this is very much hard coded.
        AuctionItemClientSide = OG_MessageFromServer.ListofAucitonItems;

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF FIRST AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////
        String ItemDesciption = AuctionItemClientSide.get(0).ItemDescription;
        Mycontroller.SetDescription1(ItemDesciption);

        if (AuctionItemClientSide.get(0).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold1("SOLD");
            Mycontroller.SetWinner1(Integer.toString(AuctionItemClientSide.get(0).WinnerID));
        }

        else{ Mycontroller.SetSold1("UNSOLD");
            Mycontroller.SetWinner1("None");}

        Mycontroller.Setcurrent_bid_1(AuctionItemClientSide.get(0).CurrentBid);
        Mycontroller.SetMinPrice_1(AuctionItemClientSide.get(0).MinPrice);
        Mycontroller.SetMessageToUser1("No Message",  false);

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF SECOND AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////

        String ItemDesciption2 = AuctionItemClientSide.get(1).ItemDescription;
        Mycontroller.SetDescription2(ItemDesciption2);

        if (AuctionItemClientSide.get(1).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold2("SOLD");
            Mycontroller.SetWinner2(Integer.toString(AuctionItemClientSide.get(1).WinnerID));
        }

        else{ Mycontroller.SetSold2("UNSOLD");
            Mycontroller.SetWinner2("None");}

        Mycontroller.Setcurrent_bid_2(AuctionItemClientSide.get(1).CurrentBid);
        Mycontroller.SetMinPrice_2(AuctionItemClientSide.get(1).MinPrice);
        Mycontroller.SetMessageToUser2("No Message",  false);

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF THIRD AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////

        String ItemDesciption3 = AuctionItemClientSide.get(2).ItemDescription;
        Mycontroller.SetDescription3(ItemDesciption3);
        if (AuctionItemClientSide.get(2).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold3("SOLD");
            Mycontroller.SetWinner3(Integer.toString(AuctionItemClientSide.get(2).WinnerID));
        }

        else{ Mycontroller.SetSold3("UNSOLD");
            Mycontroller.SetWinner3("None");}

        Mycontroller.Setcurrent_bid_3(AuctionItemClientSide.get(2).CurrentBid);
        Mycontroller.SetMinPrice_3(AuctionItemClientSide.get(2).MinPrice);
        Mycontroller.SetMessageToUser3("No Message",  false);

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF FOURTH AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////


        String ItemDesciption4 = AuctionItemClientSide.get(3).ItemDescription;
        Mycontroller.SetDescription4(ItemDesciption4);

        if (AuctionItemClientSide.get(3).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold4("SOLD");
            Mycontroller.SetWinner4(Integer.toString(AuctionItemClientSide.get(3).WinnerID));
        }

        else{ Mycontroller.SetSold4("UNSOLD");
            Mycontroller.SetWinner4("None");}

        Mycontroller.Setcurrent_bid_4(AuctionItemClientSide.get(3).CurrentBid);
        Mycontroller.SetMinPrice_4(AuctionItemClientSide.get(3).MinPrice);
        Mycontroller.SetMessageToUser4("No Message",  false);

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF FIFTH AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////
        String ItemDesciption5 = AuctionItemClientSide.get(4).ItemDescription;
        Mycontroller.SetDescription5(ItemDesciption5);

        if (AuctionItemClientSide.get(4).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold5("SOLD");
            Mycontroller.SetWinner5(Integer.toString(AuctionItemClientSide.get(4).WinnerID));
        }

        else{ Mycontroller.SetSold5("UNSOLD");
            Mycontroller.SetWinner5("None");}

        Mycontroller.Setcurrent_bid_5(AuctionItemClientSide.get(4).CurrentBid);
        Mycontroller.SetMinPrice_5(AuctionItemClientSide.get(4).MinPrice);
        Mycontroller.SetMessageToUser5("No Message",  false);

        //////////////////////////////////////////////////////////////
        ////INITIALIZATION OF SIXTH AUCTION ITEM/////////////////////
        /////////////////////////////////////////////////////////////

        String ItemDesciption6 = AuctionItemClientSide.get(5).ItemDescription;
        Mycontroller.SetDescription6(ItemDesciption6);

        if (AuctionItemClientSide.get(5).Sold) {//update UI for the SOLD category
            Mycontroller.SetSold6("SOLD");
            Mycontroller.SetWinner6(Integer.toString(AuctionItemClientSide.get(5).WinnerID));
        }

        else{ Mycontroller.SetSold6("UNSOLD");
            Mycontroller.SetWinner6("None");}

        Mycontroller.Setcurrent_bid_6(AuctionItemClientSide.get(5).CurrentBid);
        Mycontroller.SetMinPrice_6(AuctionItemClientSide.get(5).MinPrice);
        Mycontroller.SetMessageToUser6("No Message",  false);

        /////////////////////////////////////////////////////////////
        /////////////////SET ALL ITEM NAMES 1-6 /////////////////////
        String SetItem1 = AuctionItemClientSide.get(0).ItemName;
        Mycontroller.SetItem1(SetItem1);
        String SetItem2 = AuctionItemClientSide.get(1).ItemName;
        Mycontroller.SetItem2(SetItem2);
        String SetItem3 = AuctionItemClientSide.get(2).ItemName;
        Mycontroller.SetItem3(SetItem3);
        String SetItem4 = AuctionItemClientSide.get(3).ItemName;
        Mycontroller.SetItem4(SetItem4);
        String SetItem5 = AuctionItemClientSide.get(4).ItemName;
        Mycontroller.SetItem5(SetItem5);
        String SetItem6 = AuctionItemClientSide.get(5).ItemName;
        Mycontroller.SetItem6(SetItem6);

        double BuyItnow1 = AuctionItemClientSide.get(0).SetPrice;
        Mycontroller.setBuyItnow1(BuyItnow1);
        double BuyItnow2 = AuctionItemClientSide.get(1).SetPrice;
        Mycontroller.setBuyItnow2(BuyItnow2);
        double BuyItnow3 = AuctionItemClientSide.get(2).SetPrice;
        Mycontroller.setBuyItnow3(BuyItnow3);
        double BuyItnow4 = AuctionItemClientSide.get(3).SetPrice;
        Mycontroller.setBuyItnow4(BuyItnow4);
        double BuyItnow5 = AuctionItemClientSide.get(4).SetPrice;
        Mycontroller.setBuyItnow5(BuyItnow5);
        double BuyItnow6 = AuctionItemClientSide.get(5).SetPrice;
        Mycontroller.setBuyItnow6(BuyItnow6);
    }
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

    public static  ArrayList<AuctionItem>  DummyAuctionList=null;
    public static final int DefaultBidPrice =-1;
    public static final int DefaultUSERID =-1;
    public static final int DefaultActionID = -1;
    public  static Message CreateMessageBasedOnCommand(String Commmand) {
        if (Commmand.equals("KICKOFF")) { //Method to Write back to
            String command = "KICKOFF";
            //String type, int AuctionID, int USERID, ArrayList<AuctionItem> listofAucitonItems, double BidPrice)
            Message InitialMessageToGetGUI = new Message(command,DefaultActionID, DefaultUSERID,DummyAuctionList, DefaultBidPrice);

            return InitialMessageToGetGUI;
        }

        if (Commmand.equals("Initialization")) {
            String command = "Initialization_WAS_RECEIVED_AND_SHOULD_BE_COMPLETE";
            Message ConfirmationMessageofGUIKickoff = new Message(command,DefaultActionID, DefaultUSERID,DummyAuctionList, DefaultBidPrice);
            return ConfirmationMessageofGUIKickoff;
        }
        if (Commmand.equals("BIDFROMUSER")) {
            return MessageGoingOutCurrently;//Because already built earlier in our function dealing wit Button Clicks
        }
        Message Default_Message  = new Message("NO_MESSAGE_WAS_CREATED",DefaultActionID, DefaultUSERID,DummyAuctionList,  DefaultBidPrice);

        return Default_Message;
    }


///////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////

    static Message MessageGoingOutCurrently;//The sole message being send out. Each Client is only sending one message at a time anyways
    //so Static reference is of no worries.
//Handler for controller class for A Bid Button Being hit.
    public static void BidButtonHit (int ButtonNumber, String BidPrice_string)  {
        double BidPrice=0.0;
        if(BidPrice_string.equals("")){  return; }

       //Here could handle case where letters are Inputed but Not really necessary

        if (isInteger(BidPrice_string)) {
            Integer IntBidPrice = Integer.parseInt(BidPrice_string);
            BidPrice =  IntBidPrice.doubleValue();
        } else if (isDouble(BidPrice_string)){
            BidPrice = Double.parseDouble(BidPrice_string);
        }
        String Command = "BIDFROMUSER";
        int ActionID = AuctionItemClientSide.get(ButtonNumber-1).UniqueID;//get Auction ID to know what object
        MessageGoingOutCurrently = new Message(Command, ActionID, USERID, AuctionItemClientSide, BidPrice);
        commandPasedToConsole= "BIDFROMUSER";
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
    }

}
