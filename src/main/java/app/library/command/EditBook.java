package app.library.command;

import app.library.author.Author;
import app.library.author.AuthorService;
import app.library.console.View;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.Repository;

public class EditBook implements Command {
    Repository<Book> storage;
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public EditBook(Repository<Book> storage, AuthorRepositoryCustom<Author> authors, View view) {
        this.storage = storage;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.EDIT_BOOK.getCommandName()));
    }

    @Override
    public void handle() {
        Long bookId = null;
        Book book = null;
        while (book == null) {
            view.write("Enter book's id for editing from the list.");
            storage.print();
            bookId = view.readLong();
            book = storage.findById(bookId);
        }

        view.write("Enter Book name for editing.");
        String bookName = view.read();

        view.write("Enter count of pages.");
        int countOfPages = view.readInt();

        Author author = AuthorService.authorSelection(authors, view);
        try {
            book = new Book(bookId, bookName, countOfPages, author);
            storage.update(book);
            view.write(String.format("Book with name '%s' pages count '%d', " +
                    "author ID '%d', was successfully edited%n", bookName, countOfPages, author));
            view.write("You can enter new command.");
        } catch (BookRepositoryException e) {
            view.write("Error in book update. Please try again.");
        }
    }
}
