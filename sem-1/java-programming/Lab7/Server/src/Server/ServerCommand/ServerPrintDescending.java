package Server.ServerCommand;


import MajorClasses.Printer;
import db.DatabaseHandler;

public class ServerPrintDescending extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        String info = collectionManager.getSortedCollectionInfo();
        Printer.print(info);
    }
}
