package Commands;

import MyExceptions.MyException;

public class RemoveById extends Command {

    @Override
    public void execute() throws MyException {

        reciever.executeRemoveById();
    }

    @Override
    public void getHelp() {
        System.out.println("remove_by_id  {int id} --- удалить элемент из коллекции по его id");
    }
}