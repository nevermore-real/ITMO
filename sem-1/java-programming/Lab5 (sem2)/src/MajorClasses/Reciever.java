package MajorClasses;

import Commands.Command;
import Commands.Help;
import MyExceptions.ExecuteException;
import MyExceptions.MyException;
import MyExceptions.WrongArgumentException;
import MyExceptions.WrongInputException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Reciever {

    protected ArrayList<String> args;
    protected ArrayDeque<Product> collection;
    protected BufferedReader reader;
    private final int ID_LOWER_BOUND = 100000;
    private final int ID_UPPER_BOUND = 900000;

    private final boolean success = true;
    private final boolean fail = false;
    public static int recurcyFlag = 0;

    public Reciever(ArrayDeque<Product> collection, BufferedReader reader, ArrayList<String> args) {
        this.collection = collection;
        this.reader = reader;
        this.args = args;
    }

    public void executeAdd(BigDecimal price) throws WrongArgumentException, ExecuteException, WrongInputException {

        Validator.throwIfManyArgs(args);
        Product newProduct = new Product();
        Random random = new Random();
        int id = ID_LOWER_BOUND + random.nextInt(ID_UPPER_BOUND); // random 6-digit id
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (Product product : collection) {
                if (product.getId() == id) {
                    id += 10;
                    flag = false;
                }
            }
        }
        newProduct.setId(id);
        newProduct.setCreationDate(java.time.LocalDate.now());

        Manager manager = new Manager(newProduct, args, reader);
        if (price != null) manager.price = price;
        manager.setAll();

        CollectionManager.add(collection,newProduct);
    }

    public void executeAddIfMin() throws WrongArgumentException, ExecuteException, WrongInputException {
        if (args == null || args.size() == 0) throw new WrongInputException("Нужно ввести цену");
        BigDecimal inputPrice = new BigDecimal(args.get(0));

        if (Validator.checkMin(inputPrice, args, collection)) {
            executeAdd(inputPrice);

        } else Printer.printAddMin(fail);
    }

    public void executeClear() throws ExecuteException, WrongInputException{

        Validator.throwIfManyArgs(args);

        Validator.throwIfCollectionIsEmpty(collection);

        CollectionManager.clear(collection);
    }
    public void executeExecuteScript() throws MyException {

        ScriptExecuter scriptExecuter = new ScriptExecuter(args,collection);
        scriptExecuter.prepare();
        scriptExecuter.executeScriptCommands();

    }

    public void executeExit() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        Loader loader = new Loader();
        loader.makeHistory("exit");

        Printer.printExit(success);

        System.exit(0);
    }

    public void executeGroupCountingByPartNumber() throws WrongInputException, ExecuteException{

        Validator.throwIfManyArgs(args);
        Validator.throwIfCollectionIsEmpty(collection);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> countList = new ArrayList<>();
        String partNumber;

        for (Product element : collection){
            partNumber = element.getPartNumber();
            if (!(list.contains(partNumber))){
                list.add(partNumber);
                countList.add(1);
            }else{
                countList.set(list.indexOf(partNumber), countList.get(list.indexOf(partNumber)) + 1); // увеличили счетчик на + 1
            }
        }
        Printer.printGroup(success,list,countList);
    }

    public void executeShow() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        Printer.printShow(success,collection);
    }
    public void executePrintDescending() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        ArrayList<Product> arr = new ArrayList<>(collection);

        arr.sort((o1, o2) -> Float.compare(o2.getPrice().floatValue(), o1.getPrice().floatValue())); // по убыванию

        ArrayDeque<Product> sortedCollection = new ArrayDeque<>(arr);

        Reciever showReciever = new Reciever(sortedCollection,reader,args);

        showReciever.executeShow(); //делегирование
    }

    public void executeRemoveAllByOwner() throws ExecuteException, WrongInputException {

        if (Validator.checkOwnerProducts(args, collection)){

            collection.removeIf(x -> x.getPerson().getName().equals(args.get(0)));
            Printer.printRemoveAll(success);

        }else Printer.printRemoveAll(fail);
    }

    public void executeRemoveById() throws ExecuteException {

        Product removedProduct = Validator.getValidProductById(args, reader, collection);

        CollectionManager.remove(collection, removedProduct);
    }

    public void executeRemoveLower() throws ExecuteException, WrongInputException {

       if (Validator.checkLowerPrice(args, collection)){
           float inputPrice = Float.parseFloat(args.get(0));

           for (Product elem : collection) {
               float price = elem.getPrice().floatValue();
               if (Float.compare(inputPrice, price) > 0) {
                   CollectionManager.remove(collection,elem);
               }
           }Printer.printRemoveLower(success);
       }else Printer.printRemoveLower(fail);


    }

    public void executeSave() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        if (!Loader.FILE_IS_OK) Printer.printSave(fail);
        else CollectionManager.save(collection);
    }

    public void executeUpdateId() throws ExecuteException{

        Updater updater = new Updater();
        Product updProduct = Validator.getValidProductById(args, reader, collection);
        updater.update(updProduct, reader);

    }

    public void executeHelp() throws WrongInputException {

        Validator.throwIfManyArgs(args);

        CommandMap mapForCycle = new CommandMap();

        for( Command value : mapForCycle.commandMap.values()){
            if (!(value instanceof Help)) value.getHelp();
        }

    }

    public void executeHistory() throws WrongInputException {

        Validator.throwIfManyArgs(args);

        Printer.printHistory(success);

    }
    public void executeInfo() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        CollectionManager.showInfo(collection);
    }

}
