import library.command.Command;
import library.command.Help;
import library.storage.InMemoryAuthorStorage;
import library.console.Console;
import library.console.View;
import library.model.Journal;
import library.storage.Repository;
import library.storage.ArrayRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InMemoryAuthorStorage authorStorage = new InMemoryAuthorStorage();
        Repository storage = new ArrayRepository();
        View view = new Console();
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));

        execution(storage, authorStorage, view, commands);
    }

    private static void execution(Repository storage, InMemoryAuthorStorage authorStorage, View view, List<Command> commands) {

        while (true) {
            System.out.println("Please, enter a command. Enter 'help' for command list ");
            String commandInput = view.read();

            for (Command command: commands) {
                if (command.canHandle(commandInput)) {
                    command.handle();
                } else {
                    view.write("Command is not available");
                }
            }
        }
//        String command;
//        while (true){
//            command = view.read();
//            switch (command) {
//                case "help": {
//                    view.write("List of the commands: ");
//                    view.write("'help' - list of the commands");
//                    view.write("'exit' - exit the program");
//                    view.write("'addJournal' - adds journal to the library");
//                    view.write("'addBook' - adds book to the library");
//                    view.write("'removeBook' - removes book from the library");
//                    view.write("'print' - prints all books in the library");
//                }
//                break;
//                case "addJournal": {
//                    view.write("Enter Journal name.");
//                    String journalName = view.read();
//                    view.write("Enter count of pages.");
//                    int countOfPages = Integer.parseInt(view.read());
//                    view.write("Enter Journal number.");
//                    int jNumber = Integer.parseInt(view.read());
//                    view.write("Enter publication year.");
//                    int pubYear = Integer.parseInt(view.read());
//
//                    storage.addPublication(new Journal(journalName, countOfPages, jNumber, pubYear));
//                    System.out.printf("Journal with name '%s' pages count '%d', " +
//                            "number '%d'; and year '%d' was successfully added%n", journalName, countOfPages, jNumber, pubYear);
//                    view.write("You can enter new command.");
//                }
//                break;
//                case "addBook": {
//                    view.write("Enter Book name.");
//                    String bookName = view.read();
//
//                    view.write("Enter count of pages.");
//                    int countOfPages = Integer.parseInt(view.read());
//                    view.read();
//
//                    view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");
////TODO refactor
////                    long authorId = authorStorage.authorSelection(10, scanner);
//
////                    storage.addPublication(new Book(bookName, countOfPages, authorId));
//
////                    System.out.printf("Book with name '%s' pages count '%d', " +
////                            "author ID '%d' was successfully added%n", bookName, countOfPages, authorId);
////                    view.write("You can enter new command.");
//                }
//                break;
//                case "removeBook": {
//                    view.write("Enter publication name for deletion.");
//                    String deleteName = view.read();
//
//                    view.write("Enter Author by ID from the list. Enter 'p' for previous page and 'n' for next page");
//
////                    long authorId = authorStorage.authorSelection(10, scanner);
//
////                    storage.removePublication(new Book(deleteName, 0, authorId));
//
////                    System.out.printf("Book with name %s and author count %s was successfully deleted", deleteName, authorId);
////                    view.write();
////                    view.write("You can enter new command.");
//                }
//                break;
//                case "print": {
//                    view.write("Available publications: ");
//                    storage.print();
//                    view.write("You can enter new command.");
//                }
//                break;
//                case "exit": {
//                    view.write("See you next time!!!");
//                    return;
//                }
//                default: {
//                    view.write("Command was wrong. Please, enter a command. Enter 'help' for command list");
//                }
//            }
//        }
    }
}
