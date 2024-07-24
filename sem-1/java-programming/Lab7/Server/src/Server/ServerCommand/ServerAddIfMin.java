package Server.ServerCommand;

import db.DatabaseHandler;

public class ServerAddIfMin extends ServerCommand {

    public void execute(DatabaseHandler databaseHandler){
        collectionManager.add(product);
    }
}