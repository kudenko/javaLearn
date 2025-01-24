package app.library.console;

public interface View {
    String read();
    int readInt();
    void write(String input);
    long readLong();
}
