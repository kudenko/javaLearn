package app.library.command;

import app.library.console.View;

import java.util.Arrays;

import static app.library.command.ConsoleCommand.*;

public class Help implements Command {
    private final View view;

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canHandle(String command) {
        return (command.equals(HELP.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("List of the commands: ");
        Arrays.stream(values()).forEach(command -> {
            view.write(String.format("%s - %s", command.getCommandName(), command.getCommandAction()));
        });
    }
}
