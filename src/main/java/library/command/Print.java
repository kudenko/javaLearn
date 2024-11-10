package library.command;

import library.console.View;
import library.model.Publication;
import library.storage.Repository;


public class Print implements Command {
    private final View view;
    private final Repository<Publication> repository;

    public Print(View view, Repository<Publication> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.PRINT.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Available publications: ");
        repository.print();
    }
}
