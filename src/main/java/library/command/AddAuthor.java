package library.command;

import library.author.Author;
import library.console.View;
import library.exceptions.AuthorRepositoryException;
import library.storage.AuthorRepositoryCustom;

public class AddAuthor implements Command {
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public AddAuthor(AuthorRepositoryCustom<Author> authors, View view) {
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.ADD_AUTHOR.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("Enter Author's firstname.");
        String authorName = view.read();

        view.write("Enter Author's lastname.");
        String authorLastName = view.read();

        view.write("Enter Author's email.");
        String email = view.read();

        if(!authors.findByEmail(email).isEmpty()) {
            view.write(String.format("The author with email %s already exists", email));
            view.write("Please, enter new command.");
            return;
        }
        try {
            authors.save(new Author(authorName, authorLastName, email));
            view.write(String.format("Author '%s' '%s', " +
                    "with email '%s', was successfully added%n", authorName, authorLastName, email));
            view.write("You can enter next command.");
        } catch (AuthorRepositoryException e) {
            view.write("Author save didn't work. Please try again.");
        }
    }
}
