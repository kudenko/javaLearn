package app.library.command;

import app.library.console.View;
import app.library.exceptions.JournalRepositoryException;
import app.library.model.Journal;
import app.library.storage.Repository;


public class AddJournal implements Command {
    Repository<Journal> repository;
    private final View view;

    public AddJournal(Repository<Journal> repository, View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.ADD_JOURNAL.getCommandName()));
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
        try {
            repository.save(new Journal(journalName, countOfPages, jNumber, pubYear));
        } catch (JournalRepositoryException e) {
            view.write("Error in journal save. Please try again");
        }
        view.write(String.format("Journal with name '%s' pages count '%d', " +
                "number '%d'; and year '%d' was successfully added%n", journalName, countOfPages, jNumber, pubYear));
        view.write("You can enter new command.");
    }
}
