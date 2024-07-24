package Server.ServerCommand;

import MajorClasses.Updater;
import MyExceptions.MyException;

public class ServerUpdateId extends ServerCommand {
    
    public void execute(){
        try {
            if (box.getField() != null) {
                Updater.updHardField(someField, massiveArgs, product);
            } else {
                Updater.updEasyField(product, massiveArgs);

            }
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
