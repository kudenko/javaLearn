package library.command;

import library.author.Author;
import library.author.AuthorService;
import library.console.View;
import library.model.Book;
import library.model.Publication;
import library.storage.Repository;


public class RemoveBook implements Command {
    Repository<Publication> storage;
    Repository<Author> authors;
    private final View view;

    public RemoveBook(Repository<Publication> storage, Repository<Author> authors, View view) {
        this.storage = storage;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.REMOVE_BOOK.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter publication name for deletion.");
        String deleteName = view.read();

        view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");

        long authorId = AuthorService.authorSelection(authors, view);

        storage.removeEntity(new Book(deleteName, 0, authorId));

        view.write(String.format("Book with name %s and author count %s was successfully deleted", deleteName, authorId));
        view.write("You can enter new command.");
    }
}