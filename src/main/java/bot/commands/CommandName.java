package bot.commands;

public enum CommandName {
    START,
    STOP,
    HELP,
    ECHO;

    public static Command getCommandClass(String s){


        switch (s.split("\\s")[0]) {
            case "/start":
                return new StartTaskCommand();
            case "/stop":
                return new StopCommand();
            default: return new StartTaskCommand();
        }
    }
}
