package app.library.command;

import app.library.author.Author;
import app.library.author.AuthorService;
import app.library.console.View;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.Repository;

public class AddBook implements Command {
    Repository<Book> storage;
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public AddBook(Repository<Book> storage, AuthorRepositoryCustom<Author> authors, View view) {
        this.storage = storage;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.ADD_BOOK.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter Book name.");
        String bookName = view.read();

        view.write("Enter count of pages.");
        int countOfPages = view.readInt();

        Author author = AuthorService.authorSelection(authors, view);
        try {
            storage.save(new Book(bookName, countOfPages, author));
            view.write(String.format("Book with name '%s' pages count '%d', " +
                    "author ID '%d', was successfully added%n", bookName, countOfPages, author.getId()));
            view.write("You can enter new command.");
        } catch (BookRepositoryException e) {
            view.write("Error in book save. Please try again.");
        }
    }
}
