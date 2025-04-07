package Classical;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost",1234);
        System.out.println("Connected to server:" + socket.getInetAddress());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        String message;
        System.out.println("Enter the message to encrypt");
        message = scanner.nextLine();
        output.writeUTF(message);
        System.out.println("Message sent to server: " + message);
        String encrypted = input.readUTF();
        System.out.println("Encrypted message received from server: " + encrypted);
        String decrypted = input.readUTF();
        System.out.println("Decrypted message received from server: " + decrypted);
        System.out.println("Decrypted message: " + decrypted);
        System.out.println("Closing connection...");
        socket.close();
        System.out.println("Connection closed.");
        scanner.close();
    }
}
