package library.author;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InMemoryAuthorStorage {

    List<Author> authorsStorage;

    public InMemoryAuthorStorage() {
        authorsStorage = new ArrayList<>();
    }

    public InMemoryAuthorStorage(List<Author> authorsStorage) {
        this.authorsStorage = authorsStorage;
    }

    public Author findById(long id) {
        return authorsStorage.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public Author findByEmail(String email) {
        return authorsStorage.stream().filter(author -> author.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void addAuthor(Author author) {
        if (!authorsStorage.contains(author)) {
            authorsStorage.add(author);
        } else {
            System.out.println("This author isn't new. Author " + author.getFirstName() + " " + author.getLastName() + " was modified");
            editAuthor(author);
        }
    }

    //TODO should it add in case of author isn't in the system? Same question is for addAuthor method.
    public void editAuthor(Author author) {
        if (authorsStorage.contains(author)) {
            authorsStorage.set(authorsStorage.indexOf(author), author);
        } else {
            System.out.println("This author isn't in the system. Please check the data.");
        }
    }

    public void removeAuthor(String email) {
        authorsStorage = authorsStorage.stream().filter(author -> !author.getEmail().equals(email)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return authorsStorage.stream().map(Author::toString).collect(Collectors.joining("\n"));
    }

    private void printPagination(int itemsPerPage, int page) {
        System.out.println("Select author's ID from the list: ");
        int pagesCount = authorsStorage.size() / itemsPerPage;
        if (authorsStorage.size() - pagesCount * itemsPerPage > 0) {
            pagesCount++;
        }
        if (page > pagesCount) {
            System.out.println("There is no " + page + " page. We have only " + pagesCount + " pages. Use 'p' key");
            return;
        }
        if (page < 0) {
            System.out.println("There is no negative pages. Use 'n' key for the next one");
            return;
        }
        System.out.println("Page: " + page + ", Items: " + (((page - 1)) * itemsPerPage) + " " + ((page - 1) * itemsPerPage + itemsPerPage));
        List<Author> printList = authorsStorage.subList((page - 1) * itemsPerPage, ((page - 1) * itemsPerPage + itemsPerPage));
        System.out.println(printList);
    }

    public long authorSelection(int itemsPerPage, Scanner scanner) {
        if (itemsPerPage <= 0) {
            System.out.println("items per page should be > 0");
            throw new RuntimeException("items Per page should be > 0");
        }
        if (authorsStorage.isEmpty()) {
            System.out.println("Authors should be not empty");
            throw new RuntimeException("Authors should be not empty");
        }
        int startingPage = 1;
        while (true) {
            printPagination(10, startingPage);

            String enteredText = scanner.nextLine();
            if (enteredText.equals("n")) {
                startingPage++;
            } else if (enteredText.equals("p")) {
                startingPage--;
            } else {
                try {
                    long authorId = Long.parseLong(enteredText);
                    if (findById(authorId) != null) {
                        return authorId;
                    } else {
                        System.out.println("The id " + authorId + " isn't in the system");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The ID should be in the number format. Or it should be letters 'p' or 'n'");
                }
            }
        }
    }
}
