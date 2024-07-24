package MajorClasses;

import Commands.Command;
import Commands.CommandBox;
import MyExceptions.ExecuteException;
import MyExceptions.MyException;
import MyExceptions.WrongInputException;

import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class ScriptExecuter {

    public ArrayList<String> args;
    public String input;
    public ArrayDeque<Product> collection;
    public BufferedReader reader;
    public BufferedReader scriptReader;
    public static ArrayList<String> scriptCommands = new ArrayList<>();

    public ScriptExecuter(ArrayList<String> args, ArrayDeque<Product> collection, BufferedReader reader) {
        this.args = args;
        this.collection = collection;
        this.reader = reader;
    }

    public ScriptExecuter(ArrayDeque<Product> collection) {
        this.collection = collection;
    }

    public void prepare() throws WrongInputException, ExecuteException {
        try {
            args = new ArrayList<>(Arrays.asList(reader.readLine().replaceAll(",", ".").replaceAll("^\\s+", "").split(" ")));
        }catch (IOException e){ Printer.printIOException();}
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

    public void readCommands() {
        while (input != null) {
            scriptCommands.add(input);
            try {
                input = scriptReader.readLine();
            } catch (IOException e) {
                Printer.printIOException();
            }
        }
    }

    public CommandBox executeScriptCommand() throws MyException {
        Application.scriptIsExecuting = false; //вдруг прервется
        if (scriptCommands.size() > 0) {
            input = scriptCommands.get(0); //точно 1 строка будет иначе executeException
            input = input.substring(0, 1).toUpperCase() + input.substring(1);
            scriptCommands.remove(0);
        }else return null;
        Loader loader = new Loader();
        loader.makeHistory(input);

        if (input.equalsIgnoreCase("ExecuteScript")) {
            Printer.printRecursionDetected();
            return null;
        }
        Reciever delegationReceiver = new Reciever(collection, reader, null);
        if (Arrays.stream(CommandMap.availableCommands).noneMatch(input::equals)) {
            throw new WrongInputException("Неправильная команда в скрипте!");
        }
        try {
            Method method = Reciever.class.getMethod("execute" + input);
            CommandBox box =  (CommandBox) method.invoke(delegationReceiver);
            Application.scriptIsExecuting = true;
            return box;
        } catch (NoSuchMethodException e) {
            System.out.println("Метода нет - прокол");
        } catch (ReflectiveOperationException e) {
            System.out.println("С рефлексией не то что-то в скрипте");
        }
        return null;
    }
}
