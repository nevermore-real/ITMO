package Commands;

import MyExceptions.MyException;

public class Save extends Command {

    @Override
    public void execute() throws MyException {

       reciever.executeSave();
    }

    @Override
    public void getHelp() {
        System.out.println("save --- сохранить коллекцию в файл");
    }
}
