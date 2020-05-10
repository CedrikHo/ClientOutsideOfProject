package GUI;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
  String command;
  String input;
  int number;
  AuctionItem Item;
  ArrayList<AuctionItem> ListofAucitonItems;
  int USERID;
  boolean MakeChange;
  int AuctionID;
  AuctionItem A;
  double BidPrice;

  public Message(String type ) {
    this.command = type;
    this.input = null;
    this.USERID = 0;
    this.input = "";
    this.number = 0;
    this.Item = null;
    this.MakeChange = false;
    this.ListofAucitonItems =null;
    System.out.println("Client side message");
  }

  public Message(String type, int AuctionID ,int USERID , ArrayList<AuctionItem> listofAucitonItems, double BidPrice) {
    this.AuctionID=AuctionID;
    this.command = type;
    this.input = null;
    this.USERID = USERID;
    this.input = "";
    this.number = 0;
    this.Item = null;
    this.MakeChange = false;
    this.BidPrice = BidPrice;
    this.ListofAucitonItems=listofAucitonItems;
    System.out.println("Client side message");}


  protected Message() {}

  protected Message(String type , int USERID , AuctionItem Item) {
    this.USERID = USERID;
    this.command = type;
    this.input = "";
    this.number = 0;
    this.Item = Item;
    this.ListofAucitonItems =null;
    System.out.println("Client side message");
  }

  protected Message(String type,  int USERID , AuctionItem Item, boolean MakeChange) {
    this.USERID = USERID;
    this.command = type;
    this.input = "";
    this.number = 0;
    this.Item = Item;
    this.MakeChange = MakeChange;
    this.ListofAucitonItems =null;
    System.out.println("Client side message");
  }


  public Message(String type, String input, int number) {
    this.command = type;
    this.input = input;
    this.number = number;
    this.USERID = 0;
    this.input = "";
    this.number = 0;
    this.Item = null;
    this.MakeChange = false;
    this.ListofAucitonItems =null;
    System.out.println("Client side message");
  }


  public Message(String type, ArrayList<AuctionItem> listofAucitonItems) {
    this.command = type;
    this.input = null;
    this.USERID = 0;
    this.input = "";
    this.number = 0;
    this.Item = null;
    this.MakeChange = false;
    this.ListofAucitonItems =listofAucitonItems;
    System.out.println("Client side message");}


}