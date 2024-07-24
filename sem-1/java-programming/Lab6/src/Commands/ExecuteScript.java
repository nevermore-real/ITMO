package Commands;


import MyExceptions.MyException;

public class ExecuteScript extends Command {
    @Override
    public CommandBox execute() throws MyException {
       return reciever.executeExecuteScript();

    }
    public void getHelp(){
        System.out.println("execute_script {file name} --- считать и исполнить скрипт из указанного файла");
    }
}
