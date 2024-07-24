package MajorClasses;

import Commands.Command;
import Commands.CommandBox;
import MyExceptions.MyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayDeque;

public class Application {

    ArrayDeque<Product> collection;
    CommandMap<Command> map;
    Loader loader;
    public static LocalDate CREATION_DATE;
    public static boolean scriptIsExecuting = false;
    public Application(ArrayDeque<Product> collection){

        this.collection = collection;
        this.loader = new Loader();
        this.map = new CommandMap<>();
        Application.CREATION_DATE = LocalDate.now();


    }
    public CommandBox executeOneCommand() {
        String input;

        try {
            if (!scriptIsExecuting) {
                Printer.printEnterCommand();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                if ((input = reader.readLine()) == null) System.exit(0);

                Command command = map.getCommand(input);

                command.setFields(input, collection, reader);

                Printer.printSeparator();
                CommandBox box = command.execute();
                // if command is not executed, catch exception and don't write in history.
                loader.makeHistory(input);
                return box;
            }else{
                ScriptExecuter executor = new ScriptExecuter(collection);
                CommandBox box = executor.executeScriptCommand();
                return box;
            }
        } catch (NullPointerException e) {
            if (e.getMessage().contains(Printer.getErrorMessage())) {
                Printer.printCtrlD();
                System.exit(0);
            } else Printer.printWrongCommand();
        } catch (RuntimeException e) {

        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            Printer.printIOException();
        }
        return null;
    }
    public void updateCol(ArrayDeque<Product> collection){
        this.collection = collection;
    }
}
