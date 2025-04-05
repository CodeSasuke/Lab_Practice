package RSA;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int p = 5;
        int q = 7;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 5;
        while(gcd(e,phi)!=1){
            e++;
        }
        int d = modInverse(e,phi);
        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        int encyrpted = in.readInt();
        int decrypted = modPow(encyrpted,d,n);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        System.out.println("Encyrpted message received is : " + encyrpted);
        System.out.println("Decrypted message is : " + decrypted);
        out.writeInt(decrypted);
        System.out.println("Decrypted message sent to client.");
        socket.close();
        serverSocket.close();
    }
    static int gcd(int a, int b){
        while(b!=0){
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    static int modInverse(int a, int m){
        for(int x = 1; x < m; x++){
            if((a * x) % m == 1){
                return x; // :)
            }
        }
        return -1; // No inverse found :(
    }
    static int modPow(int base, int exp, int mod){
        int result = (int) Math.pow(base, exp) % mod;
        return result;
        }
}
