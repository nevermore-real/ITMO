package Server.ServerCommand;


import MajorClasses.CollectionManager;
import MajorClasses.Printer;
import MajorClasses.Product;

public class ServerRemoveLower extends ServerCommand {
    
    public void execute(){
        collectionManager.removeLower(Float.parseFloat(arrayArgs.get(0)));
        Printer.printRemoveLower(true);
    }
}
