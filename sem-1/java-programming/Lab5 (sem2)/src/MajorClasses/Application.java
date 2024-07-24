package MajorClasses;

import Commands.Command;
import MyExceptions.InputFileException;
import MyExceptions.MyException;
import MyExceptions.WrongInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayDeque;

public class Application {

    String[] args;
    ArrayDeque<Product> collection;
    CommandMap map;
    Loader loader;
    public static LocalDate CREATION_DATE;

    public Application(String[] args){

        this.args = args;
        this.collection = new ArrayDeque<>();
        this.loader = new Loader();
        this.map = new CommandMap();
        Application.CREATION_DATE = LocalDate.now();


    }
    public void start() {

        System.out.println("Доброго времени суток!");

        try{
            loader.loadCollection(collection, args);
        }catch (WrongInputException e){
            System.out.println("Ошибка входных данных:");

        }catch (InputFileException e){
            System.out.println(e.getMessage());
            System.out.println("Ошибка во входном файле -- коллекция пуста");
            collection.clear();
            //Loader.FILE_IS_OK = false;
        }
        String input = "";

        while (true) {

            System.out.println("Введите команду:  //  Введите help для просмотра доступных команд");

            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                if ((input = reader.readLine()) != null);
                else System.exit(0);
                Command command = map.getCommand(input);
                command.setFields(input, collection, reader);

                System.out.println("---");

                command.execute();

                System.out.println("---");

                // if command is not executed, catch exception and don't write in history.
                loader.makeHistory(input);

            } catch (NullPointerException e) {
                if(e.getMessage().contains("\"java.io.BufferedReader.readLine()\" is null")){
                    System.out.println("Был брошен Ctrl D, закрываемся :)");
                    System.exit(0);
                } else System.out.println("Неправильная команда, попробуйте еще раз.");

            } catch (RuntimeException e) {
                System.out.println("Настоящая ошибка при выполнении");
                e.printStackTrace();
            }catch (MyException e){
                System.out.println(e.getMessage());
            } catch (IOException e){
                System.out.println("Ошибка при чтении данных");
            }
        }
    }
}
