package library.command;

import library.console.View;
import library.storage.Repository;

import static library.command.ConsoleCommand.PRINT;

public class Print implements Command {
    private static final ConsoleCommand commandName = PRINT;
    private final View view;
    private final Repository repository;

    public Print(View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(commandName.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Available publications: ");
        repository.print();
    }
}
