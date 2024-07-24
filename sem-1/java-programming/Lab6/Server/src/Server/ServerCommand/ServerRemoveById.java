package Server.ServerCommand;

public class ServerRemoveById extends ServerCommand {
    
    public void execute(){

        collectionManager.remove(product); //removed product in the box
    }
}
