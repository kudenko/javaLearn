import library.author.Author;
import library.command.*;
import library.config.DatabaseConnectionManager;
import library.console.Console;
import library.console.View;
import library.model.Book;
import library.model.Journal;
import library.storage.ListRepository;
import library.storage.Repository;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initApp(Repository<Book> bookRepository, Repository<Author> authorRepository, Repository<Journal> journalRepository) {
        View view = new Console();
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new AddJournal(journalRepository, view));
        commands.add(new AddBook(bookRepository, authorRepository, view));
//        commands.add(new AddAuthor(authorStorage, view));
//        commands.add(new RemoveBook(storage,authorStorage, view));
//        commands.add(new Print(view, storage));
        execution(view, commands);
    }

    public static void execution(View view, List<Command> commands) {
        while (true) {
            String commandInput = view.read();
            boolean isCompleted = false;
            for (Command command: commands) {
                if (command.canHandle(commandInput)) {
                    command.handle();
                    isCompleted = true;
                }
            }
            if (!isCompleted) {
                view.write("Command is not available");
            }
        }
    }
}
