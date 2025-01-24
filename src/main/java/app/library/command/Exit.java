package app.library.command;

import app.library.console.View;

public class Exit implements Command {
    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canHandle(String command) {
        return (command.equals(ConsoleCommand.EXIT.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("See you next time! Bye");
        System.exit(1);
    }
}
