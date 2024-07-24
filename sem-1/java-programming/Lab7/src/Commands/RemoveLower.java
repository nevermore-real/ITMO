package Commands;


import MyExceptions.MyException;

public class RemoveLower extends Command {

    @Override
    public CommandBox execute() throws MyException {

        return reciever.executeRemoveLower();
    }
    @Override
    public void getHelp() {
        System.out.println("remove_lower {Float price} --- удалить из коллекции все элементы, меньшие по цене, чем заданный");
    }
}
