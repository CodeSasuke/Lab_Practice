package Diffie_Hellman;

// Bob.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Bob {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5001);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        int privateKey = 15; // Bob's private key
        int publicKey = (int) Math.pow(Common.G, privateKey) % Common.P;

        System.out.println("Bob Private Key: " + privateKey);
        System.out.println("Bob Public Key: " + publicKey);

        // Send public key
        out.writeInt(publicKey);

        // Receive Alice's (MitM-ed) public key
        int receivedKey = in.readInt();
        System.out.println("Received (Fake) Public Key: " + receivedKey);

        int sharedKey = (int) Math.pow(receivedKey, privateKey) % Common.P;
        System.out.println("Shared Secret (with Eve): " + sharedKey);

        socket.close();
    }
}

