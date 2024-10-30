package library.command;

import library.author.AuthorService;
import library.console.View;
import library.model.Book;
import library.storage.InMemoryAuthorStorage;
import library.storage.Repository;

import static library.command.ConsoleCommand.REMOVE_BOOK;

public class RemoveBook implements Command {
    private static final ConsoleCommand commandName = REMOVE_BOOK;
    Repository storage;
    InMemoryAuthorStorage authors;
    private final View view;

    public RemoveBook(Repository storage, InMemoryAuthorStorage authors, View view) {
        this.storage = storage;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(commandName.getCommandName()));
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
