package Cryptography;

public class SubsitutionCipher implements CipherI {
    private final char[] lowercase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final char[] uppercase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
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

        // loop all letters in message
        for (int i = 0; i < cipher.length; i++) {
            // loop the alphabet
            for (int j = 0; j < lowercase.length; j++) {
                // change lower or uppercase letters
                if (cipher[i] == lowercase[j]) {
                    int modif = (j + key) % 26;

                    this.cipher[i] = lowercase[modif];
                    break;
                } else if (cipher[i] == uppercase[j]) {
                    int modif = (j + key) % 26;

                    this.cipher[i] = uppercase[modif];
                    break;
                }
            }
        }
    }

    private void decryptProcess() {
        message = cipher.clone();

        // loop all letters in message
        for (int i = 0; i < message.length; i++) {
            // loop the alphabet
            for (int j = 0; j < lowercase.length; j++) {
                // change lower or uppercase letters
                if (message[i] == lowercase[j]) {
                    int modifier = findDecryptModifier(j);
                    this.message[i] = lowercase[modifier];

                    break;
                } else if (message[i] == uppercase[j]) {
                    int modifier = findDecryptModifier(j);

                    this.message[i] = uppercase[modifier];
                    break;
                }
            }
        }
    }

    private int findDecryptModifier(int baseMod) {
        int modifier = (baseMod - key) % 26;

        while (modifier < 26) {
            modifier = modifier + 26;
        }
        while (modifier >= 26) {
            modifier = modifier - 26;
        }

        return modifier;
    }
}
