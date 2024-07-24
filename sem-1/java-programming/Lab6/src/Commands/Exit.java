package Commands;


import MyExceptions.MyException;

public class Exit extends Command {

    @Override
    public CommandBox execute() throws MyException {
        reciever.executeExit();
        return null;
    }
    public void getHelp(){
        System.out.println("exit --- завершить программу (без сохранения в файл)");
    }
}
