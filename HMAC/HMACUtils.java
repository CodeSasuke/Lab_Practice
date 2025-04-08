package HMAC;

public class HMACUtils {
    public static byte[] hmac(byte[] key, byte[] message) {
        int blockSize = 64;
        byte[] k = new byte[blockSize];

        if (key.length > blockSize)
            key = SHA1.sha1(key);

        System.arraycopy(key, 0, k, 0, key.length);

        byte[] o_key_pad = new byte[blockSize];
        byte[] i_key_pad = new byte[blockSize];

        for (int i = 0; i < blockSize; i++) {
            o_key_pad[i] = (byte) (k[i] ^ 0x5c);
            i_key_pad[i] = (byte) (k[i] ^ 0x36);
        }

        byte[] inner = concat(i_key_pad, message);
        byte[] innerHash = SHA1.sha1(inner);

        byte[] outer = concat(o_key_pad, innerHash);
        return SHA1.sha1(outer);
    }

    private static byte[] concat(byte[] a, byte[] b) {
        byte[] r = new byte[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
}
