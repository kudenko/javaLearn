import library.author.Author;
import library.command.*;
import library.console.Console;
import library.console.View;
import library.model.Book;
import library.model.Journal;
import library.storage.AuthorRepositoryCustom;
import library.storage.BookRepositoryCustom;
import library.storage.JournalRepositoryCustom;
import library.storage.Repository;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initApp(BookRepositoryCustom<Book> bookRepository, AuthorRepositoryCustom<Author> authorRepository, JournalRepositoryCustom<Journal> journalRepository) {
        View view = new Console();
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new AddJournal(journalRepository, view));
        commands.add(new AddBook(bookRepository, authorRepository, view));
        commands.add(new AddAuthor(authorRepository, view));
        commands.add(new RemoveBook(bookRepository, authorRepository, view));
        commands.add(new Print(view, bookRepository, journalRepository));
        commands.add(new RemoveJournal(journalRepository, view));
        commands.add(new RemoveAuthor(bookRepository, authorRepository, view));
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
