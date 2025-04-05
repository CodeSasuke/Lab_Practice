// Alice.java
package Diffie_Hellman;
import java.io.*;
import java.net.*;

public class Alice {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        int privateKey = 6;
        int publicKey = (int) Math.pow(Common.G, privateKey) % Common.P;

        System.out.println("Alice Private Key: " + privateKey);
        System.out.println("Alice Public Key: " + publicKey);

        // Send public key
        out.writeInt(publicKey);

        // Receive Bob's (MitM-ed) public key
        int receivedKey = in.readInt();
        System.out.println("Received (Fake) Public Key: " + receivedKey);

        int sharedKey = (int) Math.pow(receivedKey, privateKey) % Common.P;
        System.out.println("Shared Secret (with Eve): " + sharedKey);

        socket.close();
    }
}
