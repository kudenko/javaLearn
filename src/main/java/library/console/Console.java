package library.console;

import java.util.Scanner;

public class Console implements View {
    private static final Scanner scanner = new Scanner(System.in);

    public Console() {
    }

    public void getConsole() {
        System.out.println("Please, enter a commend. Enter 'help' for command list ");

    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String input) {
        System.out.println(input);
    }

    public int readInt() {
        while(true) {
            try {
                return Integer.parseInt(read());
            } catch (NumberFormatException e) {
                write("Passed String is not a number. Please, enter a number");
            }
        }
    }
}
