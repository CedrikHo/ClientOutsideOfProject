package GUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

class Client{
    static int USERID=0;
  private static String host = "127.0.0.1";
  private BufferedReader fromServer;
  protected   PrintWriter toServer;
  private Scanner consoleInput = new Scanner((System.in));

Client () throws Exception {
    USERID++;
    this.setUpNetworking();
}


        protected void  sendToServer (String string){
            System.out.println("Sending to server: " + string);
            toServer.println(string);
            toServer.flush();
        }

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
        /////////////////////////////////////////////
        //BELOW IS ALL THE METHODS TO READ STUFF FROM SERVER AND HOW TO REACT TO IT.
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
                CreateResponsePackge("Initialization");
            }
        }


    public static ArrayList<AuctionItem> AuctionItemClientSide;

    ////METHODS THAT CHANGE THE UI based on info given by the SERVER
        public void InitializeMethod (Message OG_MessageFromServer) {
            AuctionItemClientSide = OG_MessageFromServer.ListofAucitonItems;
            String ItemDesciption = AuctionItemClientSide.get(0).ItemDescription;

        }

    ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

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


    public void  BidButtonHit(int ButtonNumber) throws IOException {
            Message BIDFROMUSER = new Message( "BIDFROMUSER", ButtonNumber, USERID, AuctionItemClientSide );
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            sendToServer(gson.toJson(BIDFROMUSER));
                    }


   public static void main (String[]args) throws Exception {

    }

    ///NOT SURE WE NEED THIS
    FileOutputStream out = new FileOutputStream("test.txt");
    ObjectOutputStream oout = new ObjectOutputStream(out);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
    public void setObj(Message m) throws IOException {
        oout.writeObject(m);
    }

}
