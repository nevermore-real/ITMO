package Commands;

import MyExceptions.MyException;

public class AddIfMin extends Command {
    public void execute() throws MyException {

        reciever.executeAddIfMin();
    }

    public void getHelp(){
        System.out.println("add_if_min {Float price} -- и затем остальные аргументы --- добавить новый элемент в коллекцию, если его значение price меньше, чем у наименьшего элемента этой коллекции");
    }
}
