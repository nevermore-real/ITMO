package Server.ServerCommand;


import MajorClasses.Printer;

public class ServerPrintDescending extends ServerCommand {
    
    public void execute(){
        String info = collectionManager.getSortedCollectionInfo();
        Printer.print(info);
    }
}
