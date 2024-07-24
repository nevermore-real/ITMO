package Server.ServerCommand;


import MajorClasses.Printer;

public class ServerHistory extends ServerCommand {
    
    public void execute(){
        Printer.printHistory(true, arrayArgs);
    }
}
