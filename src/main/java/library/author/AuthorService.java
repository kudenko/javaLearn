package library.author;

import library.command.AddAuthor;
import library.console.View;
import library.storage.InMemoryAuthorStorage;
import library.storage.Repository;
import library.utils.Pagination;

public class AuthorService {
    public static long authorSelection(Repository<Author> authorsStorage, View view) {
        if (authorsStorage.getEntitiesList().isEmpty()) {
            view.write("Authors shouldn't be empty. Please create a new one.");
            new AddAuthor(authorsStorage, view).handle();
        } else {
            view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");
        }

        int startingPage = 1;
        Long authorId = null;
        while (authorId == null) {
            Pagination.printPagination(authorsStorage.getEntitiesList(),
                    authorsStorage.getEntitiesList().size() >= 10 ? 10 : authorsStorage.getEntitiesList().size(),
                    startingPage, view);
            String enteredText = view.read();
            if (enteredText.equals("n")) {
                startingPage++;
            } else if (enteredText.equals("p")) {
                startingPage--;
            } else {
                authorId = selectAuthorIdFromTheList(enteredText, authorsStorage, view);
            }
        }
        return authorId;
    }

    public static InMemoryAuthorStorage createAuthors() {
        InMemoryAuthorStorage authors = new InMemoryAuthorStorage();
        for (int i = 1; i < 31; i++) {
            authors.addEntity(new Author(String.format("FirstName_%d", i), String.format("LastName_%d", i), String.format("email@test%d", i)));
        }
        return authors;
    }

    private static Long selectAuthorIdFromTheList(String enteredText, Repository<Author> authorsStorage, View view) {
        try {
            long authorId = Long.parseLong(enteredText);
            if (authorsStorage.findById(authorId) != null) {
                return authorId;
            } else {
                view.write("The id " + authorId + " isn't in the system");
            }
        } catch (NumberFormatException e) {
            view.write("The ID should be in the number format. Or it should be letters 'p' or 'n'");
        }
        return null;
    }
}
