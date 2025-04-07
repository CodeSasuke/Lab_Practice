package Classical;
import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected: " + socket.getInetAddress());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        int shift = 1;
        String received = input.readUTF();
        String encrypted = CaesarCipher.encrypt(received, shift);
        System.out.println("Received message: " + received);
        System.out.println("Encrypted message: " + encrypted);
        output.writeUTF(encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, shift);
        System.out.println("Decrypted message: " + decrypted);
        System.out.println("Sending decrypted message back to client...");
        output.writeUTF(decrypted);
        System.out.println("Decrypted message sent to client.");
        socket.close();
        serverSocket.close();
        System.out.println("Server closed.");
    }
}