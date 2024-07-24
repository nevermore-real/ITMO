package MajorClasses;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandMap<C> {
    private  String pattern;
    private  String patternClient = "Commands.";
    private  String patternServer = "Server.ServerCommand.Server";
    public static String[] availableCommands = {"Help", "Exit", "Info", "Show", "Clear", "Save", "Add", "UpdateId","RemoveById", "AddIfMin",
            "RemoveLower", "RemoveAllByOwner", "PrintDescending", "History", "GroupCountingByPartNumber", "ExecuteScript"};
    public Map<String, C> commandMap = new HashMap<>();
    public CommandMap() {
        pattern = patternClient;
        init();
    }
    public CommandMap(String server){
        pattern = patternServer;
        availableCommands = Arrays.stream(availableCommands).filter(x -> !(x.equals("ExecuteScript") || x.equals("Exit"))).toArray(String[]::new);
        init();
    }

    @SuppressWarnings("unchecked") // unchecked cast - works fine because there will be only command classes in map;
    public void init(){

        for (String command : availableCommands){
            try{

                String commandName = parseName(command);
                Class<?> commandClass = Class.forName(commandName);
                C commandInstance = (C) commandClass.getConstructor().newInstance();
                this.commandMap.put(command.toLowerCase(),commandInstance);

            }catch (ClassNotFoundException e){
                Printer.printMapClassNotFound();
            }catch (ReflectiveOperationException e){
                Printer.printMapReflectionError();
            }
        }
    }
    public String parseName(String name){
        String[] partsOfName = name.split("_");
        StringBuilder parsedName = new StringBuilder(pattern); // need to use full name with package
        for (String partOfName : partsOfName){
            parsedName.append(partOfName.substring(0, 1).toUpperCase());
            parsedName.append(partOfName.substring(1));
        }
        return parsedName.toString();
    }
    public C getCommand(String input){
        String[] partsOfName = input.split("_");
        StringBuilder parsedName = new StringBuilder();
        for (String partOfName : partsOfName) {
            parsedName.append(partOfName.substring(0, 1).toUpperCase());
            parsedName.append(partOfName.substring(1));
        }
        return this.commandMap.get(parsedName.toString().toLowerCase());
    }
}

