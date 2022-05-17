package Cryptography;

public interface CipherI {
    void encrypt(String message, String key);
    void decrypt(String message, String key);
    char[] getMessage();
    char[] getCipher();
}
