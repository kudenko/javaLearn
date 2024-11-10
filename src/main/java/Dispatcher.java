import library.author.Author;
import library.author.AuthorService;
import library.command.*;
import library.console.Console;
import library.console.View;
import library.model.Publication;
import library.storage.InMemoryAuthorStorage;
import library.storage.ListRepository;
import library.storage.Repository;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initApp() {
        Repository<Author> authorStorage = new InMemoryAuthorStorage();
//        Repository<Author> authorStorage = AuthorService.createAuthors();

        Repository<Publication> storage = new ListRepository();
        View view = new Console();
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new AddJournal(storage, view));
        commands.add(new AddBook(storage, authorStorage, view));
        commands.add(new AddAuthor(authorStorage, view));
        commands.add(new RemoveBook(storage,authorStorage, view));
        commands.add(new Print(view, storage));
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
