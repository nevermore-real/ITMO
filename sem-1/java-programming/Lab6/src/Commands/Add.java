package Commands;

import MyExceptions.MyException;

import java.math.BigDecimal;

public class Add extends Command {
    BigDecimal price;
    public CommandBox execute() throws MyException {
        return reciever.executeAdd(price);
    }
    public void getHelp(){
        System.out.println("add  {String name, Float price, String partNumber} -- и дальше читать подсказки программы  --- добавить новый элемент в коллекцию");
    }
}
