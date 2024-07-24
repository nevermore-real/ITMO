package MajorClasses;

import MajorClasses.Loader;
import MajorClasses.Printer;
import MajorClasses.Product;
import MyExceptions.InputFileException;
import MyExceptions.WrongInputException;

import java.util.ArrayDeque;

public class CollectionLoader {

    public static void load(ArrayDeque<Product> collection, String[] args) {
        try {
            Loader loader = new Loader();
            loader.loadCollection(collection, args);
        } catch (WrongInputException e) {
            Printer.printWrongInput();

        } catch (InputFileException e) {
            Printer.printColEmpty();
            collection.clear();
        }
    }
}