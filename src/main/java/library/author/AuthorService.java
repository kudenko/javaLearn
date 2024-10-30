package library.author;

import library.console.View;
import library.storage.InMemoryAuthorStorage;
import library.utils.Pagination;

public class AuthorService {
    public static long authorSelection(InMemoryAuthorStorage authorsStorage, View view) {
        if (authorsStorage.getEntitiesList().isEmpty()) {
            view.write("Authors shouldn't be empty");
            throw new RuntimeException("Authors shouldn't be empty");
        }

        int startingPage = 1;
        Long authorId = null;
        while (authorId == null) {
            Pagination.printPagination(authorsStorage.getEntitiesList(), 10, startingPage, view);
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

    private static Long selectAuthorIdFromTheList(String enteredText, InMemoryAuthorStorage authorsStorage, View view) {
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
