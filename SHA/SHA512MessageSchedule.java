package SHA;
import java.util.Arrays;

public class SHA512MessageSchedule {

    public static long[] createMessageSchedule(byte[] block) {
        long[] W = new long[80];

        // Step 1: W[0..15] from block
        for (int i = 0; i < 16; i++) {
            W[i] = 0;
            for (int j = 0; j < 8; j++) {
                W[i] = (W[i] << 8) | (block[i * 8 + j] & 0xFF);
            }
        }

        // Step 2: W[16..79] using σ functions
        for (int i = 16; i < 80; i++) {
            long s0 = rotr(W[i - 15], 1) ^ rotr(W[i - 15], 8) ^ (W[i - 15] >>> 7);
            long s1 = rotr(W[i - 2], 19) ^ rotr(W[i - 2], 61) ^ (W[i - 2] >>> 6);
            W[i] = W[i - 16] + s0 + W[i - 7] + s1;
        }

        return W;
    }

    public static long rotr(long x, int n) {
        return (x >>> n) | (x << (64 - n));
    }

    public static void main(String[] args) {
        // 128-byte dummy padded block (normally generated after padding)
        byte[] dummyBlock = new byte[128];
        Arrays.fill(dummyBlock, (byte) 0xAB); // just test data

        long[] W = createMessageSchedule(dummyBlock);

        for (int i = 0; i < W.length; i++) {
            System.out.printf("W[%02d] = %016x\n", i, W[i]);
        }
    }
}
