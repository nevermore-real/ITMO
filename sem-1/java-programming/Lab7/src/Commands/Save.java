package Commands;


import MyExceptions.MyException;

public class Save extends Command {

    @Override
    public CommandBox execute() throws MyException {

       return reciever.executeSave();
    }

    @Override
    public void getHelp() {
        System.out.println("save --- сохранить коллекцию в файл");
    }
}
