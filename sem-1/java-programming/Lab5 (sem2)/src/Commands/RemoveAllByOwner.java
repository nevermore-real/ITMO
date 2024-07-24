package Commands;

import MyExceptions.MyException;

public class RemoveAllByOwner extends Command {
    public void execute() throws MyException {

        reciever.executeRemoveAllByOwner();
    }
    @Override
    public void getHelp() {
            System.out.println("remove_all_by_owner {String name} --- удалить из коллекции все элементы, значение поля Person.name которого эквивалентно заданному");
    }
}