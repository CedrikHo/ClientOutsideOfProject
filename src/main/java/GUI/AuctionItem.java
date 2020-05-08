package GUI;

import java.io.Serializable;

public class AuctionItem implements Serializable {
    static int ItemID =0;
    //Fields of all the things an Auction Item may have
    int UniqueID;
    String ItemName;
    int MinPrice;
    int CurrentBid;
    boolean Sold;
    double timeLeft;
    boolean OutofTime;
    String LastBidder;
    String WinnerID;//who has won the Item
    String ItemDescription;

    public AuctionItem(){
        UniqueID= ItemID++; //When creating Auction Item I never want to confuse by name so just give it an ID.
        ItemName =null;
        CurrentBid =0;
        LastBidder = null;
        Sold = false;
        WinnerID = null;
        OutofTime = false;
    }



}
