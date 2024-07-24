package Commands;


import MyExceptions.MyException;

public class UpdateId extends Command {

    @Override
    public CommandBox execute() throws MyException {

        return reciever.executeUpdateId();
    }

    @Override
    public void getHelp() {
        System.out.println("update_id  {int id, желаемое поле} -- может повторяться -- для остановки ввести stop --- обновить поле элемента в коллекции");
    }
}

