package PlayFair;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String key = "KEYWORD";
        String encrypted = input.readUTF();
        System.out.println("Key " + key);
        System.out.println("Encrypted message " + encrypted);
        PlayfairCipher.setKey(key);

        String decrypted = PlayfairCipher.decrypt(encrypted);
        System.out.println("Decrypted message " + decrypted);
        socket.close();
        serverSocket.close();
    }
}
