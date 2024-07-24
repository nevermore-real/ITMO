package Commands;

import MyExceptions.MyException;

public class Info extends Command {

    @Override
    public void execute() throws MyException {

        reciever.executeInfo();
    }
    public void getHelp(){
        System.out.println("info --- вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)");
    }
}
