package library.console;

import library.Book;
import library.Journal;
import library.Storage;

import java.util.Scanner;

public class Console {
    public Console() {
    }

    public Console(Storage storage) {
        this.storage = storage;
    }


    private Scanner scanner = new Scanner(System.in);
    private Storage storage;

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

                    System.out.println("Enter Author.");
                    String author = scanner.nextLine();

                    storage.addPublication(new Book(bookName, countOfPages, author));

                    System.out.printf("Book with name '%s' pages count '%d', " +
                            "author '%s' was successfully added%n", bookName, countOfPages, author);
                    System.out.println("You can enter new command.");
                }
                break;
                case "exit": {
                    return;
                }
            }

        } while (scanner.hasNext());
    }
}
