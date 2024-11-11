package library.command;

import library.author.Author;
import library.console.View;
import library.storage.Repository;

public class AddAuthor implements Command {
    Repository<Author> authors;
    private final View view;

    public AddAuthor(Repository<Author> authors, View view) {
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

        authors.addEntity(new Author(authorName, authorLastName, email));

        view.write(String.format("Author '%s' '%s', " +
                "with email '%s', was successfully added%n", authorName, authorLastName, email));
        view.write("You can enter next command.");
    }
}
