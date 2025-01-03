package library.command;

import library.author.Author;
import library.author.AuthorService;
import library.console.View;
import library.exceptions.AuthorRepositoryException;
import library.model.Book;
import library.storage.Repository;

import java.util.List;


public class RemoveBook implements Command {
    Repository<Book> bookRepository;
    Repository<Author> authors;
    private final View view;

    public RemoveBook(Repository<Book> bookRepository, Repository<Author> authors, View view) {
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

        long authorId = AuthorService.authorSelection(authors, view);

        List<Book> deletionPublication = bookRepository.findAll().stream().filter(book -> book.getAuthorId() == authorId).filter(book -> book.getName().equals(deleteName)).toList();

        if(deletionPublication.isEmpty()) {
            //throw new AuthorRepositoryException(String.format("There is no book with name %s and author id %d", deleteName, authorId));
            view.write(String.format("There is no book with name %s and author id %d", deleteName, authorId));
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
        bookRepository.delete(bookIdForDeletion);

        view.write(String.format("Book with name %s and author id %d was successfully deleted", removedBook.getName(), removedBook.getAuthorId()));
        view.write("You can enter new command.");
    }
}
