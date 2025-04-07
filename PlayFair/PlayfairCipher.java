package PlayFair;

import java.util.*;
public class PlayfairCipher {
    private static char[][] matrix = new char[5][5];
    private static String key;

    public static void setKey(String keyInput){
        key = keyInput.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder keyBuilder = new StringBuilder();

        for(char ch : key.toCharArray()) {
            if(keyBuilder.indexOf(String.valueOf(ch)) == -1){
                keyBuilder.append(ch);
            }
        }
        for(char ch = 'A'; ch <= 'Z'; ch++) {
            if(ch == 'J') continue;
            if(keyBuilder.indexOf(String.valueOf(ch)) == -1){
                keyBuilder.append(ch);
            }
        }
        int index = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                matrix[i][j] = keyBuilder.charAt(index++);
            }
        }
        
           
    }
    public static String encrypt(String text){
        return process(text, true);
    }
    public static String decrypt(String text){
        return process(text, false);
    }
    public static String process(String text, boolean encrypt){
       text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < text.length(); i+=2){
        char a = text.charAt(i);
        char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
        if(a == b){
            b = 'X';
            i--;
        }
        sb.append(processPair(a,b,encrypt));
       }
       return sb.toString();

    }
    private static String processPair(char a, char b, boolean enc){
        int[] p1 = find(a), p2 = find(b);
        int row1 = p1[0], col1 = p1[1];
        int row2 = p2[0], col2 = p2[1];
        if(row1 == row2){
            return "" + matrix[row1][(col1 + (enc ? 1 : 4)) % 5] + matrix[row2][((col2 + (enc ?1:4)) % 5)];
        }
        else if(col1 == col2){
            return "" + matrix[(row1 + (enc ? 1 : 4)) % 5][col1] + matrix[(row2 + (enc ? 1:4)) % 5][col2];
        }
        else{
            return "" + matrix[row1][col2] + matrix[row2][col1];
        }
    }
    private static int[] find(char c){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(matrix[i][j] == c){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    
}
