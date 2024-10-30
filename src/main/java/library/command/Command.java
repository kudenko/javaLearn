package library.command;

public interface Command {
    boolean canHandle(String command);

    void handle();
}
