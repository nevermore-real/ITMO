package Server;

import Commands.CommandBox;
import MajorClasses.CollectionManager;
import MajorClasses.CommandMap;
import MajorClasses.Product;
import MajorClasses.Response;
import Server.ServerCommand.ServerCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;

public class ServerHandler {
    String commandName;
    ServerCommand serverCommand;
//    public ArrayDeque<Product> collection;
    CommandMap<ServerCommand> commandMap = new CommandMap<>("server");
    CollectionManager manager;

    //private static final Logger log = LogManager.getLogger();
    public void extractBox(CommandBox box){
        this. commandName = box.getCommand().toLowerCase();
        serverCommand = commandMap.getCommand(commandName);
        serverCommand.setCollectionManager(manager);
        serverCommand.box = box;
        serverCommand.someField = box.getField();
        serverCommand.massiveArgs = box.getMassiveArgs();
        serverCommand.arrayArgs = box.getArrayArgs();
        serverCommand.intArgs = box.getIntArgs();
        serverCommand.product = box.getProduct();
        //log.trace("box extracted");
    }
    public void setCollectionManager(CollectionManager manager){
        this.manager = manager;
    }
    public Response executeCommand(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        serverCommand.execute();

        Response response = new Response();
        response.setMessage(baos.toString());
        System.setOut(old);

        return response;
        //log.info("command executed");
    }
}
