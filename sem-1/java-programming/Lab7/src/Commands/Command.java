package Commands;
import MajorClasses.Product;
import MajorClasses.Reciever;
import MyExceptions.MyException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {

    public ArrayList<String> args;
    public ArrayDeque<Product> collection;
    public BufferedReader reader;
    protected Reciever reciever;

    public void setArgs(ArrayList<String> args) {

        args.remove(0);
        this.args = args;
    }

    public void setCollection(ArrayDeque<Product> collection) {
        this.collection = collection;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setFields(String args, ArrayDeque<Product> collection, BufferedReader reader) {

        ArrayList<String> arrayArgs = new ArrayList<>(Arrays.asList(args.split(" ")));
        setArgs(arrayArgs);
        setCollection(collection);
        setReader(reader);
        this.reciever = new Reciever(collection, reader, arrayArgs);
    }

    public abstract void getHelp();

    public abstract CommandBox execute() throws MyException;
}
