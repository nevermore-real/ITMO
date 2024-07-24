package MajorClasses;


import Commands.CommandBox;
import MyExceptions.ExecuteException;
import MyExceptions.MyException;
import MyExceptions.WrongArgumentException;
import MyExceptions.WrongInputException;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Reciever {

    protected ArrayList<String> args;
    protected ArrayDeque<Product> collection;
    protected BufferedReader reader;
    private final int ID_LOWER_BOUND = 100000;
    private final int ID_UPPER_BOUND = 900000;
    CommandBox box;

    private final boolean success = true;
    private final boolean fail = false;

    public Reciever(ArrayDeque<Product> collection, BufferedReader reader, ArrayList<String> args) {
        this.collection = collection;
        this.reader = reader;
        this.args = args;
    }

    public CommandBox executeAdd(BigDecimal price) throws WrongArgumentException, ExecuteException, WrongInputException {

        Validator.throwIfManyArgs(args);
        Product newProduct = new Product();
        Random random = new Random();
        int id = ID_LOWER_BOUND + random.nextInt(ID_UPPER_BOUND); // random 6-digit id
        boolean flag = false;
        while (!flag && collection!=null) {
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
        if (price != null){
            manager.price = price;
            box = new CommandBox("AddIfMin");
        }else {
            box = new CommandBox("Add");
        }
        manager.setAll();


        box.setProduct(newProduct);
        return box;

    }

    public CommandBox executeAddIfMin() throws WrongArgumentException, ExecuteException, WrongInputException {
        if (args == null || args.size() == 0) throw new WrongInputException("Нужно ввести цену");
        BigDecimal inputPrice = new BigDecimal(args.get(0));

        if (Validator.checkMin(inputPrice, args, collection)) {
            return executeAdd(inputPrice);

        } else Printer.printAddMin(fail);
        return null;
    }

    public CommandBox executeClear() throws ExecuteException, WrongInputException{

        Validator.throwIfManyArgs(args);

        Validator.throwIfCollectionIsEmpty(collection);
        box = new CommandBox("Clear");
        return box;

    }
    public CommandBox executeExecuteScript() throws MyException {

        ScriptExecuter scriptExecuter = new ScriptExecuter(args,collection,reader);
        scriptExecuter.prepare();
        scriptExecuter.readCommands();
        return scriptExecuter.executeScriptCommand();
    }

    public void executeExit() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        Loader loader = new Loader();
        loader.makeHistory("exit");

        Printer.printExit(success);

        System.exit(0);
    }

    public CommandBox executeGroupCountingByPartNumber() throws WrongInputException, ExecuteException{

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
        box = new CommandBox("GroupCountingByPartNumber");
        box.setArrayArgs(list);
        box.setIntArgs(countList);

        return box;
    }

    public CommandBox executeShow() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        box = new CommandBox("Show");
        return box;
    }
    public CommandBox executePrintDescending() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        box = new CommandBox("PrintDescending");
        return box;
        //Reciever showReciever = new Reciever(sortedCollection,reader,args);

        //showReciever.executeShow(); //делегирование
    }

    public CommandBox executeRemoveAllByOwner() throws ExecuteException, WrongInputException {
        Validator validator = new Validator(reader);
        String name;
        name = validator.checkOwnerProducts(args, collection);
        if (name != null){
            args.add(name);
            box = new CommandBox("RemoveAllByOwner");
            box.setArrayArgs(args);
            return box;
        }else Printer.printRemoveAll(fail);
        return null;
    }

    public CommandBox executeRemoveById() throws ExecuteException {

        Product removedProduct = Validator.getValidProductById(args, reader, collection);

        box = new CommandBox("RemoveById");
        box.setProduct(removedProduct);
        return box;
    }

    public CommandBox executeRemoveLower() throws ExecuteException, WrongInputException {
        Validator validator = new Validator(reader);
       BigDecimal price = validator.checkLowerPrice(args, collection);
       if (price != null){
           args.add(String.valueOf(price));
           box = new CommandBox("RemoveLower");
           box.setArrayArgs(args);
           return box;

       }else{
           Printer.printRemoveLower(fail);
       }return null;


    }

    public CommandBox executeSave() throws WrongInputException{

        Validator.throwIfManyArgs(args);

        box = new CommandBox("Save");
        return box;
    }

    public CommandBox executeUpdateId() throws ExecuteException{

        Updater updater = new Updater();
        Product updProduct = Validator.getValidProductById(args, reader, collection);
        return updater.update(updProduct, reader);

    }

    public CommandBox executeHelp() throws WrongInputException {

        Validator.throwIfManyArgs(args);
        box = new CommandBox("Help");
        return box;
    }

    public CommandBox executeHistory() throws WrongInputException {

        Validator.throwIfManyArgs(args);
        box = new CommandBox("History");
        box.setArrayArgs(Loader.historyArray);
        return box;
    }
    public CommandBox executeInfo() throws WrongInputException{

        Validator.throwIfManyArgs(args);
        box = new CommandBox("Info");
        return box;
    }

}
