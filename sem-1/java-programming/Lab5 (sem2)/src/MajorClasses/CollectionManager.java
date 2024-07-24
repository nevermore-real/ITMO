package MajorClasses;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayDeque;

public class CollectionManager {
    private static final boolean success = true;
    private static final boolean fail = false;

    public static void add(ArrayDeque<Product> collection, Product element){
        if (collection.add(element)) Printer.printAdd(success);
        else Printer.printAdd(fail);
    }
    public static void remove(ArrayDeque<Product> collection, Product element){

        if (collection.remove(element)) {

            Printer.printRemove(success);

        }else{

            Printer.printRemove(fail);
        }
    }

    public static void showInfo(ArrayDeque<Product> collection){
        Printer.printInfo(success, collection); // да можно и сразу принтером но мы правильно делегируем задачи
    }
    public static void clear(ArrayDeque<Product> collection){

        collection.clear(); // гарантия есть по контракту ArrayDeque

        Printer.printClear(success);
    }
    public static void save(ArrayDeque<Product> collection){

        PrintStream goBack = System.out;
        try {
            System.setOut(new PrintStream(Loader.filename));

            Printer.printShow(success,collection);

            System.setOut(goBack);

            Printer.printSave(success);

        } catch (FileNotFoundException e) {
            Printer.printSave(fail);
        }
    }

}
