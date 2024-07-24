package Server.ServerCommand;

import MajorClasses.Printer;

public class ServerShow extends ServerCommand {

    public void execute() {

        String show = collectionManager.show();
        Printer.print(show);
    }
}
