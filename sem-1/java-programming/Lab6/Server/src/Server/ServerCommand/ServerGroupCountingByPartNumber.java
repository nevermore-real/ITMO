package Server.ServerCommand;


import MajorClasses.Printer;

public class ServerGroupCountingByPartNumber extends ServerCommand {
    
    public void execute() {
        Printer.printGroup(true, arrayArgs, intArgs);
    }
}
