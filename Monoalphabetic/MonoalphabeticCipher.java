package Monoalphabetic;

public class MonoalphabeticCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KEY = "QWERTYUIOPASDFGHJKLZXCVBNM";
    public static String encrypt(String plaintext){
        plaintext = plaintext.toUpperCase();
        StringBuilder result = new StringBuilder();
    for(char ch : plaintext.toCharArray()){
        if(Character.isLetter(ch)){
            int index = ALPHABET.indexOf(ch);
            result.append(KEY.charAt(index));
        }
        else{
            result.append(ch); // skips symbols
        }
    }
    return result.toString();
    }
    public static String decrypt(String cipherText){
        cipherText = cipherText.toUpperCase();
        StringBuilder result = new StringBuilder();
    for(char ch : cipherText.toCharArray()){
        if(Character.isLetter(ch)){
            int index = KEY.indexOf(ch);
            result.append(ALPHABET.charAt(index));
        }
        else{
            result.append(ch); // skips symbols
        }
    }
    return result.toString();
    }
}