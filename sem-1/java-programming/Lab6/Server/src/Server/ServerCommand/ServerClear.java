package Server.ServerCommand;

public class ServerClear extends ServerCommand {

    public void execute(){
        collectionManager.clear();
    }
}
