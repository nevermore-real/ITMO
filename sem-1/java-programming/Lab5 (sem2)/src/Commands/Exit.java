package Commands;

import MyExceptions.MyException;

public class Exit extends Command {

    @Override
    public void execute() throws MyException {
        reciever.executeExit();
    }
    public void getHelp(){
        System.out.println("exit --- завершить программу (без сохранения в файл)");
    }
}
