package Commands;

import MyExceptions.MyException;

public class Show extends Command {

    @Override
    public void execute() throws MyException {

        reciever.executeShow();
    }
    @Override
    public void getHelp() {
        System.out.println("show  --- вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }
}
