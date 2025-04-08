package AES;

public class AESUtils {
    // Your S-box, Rcon, and helper functions from earlier go here
    static final int[] sbox = {
        0x63,0x7c,0x77,0x7b,0xf2,0x6b,0x6f,0xc5,
        0x30,0x01,0x67,0x2b,0xfe,0xd7,0xab,0x76,
        // Add rest of the 256 entries for full S-box
    };

    static final int[] rcon = {
        0x00,0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1B,0x36
    };

    public static int[][] toMatrix(byte[] input) {
        int[][] state = new int[4][4];
        for (int i = 0; i < 16; i++)
            state[i % 4][i / 4] = input[i] & 0xff;
        return state;
    }

    public static byte[] toBytes(int[][] state) {
        byte[] out = new byte[16];
        for (int i = 0; i < 16; i++)
            out[i] = (byte) state[i % 4][i / 4];
        return out;
    }

    public static void subBytes(int[][] state) {
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++)
                state[row][col] = sbox[state[row][col]];
    }

    public static void shiftRows(int[][] state) {
        for (int row = 1; row < 4; row++) {
            int[] temp = new int[4];
            for (int col = 0; col < 4; col++)
                temp[col] = state[row][(col + row) % 4];
            System.arraycopy(temp, 0, state[row], 0, 4);
        }
    }

    public static void mixColumns(int[][] state) {
        for (int col = 0; col < 4; col++) {
            int[] t = new int[4];
            for (int row = 0; row < 4; row++) t[row] = state[row][col];
            state[0][col] = mul(2,t[0]) ^ mul(3,t[1]) ^ t[2] ^ t[3];
            state[1][col] = t[0] ^ mul(2,t[1]) ^ mul(3,t[2]) ^ t[3];
            state[2][col] = t[0] ^ t[1] ^ mul(2,t[2]) ^ mul(3,t[3]);
            state[3][col] = mul(3,t[0]) ^ t[1] ^ t[2] ^ mul(2,t[3]);
        }
    }

    public static void addRoundKey(int[][] state, int[][] roundKey, int round) {
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++)
                state[row][col] ^= roundKey[row][4 * round + col];
    }

    public static int mul(int a, int b) {
        int result = 0;
        while (b != 0) {
            if ((b & 1) != 0)
                result ^= a;
            boolean hiBitSet = (a & 0x80) != 0;
            a = (a << 1) & 0xFF;
            if (hiBitSet)
                a ^= 0x1B;
            b >>= 1;
        }
        return result;
    }

    public static int[][] keyExpansion(byte[] key) {
        int[][] w = new int[4][44];
        for (int i = 0; i < 16; i++)
            w[i % 4][i / 4] = key[i] & 0xff;

        for (int i = 4; i < 44; i++) {
            int[] temp = new int[4];
            for (int j = 0; j < 4; j++)
                temp[j] = w[j][i - 1];

            if (i % 4 == 0) {
                temp = keyScheduleCore(temp, i / 4);
            }

            for (int j = 0; j < 4; j++)
                w[j][i] = w[j][i - 4] ^ temp[j];
        }
        return w;
    }

    public static int[] keyScheduleCore(int[] word, int iteration) {
        int[] out = new int[4];
        out[0] = sbox[word[1]] ^ rcon[iteration];
        out[1] = sbox[word[2]];
        out[2] = sbox[word[3]];
        out[3] = sbox[word[0]];
        return out;
    }

    public static byte[] encryptBlock(byte[] input, byte[] key) {
        int[][] state = toMatrix(input);
        int[][] roundKeys = keyExpansion(key);

        addRoundKey(state, roundKeys, 0);

        for (int round = 1; round <= 9; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, roundKeys, round);
        }

        subBytes(state);
        shiftRows(state);
        addRoundKey(state, roundKeys, 10);

        return toBytes(state);
    }

    // Decryption not added, let me know if needed
}
