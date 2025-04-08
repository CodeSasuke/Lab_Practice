package HMAC;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        Socket socket = server.accept();
        InputStream in = socket.getInputStream();

        byte[] buffer = new byte[20]; // HMAC-SHA1 = 20 bytes
        in.read(buffer);

        System.out.println("Received HMAC:");
        for (byte b : buffer)
            System.out.printf("%02x", b);
        System.out.println();

        socket.close();
        server.close();
    }
}
