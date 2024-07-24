package Commands;


import MyExceptions.MyException;

public class Clear extends Command {
    @Override
    public CommandBox execute() throws MyException {

       return reciever.executeClear();
    }
    public void getHelp(){
        System.out.println("clear --- очистить коллекцию");
    }
}
