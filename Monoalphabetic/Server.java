package Monoalphabetic;
import java.io.*;
import java.net.*;

import javax.xml.crypto.Data;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        String cipherText = input.readUTF();
        System.out.println("CipherText received : " + cipherText);
        String decryptedText = MonoalphabeticCipher.decrypt(cipherText);
        System.out.println("Decrypted Text : " + decryptedText);
        socket.close();
        serverSocket.close();
        System.out.println("Server stopped");   

    }

}
