package Server.ServerCommand;

import MajorClasses.Printer;
import db.DatabaseHandler;

public class ServerShow extends ServerCommand {

    public void execute(DatabaseHandler databaseHandler) {

        String show = collectionManager.show();
        Printer.print(show);
    }
}
