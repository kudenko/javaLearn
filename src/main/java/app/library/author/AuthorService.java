package app.library.author;

import app.library.command.AddAuthor;
import app.library.console.View;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.Repository;
import app.library.utils.Pagination;

public class AuthorService {
    public static long authorSelection(AuthorRepositoryCustom<Author> authorsStorage, View view) {
        checkAuthorsStorage(authorsStorage, view);

        int startingPage = 1;
        int itemsPerPage = Math.min(authorsStorage.findAll().size(), 10);
        int pages = Pagination.getPagePaginationPageCount(authorsStorage.findAll(), itemsPerPage);

        Long authorId = null;
        while (authorId == null) {
            Pagination.printPagination(authorsStorage.findAll(), itemsPerPage, startingPage, view);
            String enteredText = view.read();
            if (enteredText.equals("n") && startingPage <= pages) {
                startingPage++;
            } else if (enteredText.equals("p") && startingPage > 0) {
                startingPage--;
            } else {
                authorId = selectAuthorIdFromTheList(enteredText, authorsStorage, view);
            }
        }
        return authorId;
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

    private static void checkAuthorsStorage(AuthorRepositoryCustom<Author> authorsStorage, View view) {
        if (authorsStorage.findAll().isEmpty()) {
            view.write("Authors shouldn't be empty. Please create a new one.");
            new AddAuthor(authorsStorage, view).handle();
        } else {
            view.write("Enter Author by ID from the list." +
                    "\nEnter 'n' for next page" +
                    "\nAvailable authors: " + authorsStorage.findAll().size());
        }
    }
}
