package library.command;

import library.console.View;
import library.exceptions.JournalRepositoryException;
import library.model.Journal;
import library.storage.Repository;


public class EditJournal implements Command {
    Repository<Journal> repository;
    private final View view;

    public EditJournal(Repository<Journal> repository, View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.EDIT_JOURNAL.getCommandName()));
    }

    @Override
    public void handle() {
        Long journalId = null;
        Journal journal = null;
        while (journal == null) {
            view.write("Enter journal's id for editing from the list.");
            repository.print();
            journalId = view.readLong();
            journal = repository.findById(journalId);
        }

        view.write("Enter Journal name for editing.");
        String journalName = view.read();
        view.write("Enter count of pages for editing.");
        int countOfPages = view.readInt();
        view.write("Enter Journal number for editing.");
        int jNumber = view.readInt();
        view.write("Enter publication year for editing.");
        int pubYear = view.readInt();
        try {
            journal = new Journal(journalId, journalName, countOfPages, jNumber, pubYear);
            repository.update(journal);
        } catch (JournalRepositoryException e) {
            view.write("Error in journal update. Please try again");
        }
        view.write(String.format("Journal with name '%s' pages count '%d', " +
                "number '%d'; and year '%d' was successfully edited%n", journalName, countOfPages, jNumber, pubYear));
        view.write("You can enter new command.");
    }
}
