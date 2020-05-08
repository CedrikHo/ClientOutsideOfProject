package GUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class Client  {

  private static String host = "127.0.0.1";
  private BufferedReader fromServer;
  private PrintWriter toServer;
  private Scanner consoleInput = new Scanner((System.in));
  private controller myController;

 // FXMLDocumentController controller
  public Client () throws Exception {

    this.setUpNetworking();
    loader.get
    String [] args = {""};
    controller.main(args);
    //Stage DummyStage = new Stage();
    //myController.start(DummyStage);
  }
  public static void main(String[] args) throws Exception {
    new Client();
  }

  public void InitializeGUI() {
    //Should send Command to CommandLine of Client. Need to send the specific command "Initialize"
    //Need to some how tie internal commands to be seen as the input from the command line...
  }

  protected void sendToServer(String string) {
    System.out.println("Sending to server: " + string);
    toServer.println(string);
    toServer.flush();
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
          String input = consoleInput.nextLine();
          Message MessageSpecificToTheCommand = ParseCommandLineForSpecificInstructionandBuildMessage(input);
          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.create();
          sendToServer(gson.toJson(MessageSpecificToTheCommand));
        }
      }
    });

    readerThread.start();
    writerThread.start();
  }

  /////////////////////////////////////////////
  //BELOW IS ALL THE METHODS TO READ STUFF FROM SERVER AND HOW TO REACT TO IT.
  protected void processRequest(String input) {
    String output = "DefaultMessageIfNoCaseHit";
    Gson gson = new Gson();
    Message message = gson.fromJson(input, Message.class);//Converting Back into a Message
    ParseInputGivenByServer(message.command, message);//This will go to execute task and create response if Needed Use the Output to write back to server
  }


  public void ParseInputGivenByServer(String Commmand, Message OG_MessageFromServer) {
    //This Method Parses the command call received from the server.
    // Consider this our Client side command line if you will...Theoretically users could use this method to control all their actions.
    // The case statements in here may do the following:
    // 1) call a method to take care of UI updates.
    // 2) call helper methods to manipulate data we have local
    // 3) call a method to send back a message specific to what the server asked for. I still need to work out the details of this here.

    //Things to be changed
    //->Bid price if valid response is received.
    //->History of USERs Valid bids in some sort of ArrayList or Format

    //All of this can be in the Function that reads the Bid return from Server Command. This would be the command Server sends back after we send ours we button hit.
    //Pop up window if Invalid bid needs to have platform.run to Modify Io
    //Modify Highest bid textBox. again platform.run
    //Modify Sold Text Box again platform.run
    if (Commmand.equals("Initialization")) {
      InitializeMethod(OG_MessageFromServer);
      ParseCommandLineForSpecificInstructionandBuildMessage("Initialization");
      //METHOD TO DO STUFF
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////METHODS THAT CHANGE THE UI based on info given by the SERVER
  public void InitializeMethod(Message OG_MessageFromServer) {
    //Place HOLDER for method that modifies Variables on the Client side such as Modifying the UI or his local History.
    //Here we can update the Descriptions of Every one.
    //Using OG_MessageFromServer we can reconstruct what was sent over hopefully and use it on this side.
    ArrayList<AuctionItem> AuctionItemClientSide = OG_MessageFromServer.ListofAucitonItems;

    //Change UI HERE for descriptions.
    String ItemDesciption = AuctionItemClientSide.get(0).ItemDescription;
    try {
      myController.SetDescription1( ItemDesciption);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
  //END of METHODS THAT CHANGE THE UI based on info given by the SERVER
  ///////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////

  ///////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  //METHODS that manipulate local variables and data.


  ///////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////

  public Message ParseCommandLineForSpecificInstructionandBuildMessage(String Commmand) {
    if (Commmand.equals("KICKOFF")) { //Method to Write back to
      String words = "KICKOFF";
      Message InitialMessageToGetGUI = new Message(words);
      return InitialMessageToGetGUI;
    }

    if (Commmand.equals("Initialization")) {
      String words = "Initialization_WAS_RECEIVED_AND_SHOULD_BE_COMPLETE";
      Message ConfirmationMessageofGUIKickoff = new Message(words);
      return ConfirmationMessageofGUIKickoff;
    }

    Message Default = new Message("NoDATA");
    return Default;
  }


}
