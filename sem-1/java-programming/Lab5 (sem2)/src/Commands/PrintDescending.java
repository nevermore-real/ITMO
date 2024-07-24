package Commands;

import MyExceptions.MyException;

public class PrintDescending extends Command {

    @Override
    public void execute() throws MyException {

        reciever.executePrintDescending();

    }
    @Override
    public void getHelp() {
        System.out.println("print_descending --- вывести элементы коллекции в порядке убывания");
    }
}
