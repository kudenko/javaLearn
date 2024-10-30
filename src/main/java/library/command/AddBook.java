package library.command;

import library.author.AuthorService;
import library.console.View;
import library.model.Book;
import library.storage.InMemoryAuthorStorage;
import library.storage.Repository;

import static library.command.ConsoleCommand.ADD_BOOK;

public class AddBook implements Command {
    private static final ConsoleCommand commandName = ADD_BOOK;
    Repository storage;
    InMemoryAuthorStorage authors;
    private final View view;

    public AddBook(Repository storage, InMemoryAuthorStorage authors, View view) {
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
        view.write("Enter Book name.");
        String bookName = view.read();

        view.write("Enter count of pages.");
        int countOfPages = view.readInt();

        view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");
        long authorId = AuthorService.authorSelection(authors, view);
        storage.addEntity(new Book(bookName, countOfPages, authorId));

        view.write(String.format("Book with name '%s' pages count '%d', " +
                "author ID '%d', was successfully added%n", bookName, countOfPages, authorId));
        view.write("You can enter new command.");
        storage.print();
    }
}
