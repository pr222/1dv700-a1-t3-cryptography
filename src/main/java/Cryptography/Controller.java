package Cryptography;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final ConsoleUI ui;
    private CipherI cipher;
    private boolean decipher;
    private String key;
    private String message;
    private String filePath;
    private String fileName;

    Controller (ConsoleUI ui) {
        this.ui = ui;
        this.decipher = false;
    }

    public void run() {
        chooseCipher();
        chooseCipherDirection();
        chooseKey();
        chooseMessage();
        process();
    }

    private void chooseCipher() {
        ArrayList<String> mainMenu = new ArrayList<>();
        mainMenu.add("Substitution Cipher");
        mainMenu.add("Transposition Cipher");

        boolean isValid = false;
        while (!isValid) {
            String option = ui.askForMenuOptions("Choose a Cipher", mainMenu);
            if (option.equals("0")) {
                cipher = new SubsitutionCipher();
                isValid = true;
            } else if (option.equals("1")) {
                isValid = true;
                cipher = new TranspositionCipher();
            }
        }
    }

    private void chooseCipherDirection() {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Encrypt");
        menu.add("Decrypt");

        boolean isValid = false;
        while (!isValid) {
            String option = ui.askForMenuOptions("Choose what to do", menu);
            if (option.equals("0")) {
                decipher = false;
                isValid = true;
            } else if (option.equals("1")) {
                isValid = true;
                decipher = true;
            }
        }
    }

    private void chooseKey() {
        boolean isValid = false;
        while (!isValid) {
            String userKey = ui.askForKey();

            if (userKey.length() == 1 && userKey.hashCode() <= 255) {
                isValid = true;
                this.key = userKey;
            } else {
                ui.displayMessage("Not a valid key.");
            }
        }
    }

    private void chooseMessage() {
        try {
            String userPath = ui.askForMessageToProcess();
            File file = new File(userPath);
            this.fileName = file.getName();
            this.filePath = file.getParent() + "/";

            Scanner scan = new Scanner(file);
            StringBuilder content = new StringBuilder();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                content.append(line).append("\n");
            }

            this.message = content.toString();
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        char[] processed;

        if (decipher) {
            cipher.decrypt(message, key);
            processed = cipher.getMessage();
            this.filePath += "decrypted-";
        } else {
            cipher.encrypt(message, key);
            processed = cipher.getCipher();
            this.filePath += "encrypted-";
        }
        this.filePath += this.fileName;

        StringBuilder result = new StringBuilder();
        for (char c: processed) {
            result.append(c);
        }

        writeToFile(result.toString());

        ui.displayMessage("Finished, closing application...");
    }

    private void writeToFile(String content) {
        try {
            File file = new File(this.filePath);
            PrintWriter printer = new PrintWriter(file);

            printer.print(content);
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
