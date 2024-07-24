package Server.ServerCommand;


import Commands.Command;
import MajorClasses.CommandMap;

public class ServerHelp extends ServerCommand {
    
    public void execute(){
        CommandMap<Command> mapForCycle = new CommandMap<>();
        mapForCycle.commandMap.values().forEach(Command::getHelp);
//        for(Command value : mapForCycle.commandMap.values()){
//            if (!(value instanceof Help)) value.getHelp();
//        }
    }
}
