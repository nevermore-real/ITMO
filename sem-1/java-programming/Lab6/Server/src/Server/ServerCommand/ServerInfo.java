package Server.ServerCommand;


public class ServerInfo extends ServerCommand {
    
    public void execute(){
        collectionManager.showInfo();
    }
}
