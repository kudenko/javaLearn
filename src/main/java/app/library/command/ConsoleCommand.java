package app.library.command;

public enum ConsoleCommand {
    HELP("help", "prints list of the commands"),
    EXIT("exit", "exit the program"),
    ADD_JOURNAL("addJournal","adds journal to the app.library"),
    ADD_BOOK("addBook", "adds book to the app.library"),
    ADD_AUTHOR("addAuthor", "adds author to the app.library"),
    EDIT_JOURNAL("editJournal","edits journal in the app.library"),
    EDIT_BOOK("editBook", "edits book in the app.library"),
    EDIT_AUTHOR("editAuthor", "edits author in the app.library"),
    REMOVE_BOOK("removeBook", "removes book from the app.library"),
    REMOVE_AUTHOR("removeAuthor", "removes author from the app.library"),
    REMOVE_JOURNAL("removeJournal", "removes journal from the app.library"),
    PRINT("print", "prints all publications in the app.library");

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
