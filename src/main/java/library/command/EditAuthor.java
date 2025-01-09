package library.command;

import library.author.Author;
import library.console.View;
import library.exceptions.AuthorRepositoryException;
import library.storage.AuthorRepositoryCustom;

public class EditAuthor implements Command {
    AuthorRepositoryCustom<Author> authors;
    private final View view;

    public EditAuthor(AuthorRepositoryCustom<Author> authors, View view) {
        this.authors = authors;
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.EDIT_AUTHOR.getCommandName()));
    }

    @Override
    public void handle() {
        Long authorId = null;
        Author author = null;
        while (author == null) {
            view.write("Enter Author's id for editing from the list.");
            authors.print();
            authorId = view.readLong();
            author = authors.findById(authorId);
        }

        view.write("Enter Author's firstname for editing.");
        String authorName = view.read();

        view.write("Enter Author's lastname for editing.");
        String authorLastName = view.read();

        view.write("Enter Author's email for editing.");
        String email = view.read();

        if(!authors.findByEmail(email).isEmpty()) {
            view.write(String.format("The author with email %s already exists", email));
            view.write("Please, enter new command.");
            return;
        }

        try {
            author = new Author(authorName, authorLastName, email);
            author.setId(authorId);
            authors.update(author);
            view.write(String.format("Author '%s' '%s', " +
                    "with email '%s', was successfully updated%n", authorName, authorLastName, email));
            view.write("You can enter next command.");
        } catch (AuthorRepositoryException e) {
            view.write("Author update didn't work. Please try again.");
        }
    }
}
