package Commands;

import MyExceptions.MyException;

public class History extends Command {

    private final int HISTORY_COMMANDS_AMOUNT = 14;
    @Override
    public void execute() throws MyException {

        reciever.executeHistory();
    }
    public void getHelp(){
        System.out.println("history --- вывести последние " + HISTORY_COMMANDS_AMOUNT + " команд (без их аргументов)");
    }
}