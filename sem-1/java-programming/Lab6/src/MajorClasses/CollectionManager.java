package MajorClasses;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CollectionManager {
    private static final boolean success = true;
    private static final boolean fail = false;

    ArrayDeque<Product> collection;

    public CollectionManager(ArrayDeque<Product> collection){
        this.collection = collection;
    }

    public boolean add(Product element){
        return collection.add(element);
    }
    public void remove(Product element){

        if (collection.remove(element)) {

            Printer.printRemove(success);

        }else{

            Printer.printRemove(fail);
        }
    }

    public void showInfo(){
        Printer.printInfo(success, collection); // да можно и сразу принтером но мы правильно делегируем задачи
    }
    public void clear(){

        collection.clear(); // гарантия есть по контракту ArrayDeque

        Printer.printClear(success);
    }
    public void save(){

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
    public void removeByOwner(String name){
        collection.removeIf(x -> x.getPerson().getName().equals(name));
    }
    public void removeLower(float price){

        for (Product elem : collection) {
            float elemPrice = elem.getPrice().floatValue();
            if (Float.compare(price, elemPrice) > 0) {
                remove(elem);
            }
        }
    }
    public String show(){
        return collection.stream().map(element -> element.toString() + "\n").collect(Collectors.joining());
    }
    public String getSortedCollectionInfo(){
        return collection.stream()
                .sorted((o1, o2) -> Float.compare(o2.getPrice().floatValue(), o1.getPrice().floatValue()))
                .map(Product::toString)
                .collect(Collectors.joining());
    }

}
