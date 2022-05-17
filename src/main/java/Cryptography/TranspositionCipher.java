package Cryptography;

public class TranspositionCipher implements CipherI {
    private char[] message;
    private int key;
    private char[] cipher;

    public void encrypt(String message, String key) {
        this.message = message.toCharArray();
        formatKey(key);
        encryptProcess();
    }

    public void decrypt(String cipher, String key) {
        this.cipher = cipher.toCharArray();
        formatKey(key);
        decryptProcess();
    }

    public char[] getCipher() {
        return cipher;
    }

    public char[] getMessage() {
        return message;
    }

    private void formatKey(String key) {
        try {
            this.key = Integer.parseInt(key);
        } catch (Exception error) {
            this.key = key.hashCode();
        }
    }

    private void encryptProcess() {
        cipher = message.clone();

        int rows = key;
        int columns;

        if (cipher.length % key != 0) {
            columns = cipher.length / key + 1;
        } else {
            columns = cipher.length / key;
        }

        char[][] matrix = new char[rows][columns];
        int letterCount = 0;

        // Map out letters to matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (letterCount < cipher.length) {
                    matrix[i][j] = cipher[letterCount];
                    letterCount += 1;
                }
            }
        }
        letterCount = 0;

        // Modify cipher according to matrix
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (matrix[j][i] != 0) {
                    if (letterCount < cipher.length) {
                        cipher[letterCount] = matrix[j][i];
                        letterCount += 1;
                    }
                }
            }
        }
    }

    private void decryptProcess() {
        message = cipher.clone();

        int columns = key;
        int rows;

        if (message.length % key != 0) {
            rows = message.length / key + 1;
        } else {
            rows = message.length / key;
        }

        char[][] matrix = new char[rows][columns];
        int letterCount = 0;

        // Map out letters to matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (letterCount < message.length) {
                    matrix[i][j] = message[letterCount];
                    letterCount += 1;
                }
            }
        }
        letterCount = 0;

        // Modify cipher according to matrix
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (matrix[j][i] != 0) {
                    if (letterCount < message.length) {
                        message[letterCount] = matrix[j][i];
                        letterCount += 1;
                    }
                }
            }
        }
    }
}
