package library.command;

public enum ConsoleCommand {
    HELP("help", "prints list of the commands"),
    EXIT("exit", "exit the program"),
    ADD_JOURNAL("addJournal","adds journal to the library"),
    ADD_BOOK("addBook", "adds book to the library"),
    ADD_AUTHOR("addAuthor", "adds author to the library"),
    REMOVE_BOOK("removeBook", "removes book from the library"),
    REMOVE_JOURNAL("removeJournal", "removes journal from the library"),
    PRINT("print", "prints all publications in the library");

    private final String command;

    private final String commandAction;

    ConsoleCommand(String command, String commandAction) {
        this.command = command;
        this.commandAction = commandAction;
    }

    public String getCommandName() {
        return command;
    }

    public String getCommandAction() {
        return commandAction;
    }
}
