package Classical;

public class CaesarCipher {
    public static String encrypt(String text, int shift){
        StringBuilder result = new StringBuilder();
        shift = shift % 26;
        for(char ch : text.toCharArray()){
            if(Character.isLetter(ch)){
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encrypted = (char) ((ch - base + shift) % 26 + base);
                result.append(encrypted);
            }
            else{
                result.append(ch); // keep the symbols unchanged
            }
        }
        return result.toString();
    }
    public static String decrypt(String text, int shift){
        return encrypt(text, 26 - shift);
    }
}
