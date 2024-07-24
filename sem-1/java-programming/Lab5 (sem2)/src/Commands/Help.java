package Commands;

import MyExceptions.MyException;

public class Help extends Command {

    @Override
    public void execute() throws MyException {

        reciever.executeHelp();
    }
    public void getHelp(){
        System.out.println("Команда выведет информацию по всем доступным командам");
    }
}
