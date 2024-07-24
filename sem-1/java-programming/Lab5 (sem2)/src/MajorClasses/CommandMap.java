package MajorClasses;

import Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {


    public Map<String, Command> commandMap = new HashMap<>();
    public CommandMap() {
        init();
    }

    public void init(){
        String[] availableCommands = {"help", "info", "show", "clear", "exit", "save",
                "execute_script", "add", "update_id","remove_by_id", "add_if_min",
                "remove_lower", "remove_all_by_owner", "print_descending", "history", "group_counting_by_part_number"};
        for (String inputCommand : availableCommands){
            try{

                String commandName = parseName(inputCommand);
                Class<?> commandClass = Class.forName(commandName);
                Command commandInstance= (Command) commandClass.getConstructor().newInstance();

                this.commandMap.put(inputCommand,commandInstance);

            }catch (ClassNotFoundException e){
                System.out.println("Класс не найден -- в словаре команд");
            }catch (ReflectiveOperationException e){
                System.out.println("Ошибка при рефлексии");
            }
        }
    }
    public String parseName(String name){
        String[] partsOfName = name.split("_");
        StringBuilder parsedName = new StringBuilder("Commands."); // need to use full name with package
        for (String partOfName : partsOfName){
            parsedName.append(partOfName.substring(0, 1).toUpperCase());
            parsedName.append(partOfName.substring(1));
        }
        return parsedName.toString();
    }
    public Command getCommand(String input){

        String[] args = input.split(" ");

        return this.commandMap.get(args[0]);
    }
}
