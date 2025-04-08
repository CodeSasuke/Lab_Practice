package AES;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started. Waiting for client...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        InputStream in = clientSocket.getInputStream();
        byte[] buffer = new byte[16];
        int len = in.read(buffer);

        System.out.println("Encrypted bytes received:");
        for (byte b : buffer) {
            System.out.print(String.format("%02X ", b));
        }
        System.out.println();

        // You can add AESUtils.decryptBlock() here if you want to reverse it.

        clientSocket.close();
        serverSocket.close();
    }
}
