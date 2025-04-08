package AES;

import java.net.Socket;
import java.io.OutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        byte[] plain = "000000000000000".getBytes(); // Must be 16 bytes
        byte[] key = "1234567890abcdef".getBytes();  // 16 bytes key

        byte[] encrypted = AESUtils.encryptBlock(plain, key);

        Socket socket = new Socket("localhost", 12345);
        OutputStream out = socket.getOutputStream();

        out.write(encrypted);
        System.out.println("Encrypted message sent to server.");

        socket.close();
    }
}
