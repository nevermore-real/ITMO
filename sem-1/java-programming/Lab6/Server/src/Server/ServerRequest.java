package Server;

import Commands.CommandBox;
import MajorClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;


public class ServerRequest {
    ServerHandler handler;
    ArrayDeque<Product> collection = new ArrayDeque<>();

    //private static final Logger log = LogManager.getLogger();

    public ServerRequest(){
        this.handler = new ServerHandler();
        CollectionLoader.load(collection, null);
        CollectionManager collectionManager = new CollectionManager(collection);
        handler.setCollectionManager(collectionManager);
    }
    public Response doRequest(CommandBox box) {
        if (box != null) {

            handler.extractBox(box);
            Printer.printCommandExecuted(handler.commandName);
            return handler.executeCommand();
        }
        return null;
    }
}
