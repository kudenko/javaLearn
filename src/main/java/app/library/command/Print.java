package app.library.command;

import app.library.console.View;
import app.library.model.Book;
import app.library.model.Journal;
import app.library.storage.Repository;


public class Print implements Command {
    private final View view;
    private final Repository<Book> bookRepository;
    private final Repository<Journal> journalRepository;


    public Print(View view, Repository<Book> bookRepository, Repository<Journal> journalRepository) {
        this.view = view;
        this.bookRepository = bookRepository;
        this.journalRepository = journalRepository;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.PRINT.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Available publications: ");
        journalRepository.print();
        bookRepository.print();
    }
}
