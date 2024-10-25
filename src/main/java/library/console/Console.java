package library.console;

import java.util.Scanner;

public class Console implements View {
    private static final Scanner scanner = new Scanner(System.in);

    public Console() {
    }

    public void getConsole() {
        String command;

    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String input) {
        System.out.println(input);
    }
}
