package library.command;

import library.console.View;
import library.model.Journal;
import library.storage.JournalRepository;
import library.storage.Repository;

import java.util.List;

public class RemoveJournal implements Command {
    JournalRepository storage;
    private final View view;

    public RemoveJournal(Repository<Journal> storage, View view) {
        if (storage instanceof JournalRepository) {
            this.storage = (JournalRepository) storage;
        } else {
            throw new IllegalArgumentException("Provided storage must be of type JournalRepository.");
        }
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.REMOVE_JOURNAL.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter publication name for deletion.");
        String delJournalName = view.read();

        view.write("Enter publication number for deletion.");
        int deleteJournalNumber = view.readInt();

        view.write("Enter publication year for deletion");
        int deleteJournalYear = view.readInt();

        List<Journal> deletionJournals = storage.findByNameYearNumber(delJournalName, deleteJournalYear, deleteJournalNumber);

        for (Journal deletionJournal : deletionJournals) {
            view.write(String.format("Journal with id = %d was successfully deleted", deletionJournal.getId()));
            storage.delete(deletionJournal.getId());
        }
        view.write("You can enter new command.");
    }
}
