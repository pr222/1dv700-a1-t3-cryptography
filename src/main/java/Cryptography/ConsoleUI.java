package Cryptography;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scan;

    public ConsoleUI() {
        scan = new Scanner(System.in);
    }

    public String askForMenuOptions(String header, ArrayList<String> options) {
        System.out.println("*************************");
        System.out.println("* " + header);
        for (int i = 0; i < options.size(); i++) {
            System.out.println("[" + i + "] " + options.get(i));
        }

        return scan.nextLine();
    }

    public String askForKey() {
        System.out.println("*************************");
        System.out.println("* Enter a key to use: ");

        return scan.nextLine();
    }

    public String askForMessageToProcess() {
        System.out.println("*************************");
        System.out.println("* Enter fullpath of file: ");
        return scan.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println("* " + message);
    }
}
