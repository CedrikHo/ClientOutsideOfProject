
package Rework;


import GUI.AuctionItem;
import GUI.Message;
import GUI.controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.*;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientReworked extends Application {
    static int USERID=0;
    private static String host = "127.0.0.1";
    private BufferedReader fromServer;
    protected   PrintWriter toServer;
    private Scanner consoleInput = new Scanner((System.in));
    private controller cont;
    ClientReworked () throws Exception {
        USERID++;
        this.setUpNetworking();
        this.cont = cont;//controller
    }
    public static ArrayList<AuctionItem> AuctionItemClientSide;

    void setUpNetworking () throws Exception {
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
                    //   String input = consoleInput.nextLine();


                }
            }
        });
        readerThread.start();
        writerThread.start();
    }

    protected void sendToServer (String string){
        System.out.println("Sending to server: " + string);
        toServer.println(string);
        toServer.flush();
    }

////////////////////////// END OF Initialization
//////////////////////////
//////////////////////////


    protected void processRequest (String input) throws IOException {
        String output = "DefaultMessageIfNoCaseHit";
        Gson gson = new Gson();
        Message message = gson.fromJson(input, Message.class);//Converting Back into a Message
        ParseInputGivenByServer(message.command, message);//This will go to execute task and create response if Needed Use the Output to write back to server
    }

    public void ParseInputGivenByServer (String Commmand, Message OG_MessageFromServer) throws IOException {
        if(OG_MessageFromServer.command.equals("BIDFROMUSER") || OG_MessageFromServer.MakeChange){
            String newPrice =  Integer.toString(OG_MessageFromServer.number);
            //HERE PLATFORM RUN UI CALL
        }

        if (Commmand.equals("Initialization")) {
            InitializeMethod(OG_MessageFromServer);

        }
    }


    public void InitializeMethod (Message OG_MessageFromServer) {
        AuctionItemClientSide = OG_MessageFromServer.ListofAucitonItems;
        String ItemDesciption = AuctionItemClientSide.get(0).ItemDescription;
//Populate the GUI with the stuff the server is selling
    }

    ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
/*
    public static Message CreateResponsePackge (String Commmand){
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



   @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            // set title for the stage
            Stage.setTitle("Pane");
            // create a label
            Label label = new Label("this is Pane example");
            // relocate label
            label.relocate(100, 10);
            // create a Pane
            Pane pane = new Pane(label);
            // add buttons
            for (int i = 0; i < 5; i++) {
                // create button
                Button button = new Button("Button " + (int)(i + 1));
                // add button
                pane.getChildren().add(button);
                // relocate button
                button.relocate(100, 50 + 40 * i);
            }

            // create a scene
            Scene scene = new Scene(pane, 400, 300);

            // set the scene


            Stage.show();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[])
    {
        // launch the application
        launch(args);
    }
}
