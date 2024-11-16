package library.command;

import library.author.Author;
import library.author.AuthorService;
import library.console.View;
import library.model.Book;
import library.model.Publication;
import library.storage.ListRepository;
import library.storage.Repository;

import java.util.List;


public class RemoveBook implements Command {
    ListRepository storage;
    Repository<Author> authors;
    private final View view;

    public RemoveBook(ListRepository storage, Repository<Author> authors, View view) {
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
        Book removedBook = null;
        Long bookIdForDeletion = null;
        view.write("Enter publication name for deletion.");
        String deleteName = view.read();

        view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");

        long authorId = AuthorService.authorSelection(authors, view);

        List<Publication> deletionPublication = storage.findBooks(deleteName, authorId);

        if(deletionPublication.isEmpty()) {
            view.write(String.format("There is no book with with name %s and author id %d", deleteName, authorId));
            return;
        }

        if(deletionPublication.size() > 1) {
            ListRepository repoWithBookForDeletion = new ListRepository(deletionPublication);
            view.write("Such combination has several books.");
            while (bookIdForDeletion == null) {
                view.write("Please enter publication id for deletion from the list: ");
                repoWithBookForDeletion.print();
                bookIdForDeletion = view.readLong();
            }
            removedBook = (Book)repoWithBookForDeletion.findById(bookIdForDeletion);
        }

        if(deletionPublication.size() == 1) {
            removedBook = (Book) deletionPublication.get(0);
            bookIdForDeletion = removedBook.getPublicationId();
        }
        storage.removeById(bookIdForDeletion);

        view.write(String.format("Book with name %s and author id %d was successfully deleted", removedBook.getName(), removedBook.getAuthorId()));
        view.write("You can enter new command.");
    }
}
