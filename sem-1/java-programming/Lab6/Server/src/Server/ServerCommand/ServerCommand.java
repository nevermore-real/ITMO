package Server.ServerCommand;


import Commands.CommandBox;
import MajorClasses.CollectionManager;
import MajorClasses.Product;

import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class ServerCommand {
    public String command;
    public CommandBox box;
    public String someField;
    public String[] massiveArgs;
    public ArrayList<String> arrayArgs;
    public ArrayList<Integer> intArgs;
    public Product product;
    protected CollectionManager collectionManager;

    public abstract void execute();

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
