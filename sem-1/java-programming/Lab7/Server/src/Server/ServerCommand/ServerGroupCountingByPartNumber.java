package Server.ServerCommand;


import MajorClasses.Printer;
import db.DatabaseHandler;

public class ServerGroupCountingByPartNumber extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler) {
        Printer.printGroup(true, arrayArgs, intArgs);
    }
}
