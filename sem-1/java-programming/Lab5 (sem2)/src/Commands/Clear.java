package Commands;

import MyExceptions.MyException;

public class Clear extends Command {
    @Override
    public void execute() throws MyException {

        reciever.executeClear();
    }
    public void getHelp(){
        System.out.println("clear --- очистить коллекцию");
    }
}
