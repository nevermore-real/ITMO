package Server.ServerCommand;


import MajorClasses.Printer;
import db.DatabaseHandler;

public class ServerHistory extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        Printer.printHistory(true, arrayArgs);
    }
}
