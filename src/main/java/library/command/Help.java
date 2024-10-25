package library.command;

import library.console.View;

public class Help implements Command {

    private static final String commandName = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equals(commandName);
    }

    @Override
    public void handle() {
        view.write("List of the commands: ");
        view.write("'help' - list of the commands");
        view.write("'exit' - exit the program");
        view.write("'addJournal' - adds journal to the library");
        view.write("'addBook' - adds book to the library");
        view.write("'removeBook' - removes book from the library");
        view.write("'print' - prints all books in the library");
    }
}
