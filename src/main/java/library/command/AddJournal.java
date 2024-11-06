package library.command;

import library.console.View;
import library.model.Journal;
import library.model.Publication;
import library.storage.Repository;

import static library.command.ConsoleCommand.ADD_JOURNAL;

public class AddJournal implements Command {
    private static final ConsoleCommand commandName = ADD_JOURNAL;
    Repository<Publication> storage;
    private final View view;

    public AddJournal(Repository<Publication> storage, View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(commandName.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter Journal name.");
        String journalName = view.read();
        view.write("Enter count of pages.");
        int countOfPages = view.readInt();
        view.write("Enter Journal number.");
        int jNumber = view.readInt();
        view.write("Enter publication year.");
        int pubYear = view.readInt();

        storage.addEntity(new Journal(journalName, countOfPages, jNumber, pubYear));
        view.write(String.format("Journal with name '%s' pages count '%d', " +
                "number '%d'; and year '%d' was successfully added%n", journalName, countOfPages, jNumber, pubYear));
        view.write("You can enter new command.");
    }
}
