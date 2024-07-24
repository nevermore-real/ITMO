package Server.ServerCommand;


import db.DatabaseHandler;

public class ServerInfo extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        collectionManager.showInfo();
    }
}
