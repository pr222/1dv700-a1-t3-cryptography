package Cryptography;

public class App {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        Controller c = new Controller(ui);
        c.run();
    }
}
