package app.library.command;

import app.library.author.Author;
import app.library.author.AuthorService;
import app.library.console.View;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.Repository;

import java.util.List;


public class RemoveBook implements Command {
    Repository<Book> bookRepository;
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public RemoveBook(Repository<Book> bookRepository, AuthorRepositoryCustom<Author> authors, View view) {
        this.bookRepository = bookRepository;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.REMOVE_BOOK.getCommandName()));
    }

    @Override
    public void handle() {
        Book removedBook = null;
        Long bookIdForDeletion = null;
        view.write("Enter publication name for deletion.");
        String deleteName = view.read();

        view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");

        Author author = AuthorService.authorSelection(authors, view);

        List<Book> deletionPublication = bookRepository.findAll().stream().filter(book -> book.getAuthorId() == author.getId()).filter(book -> book.getName().equals(deleteName)).toList();

        if(deletionPublication.isEmpty()) {
            view.write(String.format("There is no book with name %s and author id %d", deleteName, author.getId()));
            return;
        }

        if(deletionPublication.size() > 1) {
            view.write("Such combination has several books.");
            while (bookIdForDeletion == null) {
                view.write("Please enter publication id for deletion from the list: ");
                deletionPublication.forEach(System.out::println);
                bookIdForDeletion = view.readLong();
            }
            removedBook = bookRepository.findById(bookIdForDeletion);
        }

        if(deletionPublication.size() == 1) {
            removedBook = deletionPublication.get(0);
            bookIdForDeletion = removedBook.getPublicationId();
        }
        try {
            bookRepository.delete(bookIdForDeletion);
            view.write(String.format("Book with name %s and author id %d was successfully deleted", removedBook.getName(), removedBook.getAuthorId()));
            view.write("You can enter new command.");
        } catch (BookRepositoryException e) {
            view.write("Error in book deletion. Please try again.");
        }
    }
}
