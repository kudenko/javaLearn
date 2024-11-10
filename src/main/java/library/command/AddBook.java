package library.command;

import library.author.Author;
import library.author.AuthorService;
import library.console.View;
import library.model.Book;
import library.model.Publication;
import library.storage.Repository;

public class AddBook implements Command {
    Repository<Publication> storage;
    Repository<Author> authors;
    private final View view;

    public AddBook(Repository<Publication> storage, Repository<Author> authors, View view) {
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

        long authorId = AuthorService.authorSelection(authors, view);
        storage.addEntity(new Book(bookName, countOfPages, authorId));

        view.write(String.format("Book with name '%s' pages count '%d', " +
                "author ID '%d', was successfully added%n", bookName, countOfPages, authorId));
        view.write("You can enter new command.");
    }
}
