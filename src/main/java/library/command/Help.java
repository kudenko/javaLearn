package library.command;

import library.console.View;

import java.util.Arrays;

import static library.command.ConsoleCommand.*;

public class Help implements Command {
    private static final ConsoleCommand commandName = HELP;
    private final View view;

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canHandle(String command) {
        return (command.equals(commandName.getCommandName()));
    }

    @Override
    public void handle() {
        view.write("List of the commands: ");
        Arrays.stream(values()).forEach(command -> {
            view.write(String.format("%s - %s", command.getCommandName(), command.getCommandAction()));
        });
    }
}
