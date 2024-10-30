package library.command;

import library.console.View;

import static library.command.ConsoleCommand.EXIT;

public class Exit implements Command {
    private static final ConsoleCommand commandName = EXIT;
    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(commandName.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("See you next time! Bye");
        System.exit(1);
    }
}
