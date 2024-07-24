package Server.ServerCommand;

import MajorClasses.Loader;
import MajorClasses.Printer;
import db.DatabaseHandler;

public class ServerSave extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        if (!Loader.FILE_IS_OK) Printer.printSave(false);
        else {
            collectionManager.save();
        }
    }
}