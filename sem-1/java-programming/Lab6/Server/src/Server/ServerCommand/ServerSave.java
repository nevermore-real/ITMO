package Server.ServerCommand;

import MajorClasses.Loader;
import MajorClasses.Printer;

public class ServerSave extends ServerCommand {
    
    public void execute(){
        if (!Loader.FILE_IS_OK) Printer.printSave(false);
        else {
            collectionManager.save();
        }
    }
}