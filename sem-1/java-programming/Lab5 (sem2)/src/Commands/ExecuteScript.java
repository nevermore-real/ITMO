package Commands;

import MyExceptions.MyException;

public class ExecuteScript extends Command {
    @Override
    public void execute() throws MyException {
        reciever.executeExecuteScript();

    }
    public void getHelp(){
        System.out.println("execute_script {file name} --- считать и исполнить скрипт из указанного файла");
    }
}
