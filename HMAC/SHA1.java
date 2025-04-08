package HMAC;

public class SHA1 {
    public static byte[] sha1(byte[] message) {
        int[] h = {
            0x67452301,
            0xEFCDAB89,
            0x98BADCFE,
            0x10325476,
            0xC3D2E1F0
        };

        byte[] padded = padMessage(message);
        for (int i = 0; i < padded.length; i += 64) {
            int[] w = new int[80];
            for (int j = 0; j < 16; j++) {
                w[j] = ((padded[i + 4 * j] & 0xFF) << 24) | ((padded[i + 4 * j + 1] & 0xFF) << 16)
                     | ((padded[i + 4 * j + 2] & 0xFF) << 8) | (padded[i + 4 * j + 3] & 0xFF);
            }
            for (int j = 16; j < 80; j++)
                w[j] = Integer.rotateLeft(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);

            int a = h[0], b = h[1], c = h[2], d = h[3], e = h[4];

            for (int j = 0; j < 80; j++) {
                int f, k;
                if (j < 20) { f = (b & c) | (~b & d); k = 0x5A827999; }
                else if (j < 40) { f = b ^ c ^ d; k = 0x6ED9EBA1; }
                else if (j < 60) { f = (b & c) | (b & d) | (c & d); k = 0x8F1BBCDC; }
                else { f = b ^ c ^ d; k = 0xCA62C1D6; }

                int temp = Integer.rotateLeft(a,5) + f + e + k + w[j];
                e = d; d = c; c = Integer.rotateLeft(b,30); b = a; a = temp;
            }

            h[0] += a; h[1] += b; h[2] += c; h[3] += d; h[4] += e;
        }

        byte[] result = new byte[20];
        for (int i = 0; i < 5; i++) {
            result[4*i] = (byte)(h[i] >>> 24);
            result[4*i+1] = (byte)(h[i] >>> 16);
            result[4*i+2] = (byte)(h[i] >>> 8);
            result[4*i+3] = (byte)(h[i]);
        }
        return result;
    }

    private static byte[] padMessage(byte[] msg) {
        int newLen = ((msg.length + 8) / 64 + 1) * 64;
        byte[] padded = new byte[newLen];
        System.arraycopy(msg, 0, padded, 0, msg.length);
        padded[msg.length] = (byte) 0x80;
        long bitLen = (long) msg.length * 8;
        for (int i = 0; i < 8; i++) {
            padded[padded.length - 1 - i] = (byte)(bitLen >>> (8 * i));
        }
        return padded;
    }
}
