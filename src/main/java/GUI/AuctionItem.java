package GUI;

import java.io.Serializable;

public class AuctionItem implements Serializable {
    static int ItemID = 0;
    //Fields of all the things an Auction Item may have
    int UniqueID; //ID of the Auction Item. Should be the same as index in Array of Auction created on initialization
    String ItemName;
    double SetPrice;
    double MinPrice;
    double CurrentBid;
    boolean Sold;
    double ClosingPrice;
    int WinnerID;//who has won the Item
    String ItemDescription;
    int SavingClientIDForRETURN;


    public AuctionItem(){
        UniqueID= ItemID++;
        SetPrice =-1;
        MinPrice=-1;
        ItemName =null;
        CurrentBid =0;
        WinnerID = -1;
        Sold = false;
        ClosingPrice=-1;
        SetPrice = 0;
        ItemDescription =null;
        SavingClientIDForRETURN=-1;
    }



}
