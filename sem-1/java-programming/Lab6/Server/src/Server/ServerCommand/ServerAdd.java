package Server.ServerCommand;

import MajorClasses.Printer;

public class ServerAdd extends ServerCommand {
    public void execute(){
        Printer.printAdd(collectionManager.add(product));
    }
}
