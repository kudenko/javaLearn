package app.library.command;

import app.library.author.Author;
import app.library.console.View;
import app.library.model.Book;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.BookRepositoryCustom;

import java.util.List;


public class RemoveAuthor implements Command {
    BookRepositoryCustom<Book> bookRepository;
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public RemoveAuthor(BookRepositoryCustom<Book> bookRepository, AuthorRepositoryCustom<Author> authors, View view) {
        this.bookRepository = bookRepository;
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.REMOVE_AUTHOR.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter Author's email for deletion.");
        String deleteEmail = view.read();

        List<Author> deleteAuthors = authors.findByEmail(deleteEmail);

        if(deleteAuthors.isEmpty()) {
            view.write(String.format("There is no author with email %s", deleteEmail));
            view.write("Please, enter new command.");
            return;
        }

        Author delAuthor = null;
        Long delAuthorId = null;

        if(deleteAuthors.size() > 1) {
            view.write("We have several authors with such email");
            while (delAuthorId == null) {
                view.write("Please enter author's id for deletion from the app.library: ");
                deleteAuthors.forEach(System.out::println);
                delAuthorId = view.readLong();
            }
            delAuthor = authors.findById(delAuthorId);
        }

        if(deleteAuthors.size() == 1) {
            delAuthor = deleteAuthors.getFirst();
            delAuthorId = delAuthor.getId();
        }

        List<Book> authorsBooks = bookRepository.findBooksByAuthorId(delAuthorId);
        if (!authorsBooks.isEmpty()) {
            view.write(String.format("Author with id %d associated with books and could not be deleted", delAuthorId));
            view.write("List of the associated books:");
            authorsBooks.forEach(System.out::println);
            return;
        }
        authors.delete(delAuthorId);

        view.write(String.format("Author with email %s and author id %d was successfully deleted", delAuthor.getEmail(), delAuthor.getId()));
        view.write("You can enter new command.");
    }
}
