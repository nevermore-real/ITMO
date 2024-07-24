package Server.ServerCommand;


import Commands.Command;
import MajorClasses.CommandMap;
import db.DatabaseHandler;

public class ServerHelp extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        CommandMap<Command> mapForCycle = new CommandMap<>();
        mapForCycle.commandMap.values().forEach(Command::getHelp);
//        for(Command value : mapForCycle.commandMap.values()){
//            if (!(value instanceof Help)) value.getHelp();
//        }
    }
}
