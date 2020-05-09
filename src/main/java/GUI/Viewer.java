package GUI;

public class Viewer {//Class that ties my Client to its respected Controler Created by the Root.
    public Client MyClient;
    public controller Mycontroller;
    public Viewer(controller Mycontroller, Client MyClient){
        this.Mycontroller = Mycontroller;
        this.MyClient = MyClient;
    }
}
