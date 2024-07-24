package Server.ServerCommand;


import MajorClasses.Printer;

public class ServerRemoveAllByOwner extends ServerCommand {
    
    public void execute(){
        collectionManager.removeByOwner(arrayArgs.get(0));
        Printer.printRemoveAll(true);
    }
}
