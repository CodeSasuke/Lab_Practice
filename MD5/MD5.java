package MD5;

import java.nio.charset.StandardCharsets;

public class MD5 {
    public static int F(int x, int y, int z){
        return (x & y) | (~x & z);
    }
    public static int G(int x, int y, int z){
        return (x & z) | (y & ~z);
    }
    public static int H(int x, int y, int z){
        return (x ^ y ^ z);
    }
    public static int I(int x, int y, int z){
        return (y) ^ ~(~x & z);
    }
    public static int rotateLeft(int x, int n){
        return (x << n) | (x >>> (32-n));
    }
    public static void main(String[] args) {
        byte[] msg = "abc".getBytes(StandardCharsets.UTF_8);
        int M0 = 0;
        for (int i = 0; i < 4 && i < msg.length; i++) {
            M0 |= (msg[i] & 0xff) << (8 * i); // Little-endian packing
        }
        int A = 0x67452301;
        int B = 0xefcdab89;
        int C = 0x98badcfe;
        int D = 0x10325476;
        int s = 7;
        int K0 = 0xd76aa478;
        int Fout = F(B,C,D);
        int temp = A + Fout + M0 + K0;
        A = B + rotateLeft(temp,s);
        System.out.printf("A after first round op = 0x%08x (%d)\n", A,A);
    }
}
