package RSA;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        int p = 5, q = 7;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 5;
        while(gcd(e,phi)!=1){
            e++;
        }
        int message = 12;
        int encyrpted = modPow(message,e,n);
        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Message: " + message);
        System.out.println("Encyrpted message is : " + encyrpted);
        Socket socket = new Socket("localhost", 5000);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        out.writeInt(encyrpted);
        int decyrpted = in.readInt();
        System.out.println("Decrypted message is : " + decyrpted);
        socket.close();
        
    }
    static int modPow(int message, int exp, int mod){
        int result = (int) Math.pow(message, exp) % mod;
        return result;
    }
    static int gcd(int a, int b){
        while (b!=0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
