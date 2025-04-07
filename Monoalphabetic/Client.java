package Monoalphabetic;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to server");
        String plainText = "HELLO WORLD";
        String cipherText = MonoalphabeticCipher.encrypt(plainText);
        System.out.println("PlainText : " + plainText);
        System.out.println("CipherText : " + cipherText);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(cipherText);
        System.out.println("CipherText sent to server");
        socket.close();
        System.out.println("Client stopped");
    }
}
