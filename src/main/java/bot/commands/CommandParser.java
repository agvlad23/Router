package bot.commands;

public class CommandParser {
   /* String chatId;



    Map<String,String> aliases;

    public CommandParser() {

    }

    public CommandParser(String chatId) {
        this.chatId = chatId;

    }

    public void loadAlias(String chatId){

    }

    public void saveAlias(String chatId){

    }

    public void addAlias(String command,String alias){
        aliases.put(alias,command);
    }

    public Command getCommandClassAliases(String alias){
        if (aliases==null)
            loadAlias(chatId);
        String commandName=aliases.getOrDefault(alias,alias);
        return getCommandClass(commandName);
    }*/


    public static Command getCommandClass(String commandName){
        var k= StartTaskCommand.class;

        switch (commandName.split("\\s")[0].substring(1).toLowerCase()) {
            case "task":
                return new StartTaskCommand();
            case "start":
                return new StartCommand();
            case "stop":
                return new StopCommand();
            case "help":
                return new HelpCommand();
            case "list":
                return new UserListCommand();
            case "updu":
                return new InterfaceCommand();
            case "updg":
                return new GroupEditCommand();
            case "addg":
                return new AddGroupCommand();
            default: return new HelpCommand();
        }
    }
}
