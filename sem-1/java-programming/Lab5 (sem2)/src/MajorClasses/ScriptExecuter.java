package MajorClasses;

import Commands.Command;
import MyExceptions.ExecuteException;
import MyExceptions.MyException;
import MyExceptions.WrongInputException;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ScriptExecuter {

    public ArrayList<String> args;
    public String input;
    public ArrayDeque<Product> collection;
    public BufferedReader scriptReader;

    public ScriptExecuter(ArrayList<String> args, ArrayDeque<Product> collection){
        this.args = args;
        this.collection = collection;
    }
    public void prepare () throws WrongInputException, ExecuteException{

        if (args.size() != 1) throw new WrongInputException("Один аргумент на вход --- имя файла");

        String filename = args.get(0);
        File file = new File(filename);

        try {
            scriptReader = new BufferedReader(new FileReader(file));
            if (!(file.canRead())) throw new ExecuteException("Нет прав на чтение файла!");
            if ((input = scriptReader.readLine()) == null) throw new ExecuteException("А файлик-то пустышка!");

        } catch (IOException e) {
            throw new ExecuteException("Вот это да! Вронг фаел!"); // проверили на доступ canRead()
        }
    }

    public void executeScriptCommands() throws MyException {

        CommandMap map = new CommandMap();
        Loader loader = new Loader();

        while (input != null) {

            Command command = map.getCommand(input);
            command.setFields(input, collection, scriptReader);
            if (command.getClass().getName().equalsIgnoreCase("Commands.executeScript")){
                Reciever.recurcyFlag +=1;
            }
            if (Reciever.recurcyFlag == 1) throw  new MyException("Прекращение рекурсии!");
            command.execute();
            // if command is not executed, catch exception and don't write in history.
            loader.makeHistory(input);
            try{
                input = scriptReader.readLine();
            }catch (IOException e){
                System.out.println("Проблемы при работе с файлом");
            }
        }
    }
}
