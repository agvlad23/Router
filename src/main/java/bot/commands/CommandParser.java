package bot.commands;
import bot.bot.TrackerBot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        var k=StartCommand.class;

        switch (commandName.split("\\s")[0].substring(1).toLowerCase()) {
            case "start":
                return new StartCommand();
            case "stop":
                return new StopCommand();
            case "help":
                return new HelpCommand();
            case "list":
                return new UserListCommand();
            case "in":
                return new InterfaceCommand();
            default: return new HelpCommand();
        }
    }
}
