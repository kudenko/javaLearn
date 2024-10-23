package library.console;

import library.Book;
import library.Journal;
import library.author.InMemoryAuthorStorage;
import library.storage.Storagable;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);
    private Storagable storage;
    private InMemoryAuthorStorage inMemoryAuthorStorage;

    public Console() {
    }

    public Console(Storagable storage, InMemoryAuthorStorage authorStorage) {
        this.storage = storage;
        inMemoryAuthorStorage = authorStorage;
    }

    public void getConsole() {
        String command;
        System.out.println("Please, enter a commend. Enter 'help' for command list ");
        do {
            command = scanner.nextLine();
            switch (command) {
                case "help": {
                    System.out.println("List of the commands: ");
                    System.out.println("'help' - list of the commands");
                    System.out.println("'exit' - exit the program");
                    System.out.println("'addJournal' - adds journal to the library");
                    System.out.println("'addBook' - adds book to the library");
                    System.out.println("'removeBook' - removes book from the library");
                    System.out.println("'print' - prints all books in the library");

                }
                break;
                case "addJournal": {
                    System.out.println("Enter Journal name.");
                    String journalName = scanner.nextLine();
                    System.out.println("Enter count of pages.");
                    int countOfPages = scanner.nextInt();
                    System.out.println("Enter Journal number.");
                    int jNumber = scanner.nextInt();
                    System.out.println("Enter publication year.");
                    int pubYear = scanner.nextInt();

                    storage.addPublication(new Journal(journalName, countOfPages, jNumber, pubYear));
                    System.out.printf("Journal with name '%s' pages count '%d', " +
                            "number '%d'; and year '%d' was successfully added%n", journalName, countOfPages, jNumber, pubYear);
                    System.out.println("You can enter new command.");
                }
                break;
                case "addBook": {
                    System.out.println("Enter Book name.");
                    String bookName = scanner.nextLine();

                    System.out.println("Enter count of pages.");
                    int countOfPages = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");

                    long authorId = inMemoryAuthorStorage.authorSelection(10, scanner);

                    storage.addPublication(new Book(bookName, countOfPages, authorId));

                    System.out.printf("Book with name '%s' pages count '%d', " +
                            "author ID '%d' was successfully added%n", bookName, countOfPages, authorId);
                    System.out.println("You can enter new command.");
                }
                break;
                case "removeBook": {
                    System.out.println("Enter publication name for deletion.");
                    String deleteName = scanner.nextLine();

                    System.out.println("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");

                    long authorId = inMemoryAuthorStorage.authorSelection(10, scanner);

                    storage.removePublication(new Book(deleteName, 0, authorId));

                    System.out.printf("Book with name %s and author count %s was successfully deleted", deleteName, authorId);
                    System.out.println();
                    System.out.println("You can enter new command.");
                }
                break;
                case "print": {
                    System.out.println("Available publications: ");
                    storage.print();
                    System.out.println("You can enter new command.");
                }
                break;
                case "exit": {
                    System.out.println("See you next time!!!");
                    return;
                }
            }

        } while (scanner.hasNext());
    }
}
