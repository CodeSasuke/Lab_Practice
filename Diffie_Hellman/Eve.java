// Eve.java
package Diffie_Hellman;
import java.io.*;
import java.net.*;

public class Eve {
    public static void main(String[] args) throws Exception {
        ServerSocket aliceSocket = new ServerSocket(5000);
        ServerSocket bobSocket = new ServerSocket(5001);

        System.out.println("Waiting for Alice...");
        Socket alice = aliceSocket.accept();
        System.out.println("Alice connected.");

        System.out.println("Waiting for Bob...");
        Socket bob = bobSocket.accept();
        System.out.println("Bob connected.");

        DataInputStream aliceIn = new DataInputStream(alice.getInputStream());
        DataOutputStream aliceOut = new DataOutputStream(alice.getOutputStream());

        DataInputStream bobIn = new DataInputStream(bob.getInputStream());
        DataOutputStream bobOut = new DataOutputStream(bob.getOutputStream());

        // Read public keys from both
        int alicePub = aliceIn.readInt();
        int bobPub = bobIn.readInt();

        System.out.println("Eve intercepted Alice's Key: " + alicePub);
        System.out.println("Eve intercepted Bob's Key: " + bobPub);

        // Replace public keys (MitM attack)
        int fakeKeyForBob = 6;   // Eve sends a fake key to Bob
        int fakeKeyForAlice = 15; // Eve sends a fake key to Alice

        // Send fake keys
        bobOut.writeInt(fakeKeyForBob);
        aliceOut.writeInt(fakeKeyForAlice);

        alice.close();
        bob.close();
        aliceSocket.close();
        bobSocket.close();
    }
}
